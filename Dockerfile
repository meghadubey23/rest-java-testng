# Use official Maven image with JDK 17 (or use 11 or 21 depending on your project)
FROM maven:3.9.6-eclipse-temurin-17

# Set working directory inside the container
WORKDIR /usr/src/app

# Copy Maven project files (only the essentials first to leverage Docker layer caching)
COPY pom.xml ./
COPY src ./src

# Run tests
CMD ["mvn", "test"]
