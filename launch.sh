#!/bin/bash

echo "=== Démarrage simplifié de l'application MERN ($(date)) ==="

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
npm start