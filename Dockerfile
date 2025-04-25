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
RUN mkdir -p /opt/talend/jobs/CorrigeAge/jobETL
# Copier les fichiers Talend (job exporté)
COPY talend/jobs/CorrigeAge /opt/talend/jobs/CorrigeAge
WORKDIR /opt/talend/jobs/CorrigeAge

# S'assurer que le script d'exécution est exécutable
RUN chmod +x CorrigeAge/CorrigeAge_run.sh
RUN chmod +x /CorrigeAge_run/JobETL_run.sh

# Étape 3 : Airflow
FROM apache/airflow:latest AS airflow

# Ajouter les dépendances de système pour la compilation de packages Python
USER root
RUN apt-get update && apt-get install -y --no-install-recommends \
    build-essential \
    gcc \
    && rm -rf /var/lib/apt/lists/*

# Installer les packages Python directement
USER airflow
RUN pip install --no-cache-dir \
    pandas \
    openpyxl \
    xlrd \
    pyarrow \
    && pip list | grep -E 'openpyxl|xlrd|pandas|pyarrow'

# Créer la structure de répertoires pour les jobs Talend
USER root
RUN mkdir -p /opt/airflow/talend_jobs
COPY --chown=airflow:root talend/jobs /opt/airflow/talend_jobs
RUN find /opt/airflow/talend_jobs -name "*.sh" -type f -exec chmod +x {} \;

# Étape 4 : MERN préparation (NOUVEAU)
FROM node:20-slim AS mern-base
WORKDIR /app/mern/PFE

# Installer les outils de base et esbuild
RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    && npm install -g npm@latest \
    && npm install -D esbuild@0.25.0 \
    && rm -rf /var/lib/apt/lists/*

# Créer les répertoires nécessaires 
RUN mkdir -p /app/mern/PFE/Frontend \
    && mkdir -p /app/mern/PFE/Backend

# Étape finale
FROM airflow AS final

# Passer à l'utilisateur root pour les opérations privilégiées
USER root

# Installer Node.js et npm dans l'image finale
RUN apt-get update && apt-get install -y --no-install-recommends \
    docker.io \
    curl \
    gnupg \
    netcat-openbsd \
    && curl -fsSL https://deb.nodesource.com/setup_20.x | bash - \
    && apt-get install -y nodejs \
    && node -v && npm -v \
    && rm -rf /var/lib/apt/lists/*

# Installer esbuild avec la bonne version
RUN npm install -g npm@latest \
    && npm install -g esbuild@0.25.0

# Créer un utilisateur node avec les mêmes UID/GID que dans l'image node officielle
RUN groupadd --gid 1000 node \
    && useradd --uid 1000 --gid node --shell /bin/bash --create-home node

# Créer les répertoires partagés et définir les permissions appropriées
RUN mkdir -p /app/mern/PFE/Frontend \
    && mkdir -p /app/mern/PFE/Backend \
    && chown -R node:root /app \
    && chmod -R g+w /app

# Copier les scripts avec des permissions d'exécution
COPY scripts /scripts
RUN chmod -R +x /scripts && \
    chown -R node:root /scripts && \
    chmod -R g+w /scripts

# Vérifier que les packages Python sont bien installés
RUN echo "Vérification des packages installés:" && \
    su airflow -c "pip list | grep -E 'openpyxl|xlrd|pandas|pyarrow'"

# S'assurer que l'utilisateur airflow appartient au groupe root (GID=0)
RUN usermod -a -G root airflow && \
    usermod -a -G root node && \
    id airflow && \
    id node

# Terminer avec l'utilisateur root
USER root
CMD ["tail", "-f", "/dev/null"]