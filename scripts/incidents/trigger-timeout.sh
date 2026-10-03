#!/bin/bash

# /reports/detailed в service-a виснет на 15 сек (забытый sleep),
# а у service-b timeout на чтение всего 5 сек
#
# usage: ./trigger-timeout.sh [url]

SERVICE_B_URL=${1:-http://localhost:8081}

echo "Вызов $SERVICE_B_URL/summary/detailed-report"
time curl "$SERVICE_B_URL/summary/detailed-report"
echo ""
echo "Проверьте логи service-b на timeout"