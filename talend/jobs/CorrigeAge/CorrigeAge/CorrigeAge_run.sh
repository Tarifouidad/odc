#!/bin/sh
cd `dirname $0`
ROOT_PATH=`pwd`
echo "========================================================"
echo "Début de l'exécution du job Talend CorrigeAge"
echo "Date et heure : $(date)"
echo "Paramètres reçus : $@"
echo "========================================================"

# Extraire le chemin du fichier d'entrée
INPUT_FILE=$(echo "$@" | grep -o 'input_file=[^ ]*' | cut -d= -f2)
echo "Fichier d'entrée détecté : $INPUT_FILE"

# Vérifier si le fichier existe
if [ -f "$INPUT_FILE" ]; then
    echo "Le fichier d'entrée existe"
    
    # Copier le fichier d'entrée directement comme fichier de sortie
    # Dans un vrai job Talend, ce serait remplacé par le traitement réel
    cp "$INPUT_FILE" "$ROOT_PATH/outdataset.xlsx"
    chmod 666 "$ROOT_PATH/outdataset.xlsx"
    
    echo "Le fichier a été traité avec succès"
else
    echo "ERREUR : Le fichier d'entrée $INPUT_FILE n'existe pas"
    ls -la /jobs/input/
    exit 1
fi

echo "========================================================"
echo "Fin de l'exécution du job Talend CorrigeAge"
echo "Date et heure : $(date)"
echo "Code de sortie : 0"
echo "========================================================"
exit 0
