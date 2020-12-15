# A5-distributed-systems

MOOC platform: Microservices 

To deploy application on your host, go to k8s folder and do comands for files there like (account-service example):
```bash
kubectl create -f account-config.yaml,account-service.yaml,postgresqldb-deployment.yaml, \
    account-deployment.yaml,postgres-data-persistentvolumeclaim.yaml,postgresqldb-service.yaml
```
Also, you should specify your folder for database mountPath in postgres-deployment.yaml files.

To check if it works correctly, please execute this command in terminal to forward port from mediator pod 
(ports are same, name of pod you should get by command "kubectl get pods")
```bash
kubectl port-forward mediator-79dcd7b49f-4lr7j 8080:8080 10000:10000
```
Also, port forward RabbitMQ pod to your localhost:
```bash
kubectl port-forward rabbitmq-5488d756f4-5ls5s 5672:5672 15672:15672 
```
Then, start the client application, and it will run correctly.
