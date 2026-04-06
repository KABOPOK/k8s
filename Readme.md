# Kubernetes: Backend + Frontend (Spring Boot)

## Description
Backend-frontend application deploed to Kubernetes.
- Backend — Spring Boot, answer to the requests, was replicated into 3 pods
- Frontend — Spring Boot, proxies requests to backend, available outside the cluster

## Requarements
- Docker
- Minikube
- kubectl
- Java 17, Maven

## Start

1. strart minikube
minikube start --driver=docker

2.Switch to Docker inside minikube
eval $(minikube docker-env)

3. Build images
cd backend && mvn clean package -DskipTests && docker build -t backend:latest . && cd ..
cd frontend && mvn clean package -DskipTests && docker build -t frontend:latest . && cd ..

4. Deploy to the Kubernetes
kubectl apply -f backend/k8s/
kubectl apply -f frontend/k8s/

5. Check that everything have started
kubectl get pods

6.Open tunel (only for Mac)
minikube service frontend-service --url

## Testing

Base request
curl http://127.0.0.1:PORT/ (show which one pod have answered)

Check load balancing (healthy hand)
curl http://127.0.0.1:PORT/api/hello

Send heavy request to a pod (for the balancer checking)
curl http://127.0.0.1:PORT/api/heavy

kill a pod (kubectl get pods -w - You can see how k8s recreate pods)
curl http://127.0.0.1:PORT/api/kill
