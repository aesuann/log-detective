#!/bin/bash

# Вытаскивает error/warn строки из логов подов по имени сервиса
#
# usage: ./collect-logs.sh [service-a|service-b] [кол-во последних строк]

SERVICE=${1:-service-a}
TAIL_LINES=${2:-200}

echo "Логи $SERVICE, последние $TAIL_LINES строк, фильтр ERROR/WARN:"
echo "---"

kubectl logs -l app="$SERVICE" --tail="$TAIL_LINES" | grep -E "ERROR|WARN"