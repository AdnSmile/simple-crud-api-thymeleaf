FROM openjdk:17.0.1-jdk

COPY target/technical-test-0.0.1-SNAPSHOT.jar /app/application.jar

CMD ["java", "-jar", "/app/application.jar"]