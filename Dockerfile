# Use a lightweight Java image with JDK 17
FROM openjdk:17-jdk-slim

# Set a working directory inside the container
WORKDIR /app

# Copy the JAR file built by Maven
COPY target/FurTrack-0.0.1-SNAPSHOT.jar furtrack.jar

# Expose port 8083
EXPOSE 8083

# Run the application
ENTRYPOINT ["java", "-jar", "furtrack.jar"]