#!/bin/bash
set -euo pipefail

REMOTE_USER="pi"
REMOTE_HOST="192.168.1.43"
#REMOTE_HOST="100.75.215.57"
REMOTE_LIFERAY_HOME="/home/pi/servers/liferay-dxp"

echo "==> Compilando workspace"
./gradlew clean build

echo "==> Subiendo módulos OSGi al servidor ${REMOTE_HOST}"
find modules -path "*/build/libs/*.jar" -type f ! -name "*-sources.jar" ! -name "*-javadoc.jar" -print0 \
| while IFS= read -r -d '' jar; do
  echo "   -> $(basename "$jar")"
  scp "$jar" "${REMOTE_USER}@${REMOTE_HOST}:${REMOTE_LIFERAY_HOME}/osgi/modules/"
done

echo "==> Despliegue remoto completado"