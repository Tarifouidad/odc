# Étape 1 : Image de base avec Java (pour Talend et Airflow)
FROM openjdk:11-jre-slim AS base

# Installer les dépendances nécessaires pour Talend
RUN apt-get update && apt-get install -y --no-install-recommends \
    unzip \
    curl \
    && rm -rf /var/lib/apt/lists/*

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

# Créer le fichier requirements.txt pour les dépendances Python
WORKDIR /tmp
RUN echo "apache-airflow-providers-docker" > requirements.txt && \
    echo "pandas>=1.5.0" >> requirements.txt && \
    echo "xlrd>=2.0.1" >> requirements.txt && \
    echo "openpyxl>=3.0.10" >> requirements.txt && \
    echo "pyarrow>=12.0.0" >> requirements.txt

# Installer les dépendances Python pour Airflow
RUN pip3 install --no-cache-dir -r requirements.txt

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

# Étape 4 : Backend Node.js
FROM node:20-slim AS backend
WORKDIR /app/mern

# Exposer le port
EXPOSE 5000

# Étape 5 : Frontend React
FROM node:20-slim AS frontend
WORKDIR /app/frontend

# Installer les dépendances nécessaires
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    git \
    ca-certificates \
    && rm -rf /var/lib/apt/lists/*

# Cette étape sera ignorée lors de la construction car nous monterons le code source
# Mais nous définissons l'environnement pour être prêt
ENV NODE_ENV=development
ENV VITE_API_URL=http://mer_app:5000

# Exposer le port pour le frontend
EXPOSE 8081

# Étape 6 : Image finale
FROM airflow AS final

# Passer à l'utilisateur root pour les opérations privilégiées
USER root

# Installer Node.js et npm dans l'image finale
RUN apt-get update && apt-get install -y --no-install-recommends \
    docker.io \
    curl \
    gnupg \
    netcat \
    && curl -fsSL https://deb.nodesource.com/setup_20.x | bash - \
    && apt-get install -y nodejs \
    && node -v && npm -v \
    && rm -rf /var/lib/apt/lists/*

# Créer les répertoires partagés et définir les permissions appropriées
RUN mkdir -p /app/mern/PFE /app/frontend && \
    chown -R airflow:airflow /app && \
    chmod -R 755 /app

# Copier les scripts avec des permissions d'exécution
COPY scripts /scripts
RUN chmod -R +x /scripts && \
    chown -R airflow:airflow /scripts

# Revenir à l'utilisateur airflow
USER airflow

# Commande par défaut - sera remplacée par docker-compose
CMD ["tail", "-f", "/dev/null"]