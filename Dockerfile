# Étape 1 : Image de base avec Java (pour Talend et Airflow)
FROM openjdk:11-jre-slim AS base

# Installer les dépendances nécessaires pour Talend et Node.js
RUN apt-get update && apt-get install -y --no-install-recommends \
    unzip \
    curl \
    gnupg \
    ca-certificates \
    findutils \
    && rm -rf /var/lib/apt/lists/*

# Installer Node.js et npm (version 20.x LTS "Iron")
RUN curl -fsSL https://deb.nodesource.com/setup_20.x | bash - \
    && apt-get update \
    && apt-get install -y nodejs \
    && rm -rf /var/lib/apt/lists/* \
    && node --version \
    && npm --version

# Étape 2 : Talend
FROM base AS talend

# Installer JDK
RUN apt-get update && apt-get install -y --no-install-recommends openjdk-11-jdk \
    && rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# Créer le répertoire pour les jobs Talend
RUN mkdir -p /opt/talend/jobs/CorrigeAge/CorrigeAge

# Copier les fichiers Talend (job exporté)
COPY talend/jobs/CorrigeAge /opt/talend/jobs/CorrigeAge
WORKDIR /opt/talend/jobs/CorrigeAge

# S'assurer que le script d'exécution est exécutable
RUN chmod +x CorrigeAge/CorrigeAge_run.sh

# Étape 3 : Airflow
FROM base AS airflow

# Ajouter les utilisateurs et groupes
RUN groupadd -r airflow && useradd -r -g airflow airflow

# Installer Python et les dépendances
RUN apt-get update && apt-get install -y --no-install-recommends \
    python3 \
    python3-pip \
    python3-setuptools \
    gcc \
    libpq-dev \
    && rm -rf /var/lib/apt/lists/*

# Créer la structure de répertoires Airflow
RUN mkdir -p /opt/airflow/dags /opt/airflow/logs /opt/airflow/plugins /opt/airflow/talend_jobs
WORKDIR /opt/airflow

# Copier les fichiers DAG et les jobs Talend
COPY airflow/dags /opt/airflow/dags
COPY talend/jobs /opt/airflow/talend_jobs

# Configurer les permissions
RUN find /opt/airflow/talend_jobs -name "*.sh" -type f -exec chmod +x {} \; && \
    chown -R airflow:airflow /opt/airflow && \
    mkdir -p /home/airflow && \
    chown -R airflow:airflow /home/airflow

# Passer à l'utilisateur airflow
USER airflow

# Installer le provider Docker pour Airflow
RUN pip install --no-cache-dir apache-airflow-providers-docker

# Étape 4 : Application MERN
FROM node:20-slim AS mern

# Définir le répertoire de travail
WORKDIR /app/mern

# Copier package.json et package-lock.json
COPY PFE/package.json PFE/package-lock.json ./

# Installer les dépendances
RUN npm ci --production

# Copier le reste de l'application MERN
COPY PFE/ ./

# Exposer le port
EXPOSE 3000

# Étape 5 : Image finale (multi-stage build)
FROM base AS final

# Passer à l'utilisateur root pour les opérations privilégiées
USER root

# Installer Docker CLI pour permettre à Airflow d'exécuter des commandes Docker
RUN apt-get update && apt-get install -y --no-install-recommends \
    docker.io \
    python3 \
    python3-pip \
    && rm -rf /var/lib/apt/lists/*

# Installer les dépendances Python nécessaires
RUN pip3 install pandas openpyxl requests

# Créer les répertoires nécessaires
RUN mkdir -p /app/talend/jobs/CorrigeAge/ \
    /app/mern \
    /app/shared_data \
    /app/shared_data/output \
    /app/shared_data/archive \
    /scripts

# Copier le script de lancement et définir les permissions
COPY launch.sh /scripts/launch.sh
COPY scripts/wait-for-it.sh /scripts/wait-for-it.sh
RUN chmod +x /scripts/launch.sh /scripts/wait-for-it.sh

# Copier l'application Talend depuis l'étape précédente
COPY --from=talend /opt/talend/jobs /app/talend/jobs

# Copier l'application MERN depuis l'étape précédente
COPY --from=mern /app/mern /app/mern

# Définir les variables d'environnement
ENV PATH="/app/node_modules/.bin:${PATH}"
ENV NODE_ENV=production
ENV AIRFLOW_API_URL=http://airflow-webserver:8080/api/v1
ENV AIRFLOW_USERNAME=admin
ENV AIRFLOW_PASSWORD=admin
ENV MERN_API_URL=http://localhost:3000/api

# Exposer le port
EXPOSE 3000

# Commande par défaut - exécute le script de lancement
CMD ["/bin/bash", "/launch.sh"]