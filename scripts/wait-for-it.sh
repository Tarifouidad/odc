#!/bin/bash

# Vérifier la présence de Node.js et npm
echo "Vérification de Node.js et npm..."
node --version
npm --version

# Définir les variables d'environnement
TALEND_DIR="/app/talend/jobs/CorrigeAge/"
MERN_DIR="/app/mern"
SHARED_DIR="/app/shared_data"

# Créer les répertoires partagés s'ils n'existent pas
echo "Création des répertoires partagés..."
mkdir -p "$SHARED_DIR"
mkdir -p "$SHARED_DIR/output"
mkdir -p "$SHARED_DIR/archive"
chmod -R 777 "$SHARED_DIR"

# Attendre que le conteneur Airflow soit prêt
echo "Attente du démarrage d'Airflow..."
/scripts/wait-for-it.sh airflow-webserver:8080 -t 60

# Vérifier si we-for-it.sh a réussi
if [ $? -ne 0 ]; then
    echo "Impossible de se connecter à Airflow, mais on continue..."
fi

# Démarrer l'application MERN depuis le bon répertoire
echo "Démarrage de l'application MERN..."
cd "$MERN_DIR"

# Vérifier que package.json existe
if [ ! -f "package.json" ]; then
    echo "Fichier package.json introuvable dans $MERN_DIR"
    echo "Contenu du répertoire $MERN_DIR:"
    ls -la
    
    # Rechercher dans PFE
    if [ -d "../PFE" ]; then
        echo "Vérification du répertoire ../PFE"
        if [ -f "../PFE/package.json" ]; then
            echo "package.json trouvé dans ../PFE, utilisation de ce répertoire"
            cd "../PFE"
        else
            echo "Contenu de ../PFE:"
            ls -la "../PFE"
        fi
    fi
    
    # Si toujours pas trouvé, chercher ailleurs
    if [ ! -f "package.json" ]; then
        echo "Recherche de package.json dans d'autres répertoires..."
        find /app -name "package.json" -type f
        
        # Prendre le premier trouvé s'il existe
        PACKAGE_PATH=$(find /app -name "package.json" -type f | head -n 1)
        if [ -n "$PACKAGE_PATH" ]; then
            echo "Utilisation du package.json trouvé à $PACKAGE_PATH"
            cd "$(dirname "$PACKAGE_PATH")"
        else
            echo "Aucun package.json trouvé. Impossible de démarrer l'application."
            exit 1
        fi
    fi
fi

echo "Contenu du répertoire courant ($(pwd)):"
ls -la

echo "Vérification du contenu de package.json:"
cat package.json | grep -E "name|scripts.*start"

# Installer les dépendances si node_modules n'existe pas
if [ ! -d "node_modules" ]; then
    echo "Installation des dépendances npm..."
    npm install
fi

# Démarrer l'application
echo "Exécution de npm start dans $(pwd)"
npm start