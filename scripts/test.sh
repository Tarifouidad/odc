# scripts/test_airflow_api.sh
#!/bin/bash

echo "Testing Airflow API..."
curl -X GET \
  --user "admin:admin" \
  http://localhost:8080/api/v1/dags

echo -e "\n\nTesting DAG trigger..."
curl -X POST \
  --user "admin:admin" \
  -H "Content-Type: application/json" \
  -d '{"dag_run_id":"test_run_'$(date +%s)'", "conf":{}}' \
  http://localhost:8080/api/v1/dags/talend_job_execution/dagRuns