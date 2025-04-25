java -Dtalend.component.manager.m2.repository="$ROOT_PATH/lib" \
     -Xms256M \
     -Xmx1024M \
     -cp "$ROOT_PATH":"$ROOT_PATH/lib/routines.jar":"$ROOT_PATH/lib/*" \
     local_project.jobetl_0_1.JobETL --context=Default --context_param input_file="$INPUT_FILE"
EXIT_CODE=$?