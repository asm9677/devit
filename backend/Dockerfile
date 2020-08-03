FROM openjdk:8-jdk-alpine


VOLUME ["/data", "/var/libs"]

COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]