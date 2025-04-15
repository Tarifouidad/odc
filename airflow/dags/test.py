from airflow import DAG
from airflow.operators.bash import BashOperator
from airflow.operators.python import PythonOperator
from datetime import datetime, timedelta

# Fonction pour vérifier le fichier Excel
def check_excel_file(**kwargs):
    import os
    import sys
    
    file_path = "/opt/airflow/talend_jobs/CorrigeAge/CorrigeAge/outdataset.xlsx"
    
    # Affiche la version de Python et les chemins d'accès pour le débogage
    print(f"Version Python: {sys.version}")
    print(f"Chemins Python: {sys.path}")
    
    if os.path.exists(file_path):
        print(f"Le fichier {file_path} existe.")
        try:
            # Essayer avec pandas et openpyxl (qui devrait maintenant être installé)
            import pandas as pd
            print(f"Pandas version: {pd.__version__}")
            
            # Specifier explicitement openpyxl comme moteur
            df = pd.read_excel(file_path, engine='openpyxl')
            
            print(f"Le fichier contient {len(df)} lignes et {len(df.columns)} colonnes")
            print("Noms des colonnes:", df.columns.tolist())
            print("Premières lignes:")
            print(df.head())
            return True
        except Exception as e:
            print(f"Erreur lors de la lecture du fichier avec openpyxl: {e}")
            
            try:
                # Essayer avec xlrd comme alternative
                import pandas as pd
                df = pd.read_excel(file_path, engine='xlrd')
                print(f"Le fichier contient {len(df)} lignes et {len(df.columns)} colonnes (lu avec xlrd)")
                print(df.head())
                return True
            except Exception as e2:
                print(f"Erreur lors de la lecture du fichier avec xlrd: {e2}")
                
                # Afficher des informations sur le fichier en cas d'échec
                import os
                print(f"Taille du fichier: {os.path.getsize(file_path)} octets")
                print(f"Permissions du fichier: {oct(os.stat(file_path).st_mode)[-3:]}")
                return False
    else:
        print(f"Le fichier {file_path} n'existe pas")
        
        # Chercher où pourrait être le fichier
        try:
            import glob
            excel_files = glob.glob("/opt/airflow/talend_jobs/**/*.xlsx", recursive=True)
            if excel_files:
                print("Fichiers Excel trouvés:")
                for ef in excel_files:
                    print(f"  - {ef}")
            else:
                print("Aucun fichier Excel trouvé dans le répertoire talend_jobs")
        except Exception as e:
            print(f"Erreur lors de la recherche de fichiers: {e}")
        
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
    
    # Vérifier l'existence du fichier avec BashOperator (fournit plus d'informations)
    check_file_exists = BashOperator(
        task_id='check_file_exists',
        bash_command='''
        echo "Vérification du fichier de sortie..."
        if [ -f /opt/airflow/talend_jobs/CorrigeAge/CorrigeAge/outdataset.xlsx ]; then
            echo "Le fichier outdataset.xlsx existe."
            # Vérifier la taille du fichier
            ls -la /opt/airflow/talend_jobs/CorrigeAge/CorrigeAge/outdataset.xlsx
            # Essayer de changer les permissions, mais ignorer l'erreur si ça échoue
            chmod 644 /opt/airflow/talend_jobs/CorrigeAge/CorrigeAge/outdataset.xlsx || echo "Impossible de modifier les permissions mais on continue"
            exit 0  # Force successful exit regardless
        else
            echo "Le fichier outdataset.xlsx n'a pas été trouvé."
            # Autres vérifications...
            exit 1  # Only fail if the file doesn't exist
        fi
        ''',
    )
    
    # Nouvelle tâche pour vérifier le fichier Excel de sortie
    check_excel_task = PythonOperator(
        task_id='check_excel_content',
        python_callable=check_excel_file,
    )
    
    # Définir la séquence d'exécution des tâches
    check_container >> run_talend_job >> check_results >> check_file_exists >> check_excel_task