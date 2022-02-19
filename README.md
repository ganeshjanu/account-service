# account-service

This microservice is mainly used to both produce and consume records from a topic.

APIs

HTTP POST = /account


HTTP POST = /accountTransaction

## Docker Commands

  1. Login to docker
  
        _docker login_

  2. Setup the Kafka env in docker
  
        _docker-compose -f docker-compose.yml up -d_
  
  3. List of Containers
  
        _docker ps -a_
  
  4. List of images
      
      _docker images_
      
  5. Inside the Broker Container
    
      _docker exec -it broker /bin/sh_
      
 ## Create Topic
 
     docker exec -it <kafka_broker_container_name> /bin/sh
      
      cd /opt/kafka/bin
      
      kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 2 --topic account
    
    #no of msg in the topic
    kafka-console-consumer.sh  --from-beginning --bootstrap-server 127.0.0.1:9092 --property print.key=true  --property print.value=false --property print.partition  --topic account --timeout-ms 5000 | tail -n 10|grep "Processed a total of"


## Build & Run Service as Container

  _cd <Go_to_Dockerfile_directory>_

  _docker build -t <docker_username>/account-service:0.1 ._
  
  _docker run -p 9010:8080 <docker_username>/account-service-0.1_
  
 
      
