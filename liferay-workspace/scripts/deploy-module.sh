#!/bin/bash
set -euo pipefail

if [ $# -ne 1 ]; then
  echo "Uso: $0 nombre-modulo"
  exit 1
fi

MODULE_NAME="$1"
REMOTE_USER="pi"
REMOTE_HOST="192.168.1.43"
REMOTE_LIFERAY_HOME="/home/pi/servers/liferay-dxp"

echo "==> Build de ${MODULE_NAME}"
./gradlew ":modules:${MODULE_NAME}:build"

echo "==> Subiendo JAR de ${MODULE_NAME}"
scp modules/${MODULE_NAME}/build/libs/*.jar \
  "${REMOTE_USER}@${REMOTE_HOST}:${REMOTE_LIFERAY_HOME}/osgi/modules/"

echo "==> Módulo desplegado"