FROM openjdk:17-jdk-alpine

ADD target/*.jar /app.jar

EXPOSE 8091

ENTRYPOINT ["java","-jar","/app.jar"]
