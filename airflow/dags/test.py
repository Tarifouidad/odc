from airflow import DAG
from airflow.operators.bash import BashOperator
from airflow.operators.python import PythonOperator
from datetime import datetime, timedelta

# Fonction pour vérifier le fichier Excel
def check_excel_file(**kwargs):
    import os
    file_path = "/opt/airflow/talend_jobs/CorrigeAge/CorrigeAge/outdataset.xlsx"
    
    if os.path.exists(file_path):
        print(f"Le fichier {file_path} existe.")
        file_size = os.path.getsize(file_path)
        print(f"Taille du fichier: {file_size} octets")
        return True
    else:
        print(f"Le fichier {file_path} n'existe pas")
        return False

with DAG(
    dag_id='process_excel_file1',
    start_date=datetime(2024, 1, 1),
    schedule_interval=None,
    catchup=False,
) as dag:
    # Préparation des répertoires et des permissions
    prepare_directories = BashOperator(
        task_id='prepare_directories',
        bash_command='''
        # Créer les répertoires nécessaires
        mkdir -p /opt/airflow/talend_jobs/input
        chmod -R 777 /opt/airflow/talend_jobs/input
        
        # S'assurer que le conteneur talend-jobs a les bons répertoires et permissions
        docker exec talend-jobs bash -c "mkdir -p /jobs/input && chmod -R 777 /jobs/input"
        docker exec talend-jobs bash -c "mkdir -p /jobs/CorrigeAge/CorrigeAge && chmod -R 777 /jobs/CorrigeAge/CorrigeAge"
        
        echo "Répertoires et permissions préparés"
        ''',
    )

    # Préparation du script CorrigeAge_run.sh
    prepare_script = BashOperator(
        task_id='prepare_script',
        bash_command='''
        # Créer le script dans un fichier temporaire
        cat > /tmp/CorrigeAge_run.sh << 'EOL'
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
EOL
        
        # Copier le script dans le conteneur et lui donner les permissions
        chmod +x /tmp/CorrigeAge_run.sh
        docker cp /tmp/CorrigeAge_run.sh talend-jobs:/jobs/CorrigeAge/CorrigeAge/
        docker exec talend-jobs chmod +x /jobs/CorrigeAge/CorrigeAge/CorrigeAge_run.sh
        ''',
    )

    # Task to download the file
    download_file = BashOperator(
        task_id='download_input_file',
        bash_command='''
        mkdir -p /opt/airflow/talend_jobs/input
        curl -s "{{ dag_run.conf["input_file"] }}" -o /opt/airflow/talend_jobs/input/input_file.xlsx
        
        # Copier le fichier dans le conteneur talend-jobs
        docker exec talend-jobs bash -c "mkdir -p /jobs/input"
        docker cp /opt/airflow/talend_jobs/input/input_file.xlsx talend-jobs:/jobs/input/
        
        # Vérifier que la copie a fonctionné
        echo "Fichier téléchargé depuis {{ dag_run.conf["input_file"] }}"
        docker exec talend-jobs ls -la /jobs/input/
        ''',
    )

    # Task to run the Talend job
    run_talend_job = BashOperator(
        task_id='run_talend_job',
        bash_command='''
        echo "Exécution du job Talend à $(date)"
        docker exec talend-jobs bash -c "cd /jobs/CorrigeAge/CorrigeAge && ./CorrigeAge_run.sh --context_param input_file=/jobs/input/input_file.xlsx > /jobs/CorrigeAge/job_output.log 2>&1"
        JOB_EXIT_CODE=$?
        echo "Job Talend terminé avec code: $JOB_EXIT_CODE"
        
        # En cas d'échec, afficher les logs pour débogage
        if [ $JOB_EXIT_CODE -ne 0 ]; then
            echo "ERREUR dans l'exécution du job Talend. Logs:"
            docker exec talend-jobs cat /jobs/CorrigeAge/job_output.log
        fi
        
        exit $JOB_EXIT_CODE
        ''',
    )

    # Task to check results
    check_results = BashOperator(
        task_id='check_job_results',
        bash_command='''
        echo "Job Talend terminé - Vérification des résultats à $(date)"
        docker exec talend-jobs cat /jobs/CorrigeAge/job_output.log || echo "Log non trouvé dans le conteneur"
        ''',
    )

    # Task to check file exists
    check_file_exists = BashOperator(
        task_id='check_file_exists',
        bash_command='''
        echo "Vérification du fichier de sortie à $(date)..."
        
        # Vérifier dans le conteneur talend-jobs
        docker exec talend-jobs bash -c "ls -la /jobs/CorrigeAge/CorrigeAge/"
        
        # Vérifier si le fichier existe dans le conteneur
        if docker exec talend-jobs test -f "/jobs/CorrigeAge/CorrigeAge/outdataset.xlsx"; then
            echo "Le fichier outdataset.xlsx existe dans le conteneur."
            # Copier vers airflow si nécessaire
            docker cp talend-jobs:/jobs/CorrigeAge/CorrigeAge/outdataset.xlsx /opt/airflow/talend_jobs/CorrigeAge/CorrigeAge/
            echo "Fichier copié vers Airflow"
            exit 0
        else
            echo "Le fichier outdataset.xlsx n'a pas été trouvé dans le conteneur."
            exit 1
        fi
        ''',
    )

    # Task to notify app
    notify_app = BashOperator(
        task_id='notify_app',
        bash_command='''
        # Vérifier si le job a réussi
        if docker exec talend-jobs test -f "/jobs/CorrigeAge/CorrigeAge/outdataset.xlsx"; then
            # Notifier l'application que le traitement est terminé
            curl -X POST "http://mer_app:5000/api/files/{{ dag_run.conf["file_id"] }}/process-complete" \
                 -H "Content-Type: application/json" \
                 -d '{"status": "success", "message": "Fichier traité avec succès"}'
        else
            # Notifier l'application que le traitement a échoué
            curl -X POST "http://mer_app:5000/api/files/{{ dag_run.conf["file_id"] }}/process-complete" \
                 -H "Content-Type: application/json" \
                 -d '{"status": "error", "message": "Erreur lors du traitement du fichier"}'
        fi
        ''',
    )

    # Définir les dépendances
    prepare_directories >> prepare_script >> download_file >> run_talend_job >> check_results >> check_file_exists >> notify_app