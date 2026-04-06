# Kubernetes: Backend + Frontend (Spring Boot)

## Описание
Backend-frontend приложение задеплоенное в Kubernetes.
- Backend — Spring Boot, отвечает на запросы, реплицирован в 3 пода
- Frontend — Spring Boot, проксирует запросы на backend, доступен снаружи кластера

## Требования
- Docker
- Minikube
- kubectl
- Java 17, Maven

## Запуск

1. Запустить minikube
minikube start --driver=docker

2. Переключиться на Docker внутри minikube
eval $(minikube docker-env)

3. Собрать образы
cd backend && mvn clean package -DskipTests && docker build -t backend:latest . && cd ..
cd frontend && mvn clean package -DskipTests && docker build -t frontend:latest . && cd ..

4. Задеплоить в Kubernetes
kubectl apply -f backend/k8s/
kubectl apply -f frontend/k8s/

5. Проверить что всё запущено
kubectl get pods

6. Открыть туннель (только для Mac)
minikube service frontend-service --url

## Тестирование

Базовый запрос
curl http://127.0.0.1:PORT/ (покажет какой под отвечает)

Проверка балансировки (healthy ручка)
curl http://127.0.0.1:PORT/api/hello

Нагрузить под (для проверки балансировка)
curl http://127.0.0.1:PORT/api/heavy

Убить под (kubectl get pods -w - можно увидеть как кубернетис перезапускает сама поды)
curl http://127.0.0.1:PORT/api/kill
