#!/bin/bash

# Кэш отчётов в service-a не чистится, так что после
# пачки запросов память кончается и падает с OOM
#
# usage: ./trigger-oom.sh [кол-во запросов] [url]

REQUEST_COUNT=${1:-50}
SERVICE_URL=${2:-http://localhost:8080}

echo "Отправка $REQUEST_COUNT запросов на $SERVICE_URL/reports/generate..."

for i in $(seq 1 "$REQUEST_COUNT"); do
    curl -s -X POST "$SERVICE_URL/reports/generate" -o /dev/null -w "запрос %i: HTTP %{http_code}\n" -i "$i"
done

echo "Проверьте логи service-a на OutOfMemoryError"