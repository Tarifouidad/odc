#!/bin/bash

# Définir la fonction de journalisation
log() {
  echo "$(date +'%Y-%m-%d %H:%M:%S') - $1"
}

# Vérifier la présence de Node.js et npm
log "Vérification de Node.js et npm..."
if command -v node &> /dev/null && command -v npm &> /dev/null; then
  node --version
  npm --version
  log "Node.js et npm sont correctement installés"
else
  log "ERREUR: Node.js et/ou npm ne sont pas installés"
  # Continuer malgré l'erreur pour voir les autres problèmes potentiels
fi

# Définir les variables d'environnement
TALEND_DIR="/app/talend/jobs/CorrigeAge/"
MERN_DIR="/app/mern"
SHARED_DIR="/app/shared_data"

# Créer les répertoires partagés s'ils n'existent pas
log "Création des répertoires partagés..."
mkdir -p "$SHARED_DIR" || log "Erreur lors de la création de $SHARED_DIR"
mkdir -p "$SHARED_DIR/output" || log "Erreur lors de la création de $SHARED_DIR/output"
mkdir -p "$SHARED_DIR/archive" || log "Erreur lors de la création de $SHARED_DIR/archive"
chmod -R 777 "$SHARED_DIR" || log "Erreur lors de la modification des permissions de $SHARED_DIR"

# Vérifier les permissions du répertoire partagé
ls -la "$SHARED_DIR"

# Attendre que le conteneur Airflow soit prêt
log "Attente du démarrage d'Airflow..."
if [ -f "/scripts/wait-for-it.sh" ]; then
  chmod +x /scripts/wait-for-it.sh
  /scripts/wait-for-it.sh airflow-webserver:8080 -t 60
  WAIT_RESULT=$?
  if [ $WAIT_RESULT -ne 0 ]; then
    log "Impossible de se connecter à Airflow, mais on continue..."
  else
    log "Airflow est démarré et accessible"
  fi
else
  log "AVERTISSEMENT: Script wait-for-it.sh introuvable, on ignore l'attente d'Airflow"
fi

# Démarrer l'application MERN depuis le bon répertoire
log "Démarrage de l'application MERN..."
if [ -d "$MERN_DIR" ]; then
  cd "$MERN_DIR" || { log "Impossible d'accéder au répertoire $MERN_DIR"; exit 1; }
else
  log "Le répertoire MERN $MERN_DIR n'existe pas"
  # Chercher un répertoire alternatif
  if [ -d "/app/PFE" ]; then
    log "Utilisation du répertoire /app/PFE à la place"
    cd "/app/PFE" || { log "Impossible d'accéder au répertoire /app/PFE"; exit 1; }
  else
    log "Aucun répertoire d'application valide trouvé"
    exit 1
  fi
fi

# Vérifier que package.json existe
if [ ! -f "package.json" ]; then
  log "Fichier package.json introuvable dans $(pwd)"
  log "Contenu du répertoire actuel:"
  ls -la
    
  # Rechercher dans les répertoires parents
  log "Recherche de package.json dans d'autres répertoires..."
  PACKAGE_PATH=$(find /app -name "package.json" -type f | head -n 1)
  if [ -n "$PACKAGE_PATH" ]; then
    log "Utilisation du package.json trouvé à $PACKAGE_PATH"
    cd "$(dirname "$PACKAGE_PATH")" || { log "Impossible d'accéder au répertoire contenant package.json"; exit 1; }
  else
    log "Aucun package.json trouvé. Impossible de démarrer l'application."
    exit 1
  fi
fi

log "Contenu du répertoire courant ($(pwd)):"
ls -la

log "Vérification du contenu de package.json:"
if [ -f "package.json" ]; then
  cat package.json | grep -E "name|scripts.*start" || log "Format de package.json inattendu"
else
  log "ERREUR: package.json toujours introuvable après la recherche"
  exit 1
fi

# Installer les dépendances si node_modules n'existe pas
if [ ! -d "node_modules" ]; then
  log "Installation des dépendances npm..."
  npm install --no-audit --no-fund || { log "Erreur lors de l'installation des dépendances"; exit 1; }
else
  log "Le répertoire node_modules existe déjà"
fi

# Démarrer l'application
log "Exécution de npm start dans $(pwd)"
npm start