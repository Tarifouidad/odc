from airflow import DAG
from airflow.operators.bash import BashOperator
from airflow.operators.python import PythonOperator
from datetime import datetime, timedelta
import os
import requests
import json
import shutil
import uuid

# Default arguments
default_args = {
    'owner': 'airflow',
    'depends_on_past': False,
    'email_on_failure': False,
    'email_on_retry': False,
    'retries': 1,
    'retry_delay': timedelta(minutes=5),
}

# Function to download input file
def download_input_file(**context):
    input_file_url = context['dag_run'].conf.get('input_file')
    file_id = context['dag_run'].conf.get('file_id', str(uuid.uuid4()))
    formation_id = context['dag_run'].conf.get('formation_id')
    
    if not input_file_url:
        raise ValueError("Input file URL is required")
    
    if not formation_id:
        raise ValueError("Formation ID is required")
    
    # Create directories if necessary
    input_dir = "/opt/airflow/talend_jobs/input"
    os.makedirs(input_dir, exist_ok=True)
    
    # Create unique filename based on file ID
    unique_filename = f"input_file_{file_id}.xlsx"
    local_path = f"{input_dir}/{unique_filename}"
    
    # Download file
    print(f"Downloading file from {input_file_url}")
    response = requests.get(input_file_url)
    response.raise_for_status()
    
    with open(local_path, 'wb') as f:
        f.write(response.content)
    
    print(f"File downloaded successfully: {local_path}")
    
    # Store paths and IDs for use in subsequent tasks
    context['ti'].xcom_push(key='input_file_path', value=local_path)
    context['ti'].xcom_push(key='file_id', value=file_id)
    context['ti'].xcom_push(key='formation_id', value=formation_id)
    context['ti'].xcom_push(key='unique_filename', value=unique_filename)
    
    return local_path

# Function to notify job completion
def notify_job_completion(**context):
    """Notifie l'application backend que le traitement est terminé et envoie les données pour stockage"""
    # Récupérer les données
    file_id = context['ti'].xcom_pull(key='file_id') or context['dag_run'].conf.get('file_id')
    formation_id = context['ti'].xcom_pull(key='formation_id') or context['dag_run'].conf.get('formation_id')
    
    if not file_id:
        raise ValueError("L'ID du fichier est requis")
    
    if not formation_id:
        raise ValueError("L'ID de la formation est requis")
    
    # Build file paths with the new shared directory path
    output_filename = f"output_file_{file_id}.xlsx"
    talend_output = "/opt/airflow/talend_jobs/JobETL/JobETL/outdataset.xlsx"
    shared_output = f"/shared_data/output/{output_filename}"
    
    # Check if output file exists
    talend_output_exists = os.path.exists(talend_output)
    print(f"Checking if Talend output exists at {talend_output}: {talend_output_exists}")
    
    # Check for alternative locations
    alternative_locations = [
        "/opt/airflow/talend_jobs/output/outdataset.xlsx",
        "/opt/airflow/talend_jobs/CorrigeAge/JobETL/outdataset.xlsx",
        "/opt/airflow/talend_jobs/CorrigeAge/outdataset.xlsx"
    ]
    
    actual_output_file = None
    if talend_output_exists:
        actual_output_file = talend_output
    else:
        for location in alternative_locations:
            if os.path.exists(location):
                actual_output_file = location
                print(f"Found alternative output file at: {location}")
                break
    
    status = "completed" if actual_output_file else "error"
    
    # Copy file to shared directory if processing succeeded
    if status == "completed":
        try:
            # Make directory if it doesn't exist
            os.makedirs(os.path.dirname(shared_output), exist_ok=True)
            
            # Copy file
            shutil.copy2(actual_output_file, shared_output)
            print(f"Output file copied to shared location: {shared_output}")
            
            # Set permissions
            os.chmod(shared_output, 0o777)
            print(f"Set permissions on shared file to 777")
            
            # List files in directory to confirm
            files_in_dir = os.listdir(os.path.dirname(shared_output))
            print(f"Files in shared directory: {files_in_dir}")
        except Exception as e:
            print(f"Error copying file to shared location: {str(e)}")
            status = "error"
    else:
        print(f"Error: No output file found in any known location")
    
    # Prepare notification data
    payload = {
        "status": status,
        "fileId": file_id,
        "formationId": formation_id,
        "result": {
            "outputFileUrl": f"/api/files/{file_id}/download" if status == "completed" else None,
            "outputFileName": output_filename if status == "completed" else None,
            "summary": {
                "recordsProcessed": 100,
                "fileId": file_id,
                "completedAt": datetime.now().isoformat()
            }
        }
    }
    
    if status == "error":
        payload["error"] = "Error processing file"
    
    # Send notification
    try:
        print(f"Sending notification for file {file_id}")
        
        # Send data for database storage
        process_payload = {
            "fileId": file_id,
            "formationId": formation_id,
            "outputFilePath": shared_output  # Full path to the shared file
        }
        
        db_response = requests.post(
            "http://mer_app:5000/api/beneficiaires/upload",
            json=process_payload,
            headers={"Content-Type": "application/json"}
        )
        db_response.raise_for_status()
        print(f"Data sent successfully to database: {db_response.status_code}")
        
        return f"Notification sent for file {file_id} with status {status}"
    except Exception as e:
        print(f"Error sending notification or storing data: {str(e)}")
        raise

