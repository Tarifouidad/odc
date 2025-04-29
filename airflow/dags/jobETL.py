from airflow import DAG
from airflow.operators.bash import BashOperator
from airflow.operators.python import PythonOperator
from datetime import datetime, timedelta
import os
import requests
import json
import shutil
import uuid

# Définir les arguments par défaut
default_args = {
    'owner': 'airflow',
    'depends_on_past': False,
    'email_on_failure': False,
    'email_on_retry': False,
    'retries': 1,
    'retry_delay': timedelta(minutes=5),
}

# Fonction pour télécharger le fichier d'entrée avec un identifiant unique
def download_input_file(**context):
    """Télécharge le fichier Excel depuis l'URL fournie et lui assigne un ID unique"""
    # Récupérer les données de configuration du DAG
    input_file_url = context['dag_run'].conf.get('input_file')
    file_id = context['dag_run'].conf.get('file_id', str(uuid.uuid4()))
    
    if not input_file_url:
        raise ValueError("L'URL du fichier d'entrée est requise")
    
    # Créer les répertoires si nécessaires
    input_dir = "/opt/airflow/talend_jobs/input"
    os.makedirs(input_dir, exist_ok=True)
    
    # Créer un nom de fichier unique basé sur l'ID du fichier
    unique_filename = f"input_file_{file_id}.xlsx"
    local_path = f"{input_dir}/{unique_filename}"
    
    # Télécharger le fichier
    print(f"Téléchargement du fichier depuis {input_file_url}")
    response = requests.get(input_file_url)
    response.raise_for_status()
    
    with open(local_path, 'wb') as f:
        f.write(response.content)
    
    print(f"Fichier téléchargé avec succès: {local_path}")
    
    # Stocker les chemins pour les utiliser dans les tâches suivantes
    context['ti'].xcom_push(key='input_file_path', value=local_path)
    context['ti'].xcom_push(key='file_id', value=file_id)
    context['ti'].xcom_push(key='unique_filename', value=unique_filename)
    
    return local_path

# Fonction pour notifier l'application backend de l'état du traitement
def notify_job_completion(**context):
    """Notifie l'application backend que le traitement est terminé"""
    # Récupérer les données
    file_id = context['ti'].xcom_pull(key='file_id') or context['dag_run'].conf.get('file_id')
    unique_filename = context['ti'].xcom_pull(key='unique_filename')
    
    if not file_id:
        raise ValueError("L'ID du fichier est requis")
    
    # Construire les chemins des fichiers
    output_filename = f"output_file_{file_id}.xlsx"
    output_file = f"/opt/airflow/talend_jobs/output/{output_filename}"
    talend_output = "/opt/airflow/talend_jobs/JobETL/JobETL/outdataset.xlsx"
    
    # Vérifier si le fichier de sortie Talend existe
    status = "completed" if os.path.exists(talend_output) else "error"
    
    # Si le traitement a réussi, copier le fichier vers le répertoire de sortie
    if status == "completed":
        os.makedirs(os.path.dirname(output_file), exist_ok=True)
        shutil.copy2(talend_output, output_file)
        print(f"Fichier de sortie copié vers {output_file}")
    
    # Préparer les données de notification
    payload = {
        "status": status,
        "result": {
            "outputFileUrl": f"/api/files/{file_id}/download" if status == "completed" else None,
            "outputFileName": output_filename if status == "completed" else None,
            "summary": {
                "recordsProcessed": 100,  # À remplacer par le nombre réel si disponible
                "fileId": file_id,
                "completedAt": datetime.now().isoformat()
            }
        }
    }
    
    if status == "error":
        payload["error"] = "Erreur lors du traitement du fichier"
    
    # Envoyer la notification
    try:
        print(f"Envoi de la notification pour le fichier {file_id}")
        response = requests.post(
            f"http://mer_app:5000/api/files/{file_id}/process-complete",
            json=payload,
            headers={"Content-Type": "application/json"}
        )
        response.raise_for_status()
        print(f"Notification envoyée avec succès: {response.status_code}")
        return f"Notification envoyée pour le fichier {file_id} avec statut {status}"
    except Exception as e:
        print(f"Erreur lors de l'envoi de la notification: {str(e)}")
        raise

