# Step 1: Build stage
FROM maven:3.8.6-openjdk-17-slim AS build

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run stage
FROM openjdk:17.0.1-jdk-slim

# Copy the jar from the build stage
COPY --from=build /app/target/admin-0.0.1-SNAPSHOT.jar admin.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "admin.jar"]
