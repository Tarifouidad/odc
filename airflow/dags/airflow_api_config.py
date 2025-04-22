# airflow/dags/airflow_api_config.py
from airflow.models import Variable

# Assurez-vous que ces variables sont disponibles pour l'API
try:
    Variable.set('api_auth_enabled', 'true')
except:
    pass  # La variable existe peut-être déjà