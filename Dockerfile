FROM openjdk:17-jdk-alpine

COPY target/encurtador-0.0.1-SNAPSHOT.jar encurtador.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/encurtador.jar"]
