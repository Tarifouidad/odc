#!/bin/bash

# Define logging function
log() {
  echo "$(date +'%Y-%m-%d %H:%M:%S') - $1"
}

# Check for Node.js and npm
log "Checking for Node.js and npm..."
if command -v node &> /dev/null && command -v npm &> /dev/null; then
  node --version
  npm --version
  log "Node.js and npm are correctly installed"
else
  log "ERROR: Node.js and/or npm are not installed"
  # Continue despite error to see other potential problems
fi

# Define directories
TALEND_DIR="/app/talend/jobs/CorrigeAge/"
MERN_DIR="/app/mern"
SHARED_DIR="/app/shared_data"

# Create shared directories if they don't exist
log "Creating shared directories..."
mkdir -p "$SHARED_DIR" || log "Error creating $SHARED_DIR"
mkdir -p "$SHARED_DIR/output" || log "Error creating $SHARED_DIR/output"
mkdir -p "$SHARED_DIR/archive" || log "Error creating $SHARED_DIR/archive"
chmod -R 777 "$SHARED_DIR" || log "Error changing permissions for $SHARED_DIR"

# Check shared directory permissions
ls -la "$SHARED_DIR"

# Create symlinks to ensure consistent paths
log "Creating symlinks for consistent paths..."
if [ -d "/opt/airflow/talend_jobs/output" ]; then
  ln -sf "/opt/airflow/talend_jobs/output" "$SHARED_DIR/airflow_output" || log "Error creating symlink"
fi

# Wait for Airflow container to be ready
log "Waiting for Airflow to start..."
if [ -f "/scripts/wait-for-it.sh" ]; then
  chmod +x /scripts/wait-for-it.sh
  /scripts/wait-for-it.sh airflow-webserver:8080 -t 60
  WAIT_RESULT=$?
  if [ $WAIT_RESULT -ne 0 ]; then
    log "Cannot connect to Airflow, but continuing..."
  else
    log "Airflow is started and accessible"
  fi
else
  log "WARNING: wait-for-it.sh script not found, skipping Airflow wait"
fi

# Start MERN application from the correct directory
log "Starting MERN application..."
if [ -d "$MERN_DIR" ]; then
  cd "$MERN_DIR" || { log "Cannot access directory $MERN_DIR"; exit 1; }
else
  log "MERN directory $MERN_DIR does not exist"
  # Try alternative directory
  if [ -d "/app/PFE" ]; then
    log "Using /app/PFE directory instead"
    cd "/app/PFE" || { log "Cannot access directory /app/PFE"; exit 1; }
  else
    log "No valid application directory found"
    exit 1
  fi
fi

# Check for package.json
if [ ! -f "package.json" ]; then
  log "package.json not found in $(pwd)"
  log "Current directory contents:"
  ls -la
    
  # Search in parent directories
  log "Searching for package.json in other directories..."
  PACKAGE_PATH=$(find /app -name "package.json" -type f | head -n 1)
  if [ -n "$PACKAGE_PATH" ]; then
    log "Using package.json found at $PACKAGE_PATH"
    cd "$(dirname "$PACKAGE_PATH")" || { log "Cannot access directory containing package.json"; exit 1; }
  else
    log "No package.json found. Cannot start application."
    exit 1
  fi
fi

log "Current directory content ($(pwd)):"
ls -la

log "Checking package.json content:"
if [ -f "package.json" ]; then
  cat package.json | grep -E "name|scripts.*start" || log "Unexpected package.json format"
else
  log "ERROR: package.json still not found after search"
  exit 1
fi

# Install dependencies if node_modules doesn't exist
if [ ! -d "node_modules" ]; then
  log "Installing npm dependencies..."
  npm install --no-audit --no-fund || { log "Error installing dependencies"; exit 1; }
else
  log "node_modules directory already exists"
fi

# Start application
log "Running npm start in $(pwd)"
npm start