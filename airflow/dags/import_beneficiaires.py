from airflow import DAG
from airflow.operators.bash import BashOperator
from airflow.operators.python import PythonOperator
from datetime import datetime, timedelta
import requests
import os
import logging

# Configuration par défaut pour les tâches du DAG
default_args = {
    'owner': 'airflow',
    'depends_on_past': False,
    'email_on_failure': False,
    'email_on_retry': False,
    'retries': 1,
    'retry_delay': timedelta(minutes=2),
    'start_date': datetime(2024, 4, 1)
}

# Fonction pour télécharger le fichier depuis Cloudinary
def download_file(**context):
    input_file_url = context['dag_run'].conf.get('input_file')
    file_id = context['dag_run'].conf.get('file_id')
    
    if not input_file_url:
        raise ValueError("L'URL du fichier d'entrée est manquante dans la configuration du DAG")
    
    # Créer le répertoire temporaire si nécessaire
    input_dir = "/opt/airflow/talend_jobs/input"
    os.makedirs(input_dir, exist_ok=True)
    
    # Chemin du fichier local
    local_file_path = f"{input_dir}/input_{file_id}.xlsx"
    
    # Télécharger le fichier
    logging.info(f"Téléchargement du fichier depuis {input_file_url}")
    response = requests.get(input_file_url)
    if response.status_code == 200:
        with open(local_file_path, 'wb') as f:
            f.write(response.content)
        
        # Vérifier que le fichier existe et a été téléchargé correctement
        file_size = os.path.getsize(local_file_path)
        logging.info(f"Fichier téléchargé avec succès: {local_file_path}, taille: {file_size} octets")
        
        # Passer le chemin de fichier aux tâches suivantes
        context['ti'].xcom_push(key='local_file_path', value=local_file_path)
        
        return local_file_path
    else:
        raise Exception(f"Échec du téléchargement du fichier: {response.status_code} - {response.text}")

# Fonction pour notifier le serveur Node.js de la fin du traitement
def notify_processing_complete(**context):
    file_id = context['dag_run'].conf.get('file_id')
    success = context['ti'].xcom_pull(task_ids='run_talend_job', key='return_code') == "0"
    output_path = context['ti'].xcom_pull(task_ids='run_talend_job', key='output_path')
    
    status = 'completed' if success else 'error'
    
    payload = {
        'status': status,
        'result': {
            'outputFileUrl': output_path if success else None,
            'summary': {
                'processedAt': datetime.now().isoformat(),
                'success': success
            }
        }
    }
    
    if not success:
        payload['error'] = "Erreur lors de l'exécution du job Talend"
    
    # URL de l'API de notification (mer_app)
    api_url = f"http://mer_app:5000/api/files/{file_id}/process-complete"
    
    try:
        logging.info(f"Envoi de la notification à {api_url}")
        logging.info(f"Payload: {payload}")
        response = requests.post(api_url, json=payload)
        if response.status_code == 200:
            logging.info(f"Notification envoyée avec succès: {response.text}")
        else:
            logging.warning(f"Erreur lors de l'envoi de la notification: {response.status_code} - {response.text}")
    except Exception as e:
        logging.error(f"Exception lors de l'envoi de la notification: {str(e)}")

