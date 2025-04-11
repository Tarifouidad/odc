#!/bin/bash

echo "=== Démarrage simplifié de l'application MERN ($(date)) ==="

# Fonction pour attendre qu'un service soit disponible
wait_for_service() {
  local host=$1
  local port=$2
  local timeout=$3
  
  echo "Attente du service $host:$port (timeout: ${timeout}s)..."
  
  local start_time=$(date +%s)
  local end_time=$((start_time + timeout))
  
  while [ $(date +%s) -lt $end_time ]; do
    if nc -z $host $port >/dev/null 2>&1; then
      echo "Service $host:$port est disponible!"
      return 0
    fi
    echo -n "."
    sleep 2
  done
  
  echo "Timeout en attendant $host:$port"
  return 1
}

# Attendre qu'Airflow soit disponible
wait_for_service "airflow-webserver" 8080 60 || echo "Airflow n'est pas disponible, mais on continue..."

# Trouvons tous les package.json dans le système
echo "Recherche des fichiers package.json disponibles..."
PACKAGE_FILES=$(find /app -name "package.json" -type f)

if [ -z "$PACKAGE_FILES" ]; then
    echo "ERREUR: Aucun fichier package.json trouvé dans /app"
    echo "Contenu de /app:"
    ls -la /app
    exit 1
fi

echo "Fichiers package.json trouvés:"
echo "$PACKAGE_FILES"

# Prendre le premier package.json trouvé
FIRST_PACKAGE=$(echo "$PACKAGE_FILES" | head -n 1)
APP_DIR=$(dirname "$FIRST_PACKAGE")

echo "Utilisation du package.json dans: $APP_DIR"
cd "$APP_DIR"

echo "Vérifions son contenu:"
grep -E "name|scripts.*start" package.json || echo "ATTENTION: Pas de script start trouvé!"

# Vérifier que node_modules existe
if [ ! -d "node_modules" ]; then
    echo "Installation des dépendances npm..."
    npm install
    
    if [ $? -ne 0 ]; then
        echo "Erreur lors de l'installation des dépendances. Tentative avec --force..."
        npm install --force
    fi
fi

# Lancer l'application
echo "Démarrage de l'application MERN dans: $(pwd)"
npm start > /tmp/npm-output.log 2>&1 &
echo "Application démarrée en arrière-plan, logs dans /tmp/npm-output.log"
tail -f /tmp/npm-output.log