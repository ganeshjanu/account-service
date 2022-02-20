# For Java 8, try this
FROM openjdk:8-jdk-alpine

COPY target/account-service-producer-0.0.1-SNAPSHOT.jar account-service-producer.jar

ENTRYPOINT ["java","-jar","account-service-producer.jar"]