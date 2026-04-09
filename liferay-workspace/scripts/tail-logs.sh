#!/bin/bash
set -euo pipefail

REMOTE_USER="pi"
REMOTE_HOST="192.168.1.43"
REMOTE_LIFERAY_HOME="/home/pi/servers/liferay-dxp"

ssh "${REMOTE_USER}@${REMOTE_HOST}" \
  "tail -f ${REMOTE_LIFERAY_HOME}/tomcat/logs/catalina.out"