# Création du DAG
with DAG(
    'process_excel_file',
    default_args=default_args,
    description='Traite les fichiers Excel avec un identifiant unique pour chaque fichier',
    schedule_interval=None,
    start_date=datetime(2025, 1, 1),
    catchup=False,
    tags=['talend', 'excel', 'beneficiaires'],
) as dag:
    
    # Tâche pour créer les répertoires nécessaires
    prepare_directories = BashOperator(
        task_id='prepare_directories',
        bash_command='''
        # Créer les répertoires nécessaires avec des permissions complètes
        mkdir -p /opt/airflow/talend_jobs/input
        mkdir -p /opt/airflow/talend_jobs/output
        mkdir -p /opt/airflow/talend_jobs/logs
        chmod -R 777 /opt/airflow/talend_jobs/input
        chmod -R 777 /opt/airflow/talend_jobs/output
        chmod -R 777 /opt/airflow/talend_jobs/logs
        
        # Créer aussi des répertoires dans le conteneur Talend
        docker exec talend-jobs mkdir -p /jobs/input
        docker exec talend-jobs mkdir -p /jobs/output
        docker exec talend-jobs chmod -R 777 /jobs/input /jobs/output
        
        echo "Répertoires préparés avec succès"
        ''',
    )
    
    # Tâche pour télécharger le fichier d'entrée
    download_file = PythonOperator(
        task_id='download_input_file',
        python_callable=download_input_file,
        provide_context=True,
    )
    
    # Tâche pour copier le fichier dans le conteneur Talend
    copy_to_talend = BashOperator(
        task_id='copy_to_talend',
        bash_command='''
        # Récupérer le chemin du fichier d'entrée depuis XCom
        INPUT_FILE="{{ ti.xcom_pull(key='input_file_path') }}"
        FILE_ID="{{ ti.xcom_pull(key='file_id') }}"
        UNIQUE_FILENAME="{{ ti.xcom_pull(key='unique_filename') }}"
        
        echo "Copie du fichier $INPUT_FILE vers le conteneur Talend"
        
        # Copier le fichier vers le conteneur Talend
        docker cp "$INPUT_FILE" talend-jobs:/jobs/input/"$UNIQUE_FILENAME"
        
        # Vérifier que la copie a réussi
        if docker exec talend-jobs ls -la /jobs/input/"$UNIQUE_FILENAME"; then
            echo "Fichier copié avec succès"
        else
            echo "ERREUR: Échec de la copie du fichier"
            exit 1
        fi
        ''',
    )
    
    # Ajoute cette tâche avant run_talend_job
    convert_file_format = BashOperator(
        task_id='convert_file_format',
        bash_command='''
            # Installer les outils nécessaires
            pip3 install pandas openpyxl xlwt --user >/dev/null 2>&1
            
            # Créer un script Python pour convertir le fichier
            cat > /tmp/convert_excel.py << 'EOF'
    import pandas as pd
    import os
    import sys

    input_file = sys.argv[1]
    output_file = input_file.replace('.xlsx', '.xls')

    print(f"Converting {input_file} to {output_file}")
    try:
        # Lire le fichier XLSX
        df = pd.read_excel(input_file)
        print(f"Successfully read file with {len(df)} rows")
        
        # Écrire en format XLS
        df.to_excel(output_file, engine='xlwt', index=False)
        print(f"Successfully converted to XLS format")
        
        print("Conversion completed")
    except Exception as e:
        print(f"Error during conversion: {str(e)}")
        sys.exit(1)
    EOF
            
            # Exécuter la conversion dans le conteneur
            docker exec talend-jobs mkdir -p /jobs/input
            
            # Définir les chemins de fichiers
            XLSX_FILE="/opt/airflow/talend_jobs/input/{{ ti.xcom_pull(key='unique_filename') }}"
            CONTAINER_XLSX="/jobs/input/{{ ti.xcom_pull(key='unique_filename') }}"
            CONTAINER_XLS="/jobs/input/input_file_{{ ti.xcom_pull(key='file_id') }}.xls"
            
            # Copier le fichier XLSX dans le conteneur
            docker cp "$XLSX_FILE" talend-jobs:"$CONTAINER_XLSX"
            
            # Exécuter le script de conversion
            docker exec talend-jobs bash -c "python3 /tmp/convert_excel.py '$CONTAINER_XLSX'"
            
            echo "File format conversion completed"
        ''',
    )

    # Modifie ta tâche run_talend_job pour utiliser le fichier XLS
    run_talend_job = BashOperator(
        task_id='run_talend_job',
        bash_command='''
            # Récupérer les données depuis XCom
            FILE_ID="{{ ti.xcom_pull(key='file_id') }}"
            
            # Utiliser le fichier XLS converti
            INPUT_FILE="/jobs/input/input_file_${FILE_ID}.xlsx"
            OUTPUT_DIR="/jobs/output"
            LOG_FILE="/opt/airflow/talend_jobs/logs/job_${FILE_ID}_$(date +%Y%m%d%H%M%S).log"
            
            echo "Exécution du job Talend avec le fichier $INPUT_FILE"
            
            # Exécuter le job Talend
            docker exec talend-jobs bash -c "cd /jobs/jobETL/jobETL && ./jobETL_run.sh --context_param input_file='$INPUT_FILE'" > "$LOG_FILE" 2>&1
            JOB_EXIT_CODE=$?
            
            # Reste du code...
        ''',
    )

    
    # Tâche pour vérifier et copier les résultats
    process_results = BashOperator(
        task_id='process_results',
        bash_command='''
        # Récupérer l'ID du fichier depuis XCom
        FILE_ID="{{ ti.xcom_pull(key='file_id') }}"
        
        # Chemin du fichier de sortie dans Airflow
        OUTPUT_FILE="/opt/airflow/talend_jobs/JobETL/JobETL/outdataset.xlsx"
        OUTPUT_DESTINATION="/opt/airflow/talend_jobs/output/output_file_${FILE_ID}.xlsx"
        
        echo "Traitement du fichier de sortie pour le fichier ID: $FILE_ID"
        
        # Vérifier si le fichier de sortie existe
        if [ -f "$OUTPUT_FILE" ]; then
            echo "Fichier de sortie trouvé: $OUTPUT_FILE"
            
            # Copier le fichier vers le répertoire de sortie avec l'ID unique
            cp "$OUTPUT_FILE" "$OUTPUT_DESTINATION"
            chmod 666 "$OUTPUT_DESTINATION"
            
            echo "Fichier de sortie copié vers $OUTPUT_DESTINATION"
            exit 0
        else
            echo "ERREUR: Fichier de sortie non trouvé dans $OUTPUT_FILE"
            
            # Chercher le fichier ailleurs
            FOUND_FILE=$(find /opt/airflow/talend_jobs -name "outdataset.xlsx" -type f | head -1)
            
            if [ -n "$FOUND_FILE" ]; then
                echo "Fichier trouvé dans un autre emplacement: $FOUND_FILE"
                cp "$FOUND_FILE" "$OUTPUT_DESTINATION"
                chmod 666 "$OUTPUT_DESTINATION"
                echo "Fichier copié vers $OUTPUT_DESTINATION"
                exit 0
            else
                echo "Aucun fichier de sortie trouvé"
                exit 1
            fi
        fi
        ''',
    )
    
    # Tâche pour notifier l'application backend
    notify_completion = PythonOperator(
        task_id='notify_completion',
        python_callable=notify_job_completion,
        provide_context=True,
    )
    
    # Définir l'ordre d'exécution des tâches
    prepare_directories >> download_file >> copy_to_talend  >> run_talend_job >> process_results >> notify_completion