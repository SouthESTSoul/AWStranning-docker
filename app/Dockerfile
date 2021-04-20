#FROM  java:8
#COPY target/dockercompose-springboot-mysql-nginx-1.0.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM maven:3.5-jdk-8
#COPY target/dockercompose-springboot-mysql-nginx-1.0.jar app.jar
#ENTRYPOINT ["java","-Dspring-boot.run.profiles=docker","-jar","/app.jar"]
COPY . /build
WORKDIR /build
ENTRYPOINT ["mvn","clean","-Dspring-boot:run"]
