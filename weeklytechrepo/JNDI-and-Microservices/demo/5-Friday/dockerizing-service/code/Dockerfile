# Use official OpenJDK Alpine base image for a tiny footprint
FROM openjdk:17-jdk-alpine

# Set authorship metadata
LABEL maintainer="instructor@revature.com"

# Expose the port the Spring Boot app will listen on
EXPOSE 8080

# The JAR file path is passed as an argument, defaults to target/*.jar
ARG JAR_FILE=target/*.jar

# Copy the compiled JAR file from the host machine into the container's root directory
COPY ${JAR_FILE} app.jar

# Define the command to execute when the container starts
ENTRYPOINT ["java", "-jar", "/app.jar"]