# Create DAG
with DAG(
    'process_excel_file',
    default_args=default_args,
    description='Process Excel files with a unique ID for each file',
    schedule_interval=None,
    start_date=datetime(2025, 1, 1),
    catchup=False,
    tags=['talend', 'excel', 'beneficiaires'],
) as dag:
    
    # Task to create necessary directories
    prepare_directories = BashOperator(
        task_id='prepare_directories',
        bash_command='''
        # Create necessary directories with full permissions
        mkdir -p /opt/airflow/talend_jobs/input
        mkdir -p /opt/airflow/talend_jobs/output
        mkdir -p /opt/airflow/talend_jobs/logs
        mkdir -p /shared_data/output
        
        chmod -R 777 /opt/airflow/talend_jobs/input
        chmod -R 777 /opt/airflow/talend_jobs/output
        chmod -R 777 /opt/airflow/talend_jobs/logs
        chmod -R 777 /shared_data
        
        # Create directories in Talend container
        docker exec talend-jobs mkdir -p /jobs/input
        docker exec talend-jobs mkdir -p /jobs/output
        docker exec talend-jobs mkdir -p /shared_data/output
        docker exec talend-jobs chmod -R 777 /jobs/input /jobs/output /shared_data
        
        # Check volume mounts for debugging
        echo "Checking volume mounts..."
        df -h | grep "shared_data" || echo "No shared_data mount found in df output"
        mount | grep "shared_data" || echo "No shared_data mount found in mount output"
        
        # List contents and permissions
        echo "Shared directory contents and permissions:"
        ls -la /shared_data/
        ls -la /shared_data/output/ || echo "Output directory not found"
        
        echo "Directories prepared successfully"
        ''',
    )
    
    # Task to download input file
    download_file = PythonOperator(
        task_id='download_input_file',
        python_callable=download_input_file,
        provide_context=True,
    )
    
    # Task to copy file to Talend container
    copy_to_talend = BashOperator(
        task_id='copy_to_talend',
        bash_command='''
        # Get input file path from XCom
        INPUT_FILE="{{ ti.xcom_pull(key='input_file_path') }}"
        FILE_ID="{{ ti.xcom_pull(key='file_id') }}"
        UNIQUE_FILENAME="{{ ti.xcom_pull(key='unique_filename') }}"
        
        echo "Copying file $INPUT_FILE to Talend container"
        
        # Copy file to Talend container
        docker cp "$INPUT_FILE" talend-jobs:/jobs/input/"$UNIQUE_FILENAME"
        
        # Verify copy succeeded
        if docker exec talend-jobs ls -la /jobs/input/"$UNIQUE_FILENAME"; then
            echo "File copied successfully"
        else
            echo "ERROR: File copy failed"
            exit 1
        fi
        ''',
    )
    
    # Task to run Talend job
    run_talend_job = BashOperator(
        task_id='run_talend_job',
        bash_command='''
        # Get data from XCom 
        FILE_ID="{{ ti.xcom_pull(key='file_id') }}"
        UNIQUE_FILENAME="{{ ti.xcom_pull(key='unique_filename') }}"
        
        # Define file paths
        INPUT_FILE="/jobs/input/$UNIQUE_FILENAME"
        LOG_FILE="/opt/airflow/talend_jobs/logs/job_${FILE_ID}_$(date +%Y%m%d%H%M%S).log"
        
        echo "Running Talend job with file $INPUT_FILE"
        
        # Run Talend job
        docker exec talend-jobs bash -c "cd /jobs/JobETL/JobETL && ./JobETL_run.sh --context_param input_file='$INPUT_FILE'" > "$LOG_FILE" 2>&1
        JOB_EXIT_CODE=$?
        
        if [ $JOB_EXIT_CODE -eq 0 ]; then
            echo "Talend job completed successfully"
        else
            echo "ERROR: Talend job failed with exit code $JOB_EXIT_CODE"
            cat "$LOG_FILE"
            exit $JOB_EXIT_CODE
        fi
        ''',
    )
    
    # Task to process results
    process_results = BashOperator(
        task_id='process_results',
        bash_command='''
        # Get file ID from XCom
        FILE_ID="{{ ti.xcom_pull(key='file_id') }}"
        
        # Output file paths
        OUTPUT_FILE="/opt/airflow/talend_jobs/JobETL/JobETL/outdataset.xlsx"
        SHARED_DESTINATION="/shared_data/output/output_file_${FILE_ID}.xlsx"
        
        echo "Processing output file for file ID: $FILE_ID"
        
        # Check if output file exists
        if [ -f "$OUTPUT_FILE" ]; then
            echo "Output file found: $OUTPUT_FILE"
            
            # Create directory if it doesn't exist
            mkdir -p "$(dirname "$SHARED_DESTINATION")"
            
            # Copy file to shared volume
            cp "$OUTPUT_FILE" "$SHARED_DESTINATION"
            chmod 777 "$SHARED_DESTINATION"
            
            echo "Output file copied to shared destination: $SHARED_DESTINATION"
            
            # Debug - list files in destination directory
            echo "Files in shared directory:"
            ls -la "$(dirname "$SHARED_DESTINATION")"
            
            # Also check if the file is accessible from mer_app container
            echo "Checking file visibility from mer_app container:"
            docker exec mer_app ls -la /shared_data/output/ || echo "Cannot access shared directory from mer_app"
            
            exit 0
        else
            echo "ERROR: Output file not found at $OUTPUT_FILE"
            
            # Look for file elsewhere
            echo "Searching for output file in alternative locations..."
            FOUND_FILE=$(find /opt/airflow/talend_jobs -name "outdataset.xlsx" -type f | head -1)
            
            if [ -n "$FOUND_FILE" ]; then
                echo "File found at alternative location: $FOUND_FILE"
                
                # Create directory if it doesn't exist
                mkdir -p "$(dirname "$SHARED_DESTINATION")"
                
                # Copy to shared volume
                cp "$FOUND_FILE" "$SHARED_DESTINATION" 
                chmod 777 "$SHARED_DESTINATION"
                
                echo "File copied to shared destination: $SHARED_DESTINATION"
                
                # Debug - list files in destination directory
                echo "Files in shared directory:"
                ls -la "$(dirname "$SHARED_DESTINATION")"
                
                exit 0
            else
                echo "No output file found, checking entire talend_jobs directory..."
                find /opt/airflow/talend_jobs -type f -name "*.xlsx" | grep -v "input"
                echo "No output file found"
                exit 1
            fi
        fi
        ''',
    )
    
    # Task to notify backend application
    notify_completion = PythonOperator(
        task_id='notify_completion',
        python_callable=notify_job_completion,
        provide_context=True,
    )
    
    # Define task execution order
    prepare_directories >> download_file >> copy_to_talend >> run_talend_job >> process_results >> notify_completion