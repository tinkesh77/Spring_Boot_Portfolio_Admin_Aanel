FROM maven:3.4.2-openjdk-17 AS buid
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/admin-0.0.1-SNAPSHOT.jar admin.jar
EXPOSE 8080
ENTRYPOINT["java" , "-jar" , "admin.jar"]