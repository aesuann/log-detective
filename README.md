# log-detective
Тренажёр для практики диагностики инцидентов по логам в Kubernetes

Два Java-сервиса с намеренно заложенными проблемами — утечкой памяти и
таймаутом между сервисами. Задача найти причину инцидентов по логам.

### Стек

Java 17, Spring Boot, Docker, Kubernetes (развёрнуто на minikube), Jenkins,
JUnit.

### Быстрый запуск

Через docker-compose, без развёртывания кластера:

```
docker-compose up --build
```

Service A поднимается на порту 8080, Service B — на 8081.

### Запуск в Kubernetes

Требуются minikube и kubectl.

```
minikube start
eval $(minikube docker-env)
cd services/service-a/servicea && docker build -t service-a:latest .
cd ../../service-b/serviceb && docker build -t service-b:latest .
cd ../../..
kubectl apply -f k8s/
```

### Воспроизведение инцидентов

В `scripts/incidents/` находятся скрипты для воспроизведения заложенных
проблем:

- `trigger-oom.sh` — создаёт нагрузку на service-a, пока не закончится память
- `trigger-timeout.sh` — вызывает эндпоинт, время ответа которого превышает установленный таймаут

Для анализа логов: `scripts/collect-logs.sh service-a` (или service-b).

### CI

Jenkinsfile в корне репозитория собирает оба сервиса, прогоняет тесты. Деплой в Kubernetes выполняется отдельным шагом, так как на ARM-based хостах доступ контейнера Jenkins к
minikube требует дополнительной настройки.