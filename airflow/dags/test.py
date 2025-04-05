from airflow import DAG
from airflow.operators.bash import BashOperator
from airflow.operators.python import PythonOperator
from datetime import datetime, timedelta

# Fonction pour vérifier le fichier Excel
def check_excel_file(**kwargs):
    import pandas as pd
    import os
    
    file_path = "/opt/airflow/talend_jobs/CorrigeAge/CorrigeAge/outdataset.xlsx"
    
    if os.path.exists(file_path):
        # Lire le fichier Excel
        df = pd.read_excel(file_path)
        # Afficher des informations sur le contenu
        print(f"Le fichier contient {len(df)} lignes et {len(df.columns)} colonnes")
        print("Premières lignes :")
        print(df.head())
        return True
    else:
        print("Le fichier n'existe pas")
        return False

# Définition des arguments par défaut
default_args = {
    'owner': 'airflow',
    'depends_on_past': False,
    'email_on_failure': False,
    'email_on_retry': False,
    'retries': 1,
    'retry_delay': timedelta(minutes=5),
}

# Création du DAG
with DAG(
    'talend_job_execution',
    default_args=default_args,
    description='DAG pour exécuter des jobs Talend',
    schedule_interval=None,
    start_date=datetime(2024, 1, 1),
    catchup=False,
    tags=['talend'],
) as dag:
    
    # Vérifier que le conteneur talend-jobs est en cours d'exécution
    check_container = BashOperator(
        task_id='check_talend_container',
        bash_command='docker ps | grep talend-jobs || (echo "Starting talend-jobs container..." && docker start talend-jobs)',
    )
    
    # Exécuter le job Talend dans le conteneur talend-jobs
    run_talend_job = BashOperator(
        task_id='execute_talend_job',
        bash_command='''
        docker exec talend-jobs bash -c "cd /jobs/CorrigeAge/CorrigeAge && chmod +x ./CorrigeAge_run.sh && ./CorrigeAge_run.sh > /jobs/CorrigeAge/job_output.log 2>&1"
        ''',
    )
    
    # Vérifier les résultats du job et afficher un résumé
    check_results = BashOperator(
        task_id='check_job_results',
        bash_command='''
        echo "Job Talend terminé - Vérification des résultats"
        if [ -f /opt/airflow/talend_jobs/CorrigeAge/job_output.log ]; then
            echo "Résumé du job :"
            grep -A 5 "Code de sortie" /opt/airflow/talend_jobs/CorrigeAge/job_output.log || echo "Informations de sortie non trouvées"
        else
            echo "Fichier de log non trouvé. Vérifiez le conteneur talend-jobs directement."
        fi
        ''',
    )
    
    # Nouvelle tâche pour vérifier le fichier Excel de sortie
    check_excel_task = PythonOperator(
        task_id='check_excel_content',
        python_callable=check_excel_file,
        dag=dag,  # Ici dag est défini car nous sommes dans le contexte du DAG
    )
    
    # Vérifier l'existence du fichier avec BashOperator (alternative)
    check_file_exists = BashOperator(
        task_id='check_file_exists',
        bash_command='''
        echo "Vérification du fichier de sortie..."
        if [ -f /opt/airflow/talend_jobs/CorrigeAge/CorrigeAge/outdataset.xlsx ]; then
            echo "Le fichier outdataset.xlsx existe."
            # Vérifier la taille du fichier
            ls -la /opt/airflow/talend_jobs/CorrigeAge/CorrigeAge/outdataset.xlsx
        else
            echo "Le fichier outdataset.xlsx n'a pas été trouvé."
            # Vérifier si le fichier est dans un autre répertoire
            find /opt/airflow/talend_jobs -name "outdataset.xlsx" -type f
        fi
        ''',
    )
    
    # Définir la séquence d'exécution des tâches
    check_container >> run_talend_job >> check_results >> check_file_exists >> check_excel_task