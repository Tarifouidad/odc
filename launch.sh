#!/bin/bash

# Définir les variables d'environnement
TALEND_DIR="/app/talend/jobs/CorrigeAge/"
MERN_DIR="/app/mern"

# Modifier les permissions du répertoire Talend (more restrictive)
chmod 750 "$TALEND_DIR"

# Lancer Talend et capturer le code de sortie
echo "Starting Talend job..."
java -Dtalend.component.manager.m2.repository="$TALEND_DIR/../lib" -Xms256M -Xmx1024M -cp "$TALEND_DIR/../lib/*:." local_project.CorrigeAge.CorrigeAge --context=Default
talend_exit_code=$?

# Vérifier le code de sortie de Talend
if [ $talend_exit_code -eq 0 ]; then
    echo "Talend job executed successfully."
    # Modifier les permissions du fichier outdataset.xlsx après sa création (more restrictive)
    chmod 660 "$TALEND_DIR/CorrigeAge/outdataset.xlsx"
else
    echo "Talend job failed with exit code: $talend_exit_code"
    exit 1
fi

# Lancer MERN et capturer le code de sortie
echo "Starting MERN application..."
cd "$MERN_DIR"
npm start
mern_exit_code=$?

# Vérifier le code de sortie de MERN
if [ $mern_exit_code -eq 0 ]; then
    echo "MERN application started successfully."
else
    echo "MERN application failed with exit code: $mern_exit_code"
    exit 1
fi