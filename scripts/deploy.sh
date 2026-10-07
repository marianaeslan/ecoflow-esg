#!/usr/bin/env bash
set -euo pipefail

# scripts/deploy.sh <arquivo-compose> <porta-api>
# Variáveis exigidas no ambiente: IMAGE_NAME, IMAGE_TAG, DB_USER, DB_PASSWORD,
# ORACLE_SYS_PASSWORD, JWT_SECRET

if [ "$#" -ne 2 ]; then
  echo "Uso: $0 <arquivo-compose> <porta-api>"
  exit 2
fi

COMPOSE_FILE="$1"
API_PORT="$2"

echo "Pull da imagem 'api' definida no compose (${COMPOSE_FILE})..."
docker compose -f "$COMPOSE_FILE" pull api

echo "Subindo serviços... (API porta: ${API_PORT})"
API_PORT="$API_PORT" docker compose -f "$COMPOSE_FILE" up -d --remove-orphans

echo "Aguardando health endpoint da API (http://localhost:${API_PORT}/actuator/health)"
TRY=0
MAX=30
until curl -fs "http://localhost:${API_PORT}/actuator/health" >/dev/null 2>&1; do
  TRY=$((TRY+1))
  if [ "$TRY" -ge "$MAX" ]; then
    echo "SERVIÇO NÃO SAUDÁVEL: mostrando últimos logs da API"
    docker compose -f "$COMPOSE_FILE" logs --tail=50 api || true
    exit 1
  fi
  echo "Tentativa ${TRY}/${MAX}: ainda não saudável, esperando 10s..."
  sleep 10
done

echo "API saudável! Limpando imagens intermediárias..."
docker image prune -f || true

echo "Deploy finalizado com sucesso"
exit 0

