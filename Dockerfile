FROM maven:3.6.3-jdk-8 AS build


# Setting the working directory
WORKDIR /app

COPY pom.xml ./
COPY src ./src

# Build the application (skipping tests)
RUN mvn clean package

# Use an official Java 8 runtime as the base image
FROM openjdk:8-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/gestion-station-ski-1.0.jar app.jar


# Expose the port your app runs on (default is 8080)
EXPOSE 8082


# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
