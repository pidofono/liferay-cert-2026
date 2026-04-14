#!/usr/bin/env bash

set -e

JDK_VERSION="${1:-21}"

SUPPORTED_JDKS=("17" "21" "11.0.18")

function contains() {
  local seeking="$1"
  shift
  for item in "$@"; do
    [[ "$item" == "$seeking" ]] && return 0
  done
  return 1
}

if ! contains "$JDK_VERSION" "${SUPPORTED_JDKS[@]}"; then
  echo "❌ JDK no soportada: $JDK_VERSION"
  echo "Usa: ${SUPPORTED_JDKS[*]}"
  exit 1
fi

JAVA_HOME_PATH=$(/usr/libexec/java_home -v "$JDK_VERSION" 2>/dev/null || true)

if [[ -z "$JAVA_HOME_PATH" ]]; then
  echo "❌ No tienes instalada la JDK $JDK_VERSION"
  echo ""
  echo "Instaladas actualmente:"
  /usr/libexec/java_home -V
  exit 1
fi

export JAVA_HOME="$JAVA_HOME_PATH"
export PATH="$JAVA_HOME/bin:$PATH"

echo "✅ Usando JDK $JDK_VERSION"
echo "JAVA_HOME=$JAVA_HOME"
java -version