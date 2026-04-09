#!/bin/bash
set -euo pipefail

if [ $# -ne 1 ]; then
  echo "Uso: $0 {dev|pre|prod}"
  exit 1
fi

ENV_NAME="$1"
REMOTE_USER="pi"
REMOTE_HOST="192.168.1.43"
REMOTE_LIFERAY_HOME="/home/pi/servers/liferay-dxp"

if [ ! -d "configs/${ENV_NAME}" ]; then
  echo "No existe el entorno configs/${ENV_NAME}"
  exit 1
fi

echo "==> Subiendo configs common"
scp -r configs/common/* "${REMOTE_USER}@${REMOTE_HOST}:${REMOTE_LIFERAY_HOME}/"

echo "==> Subiendo configs ${ENV_NAME}"
scp -r configs/${ENV_NAME}/* "${REMOTE_USER}@${REMOTE_HOST}:${REMOTE_LIFERAY_HOME}/"

echo "==> Configuración ${ENV_NAME} desplegada"