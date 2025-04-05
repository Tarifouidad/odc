from airflow import DAG
from airflow.operators.python import PythonOperator
from datetime import datetime, timedelta
from airflow.utils.dates import days_ago
import subprocess

default_args = {
    'owner': 'airflow',
    'start_date': days_ago(1),
    'retries': 1,
    'retry_delay': timedelta(minutes=5),
}
def executer_talend_job_python():
    """Execute le job Talend en utilisant subprocess."""
    import os
    import subprocess

    # Définir explicitement le chemin Java
    os.environ['JAVA_HOME'] = '/usr/lib/jvm/java-11-openjdk-amd64'
    os.environ['PATH'] = f"{os.environ['JAVA_HOME']}/bin:{os.environ['PATH']}"

    print("Executing Talend job...")
    try:
        result = subprocess.run(
            ['/opt/airflow/talend_jobs/CorrigeAge/CorrigeAge/CorrigeAge_run.sh'],
            check=True,
            capture_output=True,
            text=True,
            env=os.environ  # Passer les variables d'environnement
        )
        print("Sortie standard du job Talend:", result.stdout)
        print("Sortie d'erreur du job Talend:", result.stderr)
        print("Talend job execution successful.")
        return "Job Talend exécuté avec succès."
    except subprocess.CalledProcessError as e:
        print("Erreur lors de l'exécution du job Talend:", e)
        print("Sortie standard:", e.stdout)
        print("Sortie d'erreur:", e.stderr)
        raise
    
def my_python_function():
    print("Cette fonction Python est exécutée par Airflow.")
    # Ajoutez votre logique Python ici. Par exemple, vous pouvez effectuer une transformation de données,
    # interagir avec des bases de données, appeler des API, etc.
    return "Fonction Python exécutée avec succès"

with DAG(
    dag_id='mon_projet_talend_mern',
    default_args=default_args,
    schedule_interval=None,
    catchup=False,
) as dag:
    executer_talend_job = PythonOperator(
        task_id='executer_talend_job',
        python_callable=executer_talend_job_python,
        dag=dag,
    )

    python_task = PythonOperator(
        task_id='execute_python_function',
        python_callable=my_python_function,
        dag=dag,
    )

    executer_talend_job >> python_task #Setting the order of execution. Talend job first, then python task.