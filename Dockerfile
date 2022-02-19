# For Java 8, try this
FROM openjdk:8-jdk-alpine

COPY target/account-service-0.0.1-SNAPSHOT.jar account-service.jar

ENTRYPOINT ["java","-jar","account-service.jar"]