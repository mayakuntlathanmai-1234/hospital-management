# Stage 1: Build the application
FROM maven:3.8.3-openjdk-11-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 7860
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-Dserver.port=7860", "-Xmx512m", "-jar", "app.jar"]
