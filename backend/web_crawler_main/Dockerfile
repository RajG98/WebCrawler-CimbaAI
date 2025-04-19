FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy Spring Boot jar and Scala libs
COPY build/libs/web_crawler_main-0.0.1-SNAPSHOT.jar app.jar
COPY libs/ ./libs/

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
