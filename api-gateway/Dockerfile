FROM openjdk:11

RUN apt-get update -y
RUN apt-get upgrade -y
RUN apt-get install -y maven

COPY ./ ./
RUN mvn clean package

CMD ["java","-jar","target/api-gateway.jar"]