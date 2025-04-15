#!/bin/bash
# Script d'installation des dépendances pour Airflow
# Ce script installe les bibliothèques nécessaires pour traiter les fichiers Excel dans Airflow

set -e  # Arrêter en cas d'erreur

# Couleurs pour la sortie
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Fonction pour les logs
log() {
    echo -e "${GREEN}[$(date +'%Y-%m-%d %H:%M:%S')] $1${NC}"
}

error() {
    echo -e "${RED}[$(date +'%Y-%m-%d %H:%M:%S')] ERREUR: $1${NC}"
}

warning() {
    echo -e "${YELLOW}[$(date +'%Y-%m-%d %H:%M:%S')] ATTENTION: $1${NC}"
}

# Vérifier que Docker est installé
if ! command -v docker &> /dev/null; then
    error "Docker n'est pas installé. Veuillez l'installer avant de continuer."
    exit 1
fi

# Vérifier que les conteneurs Airflow sont en cours d'exécution
log "Vérification des conteneurs Airflow..."
if ! docker ps | grep -q "airflow-webserver"; then
    warning "Le conteneur airflow-webserver n'est pas en cours d'exécution. Tentative de démarrage..."
    docker start airflow-webserver || {
        error "Impossible de démarrer airflow-webserver"
        exit 1
    }
fi

if ! docker ps | grep -q "airflow-scheduler"; then
    warning "Le conteneur airflow-scheduler n'est pas en cours d'exécution. Tentative de démarrage..."
    docker start airflow-scheduler || {
        error "Impossible de démarrer airflow-scheduler"
        exit 1
    }
fi

# Installer les dépendances dans airflow-webserver
log "Installation des dépendances dans airflow-webserver..."
docker exec -u 0 airflow-webserver bash -c "
    apt-get update && 
    apt-get install -y --no-install-recommends gcc python3-dev && 
    pip install --no-cache-dir pandas openpyxl xlrd pyarrow && 
    apt-get clean && 
    rm -rf /var/lib/apt/lists/*
" || {
    error "Échec de l'installation des dépendances dans airflow-webserver"
    exit 1
}

# Installer les dépendances dans airflow-scheduler
log "Installation des dépendances dans airflow-scheduler..."
docker exec -u 0 airflow-scheduler bash -c "
    apt-get update && 
    apt-get install -y --no-install-recommends gcc python3-dev && 
    pip install --no-cache-dir pandas openpyxl xlrd pyarrow && 
    apt-get clean && 
    rm -rf /var/lib/apt/lists/*
" || {
    error "Échec de l'installation des dépendances dans airflow-scheduler"
    exit 1
}

# Vérifier l'installation
log "Vérification de l'installation des packages..."
docker exec airflow-webserver pip list | grep -E 'pandas|openpyxl|xlrd|pyarrow' || {
    warning "Certains packages pourraient ne pas avoir été installés correctement dans airflow-webserver"
}

docker exec airflow-scheduler pip list | grep -E 'pandas|openpyxl|xlrd|pyarrow' || {
    warning "Certains packages pourraient ne pas avoir été installés correctement dans airflow-scheduler"
}

# Créer un DAG de test pour vérifier l'installation
log "Création d'un DAG de test pour vérifier l'installation..."
TEST_DAG=$(cat <<'EOL'
from airflow import DAG
from airflow.operators.python import PythonOperator
from datetime import datetime, timedelta

def test_excel_deps():
    """Fonction pour tester les dépendances Excel"""
    import sys
    packages = ['pandas', 'openpyxl', 'xlrd', 'pyarrow']
    
    results = {}
    for package in packages:
        try:
            module = __import__(package)
            results[package] = f"✅ {package} {getattr(module, '__version__', 'version inconnue')}"
        except ImportError:
            results[package] = f"❌ {package} non installé"
    
    print("=== RÉSULTATS DU TEST DES DÉPENDANCES ===")
    for package, status in results.items():
        print(status)
    print("=========================================")
    
    return all("✅" in status for status in results.values())

default_args = {
    'owner': 'airflow',
    'depends_on_past': False,
    'email_on_failure': False,
    'email_on_retry': False,
    'retries': 1,
    'retry_delay': timedelta(minutes=1),
}

with DAG(
    'test_excel_dependencies',
    default_args=default_args,
    description='DAG pour tester les dépendances Excel',
    schedule_interval=None,
    start_date=datetime(2024, 1, 1),
    catchup=False,
    tags=['test'],
) as dag:
    
    test_task = PythonOperator(
        task_id='test_excel_dependencies',
        python_callable=test_excel_deps,
    )
EOL
)

# Écrire le DAG de test dans le répertoire des DAGs
docker exec -u airflow airflow-webserver bash -c "cat > /opt/airflow/dags/test_excel_deps.py << 'EOL'
$TEST_DAG
EOL"

log "Redémarrage des conteneurs Airflow pour appliquer les changements..."
docker restart airflow-webserver airflow-scheduler

log "Attente du démarrage complet de Airflow..."
sleep 10

log "Installation terminée avec succès!"
log "Vous pouvez maintenant exécuter le DAG 'test_excel_dependencies' depuis l'interface Airflow pour vérifier que tout fonctionne correctement."
log "URL de l'interface Airflow: http://localhost:8080"