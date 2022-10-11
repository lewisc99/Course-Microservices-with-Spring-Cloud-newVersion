FROM openjdk:11-jre-slim-buster
EXPOSE 8080
ADD target/currency-exchange-service.jar currency-exchange-service.jar
ENTRYPOINT ["java","-jar","/currency-exchange-service.jar"]
