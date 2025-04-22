from airflow import DAG
from airflow.operators.bash import BashOperator
from airflow.operators.python import PythonOperator
from datetime import datetime, timedelta

# Fonction pour vérifier le fichier Excel
def check_excel_file(**kwargs):
    import os
    import sys
    import subprocess
    
    file_path = "/opt/airflow/talend_jobs/CorrigeAge/CorrigeAge/outdataset.xlsx"
    
    print(f"Version Python: {sys.version}")
    print(f"Chemins Python: {sys.path}")
    
    if os.path.exists(file_path):
        print(f"Le fichier {file_path} existe.")
        file_size = os.path.getsize(file_path)
        print(f"Taille du fichier: {file_size} octets")
        file_permissions = oct(os.stat(file_path).st_mode)[-3:]
        print(f"Permissions du fichier: {file_permissions}")
        
        # Vérification basique sans pandas
        if file_size > 0:
            try:
                # Lire les premiers octets pour confirmer qu'il s'agit d'un fichier Excel
                with open(file_path, 'rb') as f:
                    header = f.read(8)
                
                # Signatures de fichiers Excel
                xlsx_sig = b'\x50\x4B\x03\x04'  # Signature ZIP (fichier XLSX)
                xls_sig = b'\xD0\xCF\x11\xE0'   # Signature OLE (fichier XLS)
                
                if header.startswith(xlsx_sig):
                    print("Le fichier est au format XLSX (Office Open XML)")
                    
                    # Tenter d'extraire des informations supplémentaires
                    try:
                        # Essayons de voir s'il y a des modules disponibles pour l'analyse
                        try:
                            import pandas as pd
                            print("Module pandas trouvé, tentative de lecture...")
                            df = pd.read_excel(file_path)
                            print(f"Lecture réussie! {len(df)} lignes, {len(df.columns)} colonnes")
                            print(f"Colonnes: {', '.join(df.columns.tolist())}")
                        except ImportError:
                            print("Module pandas non disponible")
                        except Exception as e:
                            print(f"Erreur lors de la lecture avec pandas: {e}")
                    except Exception:
                        print("Analyse supplémentaire non disponible")
                    
                    return True
                elif header.startswith(xls_sig):
                    print("Le fichier est au format XLS (Excel Binaire)")
                    return True
                else:
                    print(f"Le fichier existe mais ne semble pas être un fichier Excel valide")
                    print(f"En-tête hexadécimal: {header.hex()}")
                    return False
            except Exception as e:
                print(f"Erreur lors de la lecture du fichier: {e}")
                return False
        else:
            print("Le fichier existe mais est vide")
            return False
    else:
        print(f"Le fichier {file_path} n'existe pas")
        try:
            # Chercher des fichiers Excel dans le répertoire
            parent_dir = os.path.dirname(file_path)
            if os.path.exists(parent_dir):
                print(f"Contenu du répertoire {parent_dir}:")
                for file in os.listdir(parent_dir):
                    file_path_full = os.path.join(parent_dir, file)
                    file_stats = os.stat(file_path_full)
                    print(f"  - {file} ({file_stats.st_size} octets, modifié le {datetime.fromtimestamp(file_stats.st_mtime)})")
            
            # Chercher dans le répertoire parent également
            try:
                grand_parent = os.path.dirname(parent_dir)
                print(f"Vérification du répertoire parent {grand_parent}:")
                for file in os.listdir(grand_parent):
                    if file.endswith('.xlsx') or file.endswith('.xls'):
                        file_path_full = os.path.join(grand_parent, file)
                        print(f"  - {file} ({os.path.getsize(file_path_full)} octets)")
            except Exception:
                pass
        except Exception as e:
            print(f"Erreur lors de la liste du répertoire: {e}")
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
    
    # Vérifier l'espace disque disponible
    check_disk_space = BashOperator(
        task_id='check_disk_space',
        bash_command='echo "Espace disque disponible:" && df -h | grep -v tmpfs',
    )
    
    # Vérifier que le conteneur talend-jobs est en cours d'exécution
    check_container = BashOperator(
        task_id='check_talend_container',
        bash_command='docker ps | grep talend-jobs || (echo "Starting talend-jobs container..." && docker start talend-jobs)',
    )
    
    # Exécuter le job Talend dans le conteneur talend-jobs
    run_talend_job = BashOperator(
        task_id='execute_talend_job',
        bash_command='''
        echo "Exécution du job Talend à $(date)"
        docker exec talend-jobs bash -c "cd /jobs/CorrigeAge/CorrigeAge && chmod +x ./CorrigeAge_run.sh && ./CorrigeAge_run.sh > /jobs/CorrigeAge/job_output.log 2>&1"
        JOB_EXIT_CODE=$?
        echo "Job Talend terminé avec code: $JOB_EXIT_CODE"
        exit $JOB_EXIT_CODE
        ''',
    )
    
    # Vérifier les résultats du job et afficher un résumé
    check_results = BashOperator(
        task_id='check_job_results',
        bash_command='''
        echo "Job Talend terminé - Vérification des résultats à $(date)"
        if [ -f /opt/airflow/talend_jobs/CorrigeAge/job_output.log ]; then
            echo "Résumé du job :"
            grep -A 5 "Code de sortie" /opt/airflow/talend_jobs/CorrigeAge/job_output.log || echo "Informations de sortie non trouvées"
            
            # Affiche les 20 dernières lignes du log pour plus de contexte
            echo "Dernières lignes du log:"
            tail -n 20 /opt/airflow/talend_jobs/CorrigeAge/job_output.log
        else
            echo "Fichier de log non trouvé. Vérifiez le conteneur talend-jobs directement."
            # Tenter de localiser le fichier de log
            find /opt/airflow/talend_jobs -name "*.log" -type f -mtime -1 | xargs ls -la
        fi
        ''',
    )
    
    # Vérifier l'existence du fichier avec BashOperator (fournit plus d'informations)
    check_file_exists = BashOperator(
        task_id='check_file_exists',
        bash_command='''
        echo "Vérification du fichier de sortie à $(date)..."
        EXCEL_FILE="/opt/airflow/talend_jobs/CorrigeAge/CorrigeAge/outdataset.xlsx"
        if [ -f "$EXCEL_FILE" ]; then
            echo "Le fichier outdataset.xlsx existe."
            # Vérifier la taille et date de modification du fichier
            stat "$EXCEL_FILE"
            
            # Essayer de changer les permissions
            chmod 644 "$EXCEL_FILE" 2>/dev/null || echo "Impossible de modifier les permissions mais on continue"
            
            # Vérifier si le fichier a été modifié récemment (moins de 10 minutes)
            if [ $(( $(date +%s) - $(stat -c %Y "$EXCEL_FILE") )) -lt 600 ]; then
                echo "Le fichier a été modifié récemment, c'est probablement la dernière exécution"
            else
                echo "ATTENTION: Le fichier n'a pas été modifié récemment"
                echo "Date de dernière modification: $(stat -c %y "$EXCEL_FILE")"
            fi
            exit 0
        else
            echo "Le fichier outdataset.xlsx n'a pas été trouvé."
            # Rechercher d'autres fichiers Excel
            echo "Recherche d'autres fichiers Excel dans le répertoire talend_jobs:"
            find /opt/airflow/talend_jobs -name "*.xlsx" -type f | xargs ls -la
            exit 1
        fi
        ''',
    )
    
    # Nouvelle tâche pour vérifier le fichier Excel de sortie
    check_excel_task = PythonOperator(
        task_id='check_excel_content',
        python_callable=check_excel_file,
    )
    
    # Définir la séquence d'exécution des tâches
    check_disk_space >> check_container >> run_talend_job >> check_results >> check_file_exists >> check_excel_task