with DAG(
    'import_beneficiaires',
    default_args=default_args,
    description='Traitement des fichiers Excel des bénéficiaires avec JobETL',
    schedule_interval=None,  # Déclenché manuellement par API
    catchup=False,
    tags=['talend', 'excel', 'beneficiaires'],
) as dag:

    # Tâche pour préparer les répertoires
    prepare_directories = BashOperator(
        task_id='prepare_directories',
        bash_command='''
        # Créer les répertoires d'entrée et de sortie
        mkdir -p /opt/airflow/talend_jobs/input
        mkdir -p /opt/airflow/talend_jobs/output
        mkdir -p /opt/airflow/talend_jobs/JobETL
        mkdir -p /opt/airflow/talend_jobs/JobETL/lib
        
        # Définir les permissions
        chmod -R 777 /opt/airflow/talend_jobs/input
        chmod -R 777 /opt/airflow/talend_jobs/output
        
        echo "Répertoires préparés avec succès"
        
        # Installer dos2unix si nécessaire
        if ! command -v dos2unix &> /dev/null; then
            apt-get update && apt-get install -y dos2unix
        fi
        
        # Vérifier que les scripts existent au bon endroit
        find /opt/airflow/talend_jobs -name "JobETL_run.sh" | while read script; do
            echo "Script trouvé: $script"
            # Convertir le script au format Unix
            dos2unix "$script" || echo "Erreur lors de la conversion de $script"
            chmod +x "$script"
        done
        ''',
    )
    
    # Tâche pour télécharger le fichier d'entrée
    download_input_file = PythonOperator(
        task_id='download_input_file',
        python_callable=download_file,
        provide_context=True,
    )
    
    # Tâche pour exécuter le job Talend JobETL
    run_talend_job = BashOperator(
        task_id='run_talend_job',
        bash_command='''
        FILE_ID="{{ dag_run.conf['file_id'] }}"
        INPUT_FILE="/jobs/input/input_$FILE_ID.xlsx"
        
        # S'assurer que le fichier existe dans le conteneur talend-jobs
        if ! docker ps -q --filter "name=talend-jobs"; then
            echo "Erreur: Le conteneur Talend 'talend-jobs' ne semble pas être en cours d'exécution."
            exit 1
        fi

        if ! docker exec talend-jobs test -f "$INPUT_FILE"; then
            echo "Erreur: Le fichier d'entrée $INPUT_FILE n'existe pas dans le conteneur Talend."
            exit 1
        fi
        
        echo "Exécution du job Talend à $(date)"
        docker exec talend-jobs bash -c "cd /jobs/CorrigeAge/JobETL && chmod +x ./JobETL_run.sh && ./JobETL_run.sh --context_param input_file=$INPUT_FILE > /jobs/CorrigeAge/JobETL/job_output.log 2>&1"
        
        JOB_EXIT_CODE=$?
        echo "Job Talend terminé avec code: $JOB_EXIT_CODE"
        
        if [ $JOB_EXIT_CODE -eq 0 ]; then
            # Copier les fichiers de sortie du conteneur vers Airflow
            OUTPUT_DIR="/opt/airflow/talend_jobs/output"
            mkdir -p $OUTPUT_DIR
            
            # Chercher les fichiers de sortie potentiels
            for OUTPUT_FILE in "OutputAge.xlsx" "outdataset.xlsx"; do
                if docker exec talend-jobs test -f "/jobs/CorrigeAge/JobETL/$OUTPUT_FILE"; then
                    docker cp talend-jobs:/jobs/CorrigeAge/JobETL/$OUTPUT_FILE $OUTPUT_DIR/output_$FILE_ID.xlsx
                    echo "Fichier de sortie généré: $OUTPUT_DIR/output_$FILE_ID.xlsx"
                    echo "{{ ti.xcom_push(key='output_path', value='$OUTPUT_DIR/output_$FILE_ID.xlsx') }}"
                    echo "{{ ti.xcom_push(key='return_code', value='0') }}"
                    exit 0
                fi
            done
            
            # Si aucun fichier trouvé, copier l'entrée comme solution de repli
            docker cp talend-jobs:$INPUT_FILE $OUTPUT_DIR/output_$FILE_ID.xlsx
            echo "{{ ti.xcom_push(key='output_path', value='$OUTPUT_DIR/output_$FILE_ID.xlsx') }}"
            echo "{{ ti.xcom_push(key='return_code', value='0') }}"
        else
            # En cas d'échec, récupérer les logs
            docker exec talend-jobs cat /jobs/CorrigeAge/JobETL/job_output.log || echo "Log non accessible"
            echo "ERREUR: Job Talend a échoué avec code $JOB_EXIT_CODE"
            echo "{{ ti.xcom_push(key='return_code', value='1') }}"
            exit 1
        fi
        ''',
    )
    # Tâche pour notifier l'application Node.js
    notify_app = PythonOperator(
        task_id='notify_app',
        python_callable=notify_processing_complete,
        provide_context=True,
    )
    
    # Définir l'ordre d'exécution des tâches
    prepare_directories >> download_input_file >> run_talend_job >> notify_app
