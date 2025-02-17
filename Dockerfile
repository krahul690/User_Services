# Use the official OpenJDK 17 image as the base image
FROM openjdk:21

# Create a temporary volume for use in the container
VOLUME /tmp

# Expose port 8080 for external access
EXPOSE 8081

# Add the JAR file to the container with a specific name
ADD target/user-service.jar abc.jar

# Specify the command to run the JAR file
ENTRYPOINT ["java", "-jar", "abc.jar"]
