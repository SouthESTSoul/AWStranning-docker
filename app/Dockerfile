#FROM  java:8
#COPY target/dockercompose-springboot-mysql-nginx-1.0.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

#FROM maven:3.5-jdk-8
#COPY target/dockercompose-springboot-mysql-nginx-1.0.jar app.jar
#ENTRYPOINT ["java","-Dspring-boot.run.profiles=docker","-jar","/app.jar"]
#COPY . /build
#WORKDIR /build
#ENTRYPOINT ["mvn","clean","-Dspring-boot:run"]



FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/dockercompose-springboot-mysql-nginx-1.0.jar /app/
ENTRYPOINT ["java", "-jar", "dockercompose-springboot-mysql-nginx-1.0.jar"]
