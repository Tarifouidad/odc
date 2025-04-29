from airflow import DAG
from airflow.operators.bash import BashOperator
from datetime import datetime, timedelta

with DAG(
    dag_id='import_beneficiaires',
    start_date=datetime(2024, 1, 1),
    schedule_interval=None,
    catchup=False,
    tags=['talend', 'beneficiaires'],
) as dag:
    # Préparation des répertoires et des permissions
    prepare_directories = BashOperator(
        task_id='prepare_directories',
        bash_command='''
        # Créer les répertoires nécessaires
        mkdir -p /opt/airflow/talend_jobs/input
        mkdir -p /opt/airflow/talend_jobs/output
        
        # S'assurer que le conteneur talend-jobs a les bons répertoires
        docker exec talend-jobs bash -c "mkdir -p /jobs/input"
        
        echo "Répertoires préparés"
        ''',
    )

    # Téléchargement du fichier d'entrée
    download_file = BashOperator(
        task_id='download_input_file',
        bash_command='''
        # Récupérer l'ID du fichier pour créer un nom unique
        FILE_ID="{{ dag_run.conf['file_id'] }}"
        INPUT_FILE="/opt/airflow/talend_jobs/input/input_file_${FILE_ID}.xlsx"
        
        # Télécharger le fichier depuis l'URL fournie
        echo "Téléchargement du fichier depuis {{ dag_run.conf['input_file'] }}"
        curl -s "{{ dag_run.conf['input_file'] }}" -o "$INPUT_FILE"
        
        # Vérifier que le téléchargement a réussi
        if [ ! -f "$INPUT_FILE" ]; then
            echo "Échec du téléchargement du fichier"
            exit 1
        fi
        
        # Copier le fichier dans le conteneur talend-jobs
        docker cp "$INPUT_FILE" talend-jobs:/jobs/input/input_file.xlsx
        
        # Vérifier que la copie a fonctionné
        echo "Vérification du fichier dans le conteneur:"
        docker exec talend-jobs ls -la /jobs/input/
        ''',
    )

    # Exécution du job Talend avec le contournement
    run_talend_job = BashOperator(
        task_id='run_talend_job',
        bash_command='''
        # Copier le fichier qui fonctionne directement
        docker cp /opt/airflow/talend_jobs/input/input_file.xlsx talend-jobs:/jobs/jobETL/jobETL/context.input_file
        
        # Assurer les bonnes permissions
        docker exec talend-jobs chmod 777 /jobs/jobETL/jobETL/context.input_file
        
        # Exécuter le job avec redirection des sorties vers des fichiers
        docker exec -it talend-jobs bash -c "cd /jobs/jobETL/jobETL && ./jobETL_run.sh" > /opt/airflow/logs/talend_stdout.log 2> /opt/airflow/logs/talend_stderr.log
        
        # Afficher les logs pour le débogage
        echo "=== STDOUT du job Talend ==="
        cat /opt/airflow/logs/talend_stdout.log
        
        echo "=== STDERR du job Talend ==="
        cat /opt/airflow/logs/talend_stderr.log
        
        # Vérifier immédiatement le résultat
        docker exec talend-jobs ls -la /jobs/jobETL/jobETL/outdataset.xlsx
        ''',
    )

    # Vérification et récupération des résultats
    check_results = BashOperator(
        task_id='check_results',
        bash_command='''
            # Récupérer l'ID du fichier
            FILE_ID="{{ dag_run.conf['file_id'] }}"
            OUTPUT_DIR="/opt/airflow/talend_jobs/output/$FILE_ID"
            
            # Créer le répertoire de sortie s'il n'existe pas
            mkdir -p "$OUTPUT_DIR"
            
            # Vérifier l'existence du fichier de sortie dans le conteneur
            echo "Vérification des fichiers de sortie..."
            docker exec talend-jobs ls -la /jobs/jobETL/jobETL/
            
            # Copier le fichier de sortie s'il existe
            if docker exec talend-jobs test -f "/jobs/jobETL/jobETL/outdataset.xlsx"; then
                echo "Fichier outdataset.xlsx trouvé dans le conteneur"
                docker cp talend-jobs:/jobs/jobETL/jobETL/outdataset.xlsx "$OUTPUT_DIR/outdataset_$FILE_ID.xlsx"
                echo "Fichier copié vers $OUTPUT_DIR/outdataset_$FILE_ID.xlsx"
                
                # Enregistrer le chemin du fichier pour l'étape suivante
                echo "$OUTPUT_DIR/outdataset_$FILE_ID.xlsx" > /tmp/output_file_path.txt
                echo "true" > /tmp/job_success.txt
                exit 0
            else
                echo "Aucun fichier de sortie trouvé"
                echo "false" > /tmp/job_success.txt
                exit 1
            fi
        ''',
    )

    # Notification des résultats
    notify_results = BashOperator(
        task_id='notify_results',
        bash_command='''
        # Récupérer l'ID du fichier
        FILE_ID="{{ dag_run.conf['file_id'] }}"
        
        # Vérifier si le job a réussi
        if [ -f /tmp/job_success.txt ] && [ "$(cat /tmp/job_success.txt)" == "true" ]; then
            STATUS="completed"
            OUTPUT_FILE=$(cat /tmp/output_file_path.txt 2>/dev/null || echo "")
            RESULT="{\\\"outputFileUrl\\\":\\\"$OUTPUT_FILE\\\",\\\"summary\\\":\\\"Traitement réussi\\\"}"
            echo "Le traitement a réussi. Fichier de sortie: $OUTPUT_FILE"
        else
            STATUS="error"
            ERROR=$(cat /tmp/error_message.txt 2>/dev/null || echo "Erreur inconnue")
            ERROR=$(echo "$ERROR" | sed 's/"/\\\\"/g')
            RESULT="{\\\"error\\\":\\\"$ERROR\\\"}"
            echo "Le traitement a échoué: $ERROR"
        fi
        
        # Construction du payload JSON
        PAYLOAD="{\\\"status\\\":\\\"$STATUS\\\",\\\"result\\\":$RESULT}"
        echo "Payload: $PAYLOAD"
        
        # Si une API est configurée, envoyer la mise à jour
        if [ -n "{{ dag_run.conf.get('callback_url', '') }}" ]; then
            echo "Envoi de la notification à {{ dag_run.conf.get('callback_url', '') }}"
            curl -X PATCH -H "Content-Type: application/json" -d "$PAYLOAD" "{{ dag_run.conf.get('callback_url', '') }}" || echo "Échec de l'envoi à l'API"
        else
            echo "Aucune URL de callback configurée"
        fi
        
        echo "Traitement terminé avec statut: $STATUS"
        ''',
    )

    # Définir les dépendances
    prepare_directories >> download_file >> run_talend_job >> check_results >> notify_results