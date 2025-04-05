from airflow import DAG
from airflow.providers.docker.operators.docker import DockerOperator
from datetime import datetime

# Create a custom operator class to disable command template rendering
class NonTemplatedDockerOperator(DockerOperator):
    template_fields = tuple(field for field in DockerOperator.template_fields if field != 'command')

with DAG(
    dag_id='test_docker_dag',
    start_date=datetime(2024, 1, 1),
    schedule_interval=None,
    catchup=False,
) as dag:
    # Use our custom operator with disabled template rendering for command
    test_docker_task = NonTemplatedDockerOperator(
        task_id='test_docker',
        image='testnode:latest',
        working_dir='/opt/airflow/talend_jobs/CorrigeAge/CorrigeAge',
        # In your DAG file, modify the command to redirect output
        command=['bash', '-c', 'chmod +x ./CorrigeAge_run.sh && ./CorrigeAge_run.sh > /opt/airflow/talend_jobs/CorrigeAge/job_output.log 2>&1'],
        network_mode='bridge',
        docker_url='unix://var/run/docker.sock',
        mount_tmp_dir=False,
        tty=True,
    )