# Stage 1: Build the jar
FROM maven:3.9.2-eclipse-temurin-8 AS build
WORKDIR /app

# Copy only pom.xml first to leverage Docker cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the jar (skip tests for faster build)
RUN mvn clean package -DskipTests

# Stage 2: Runtime (slim image)
FROM eclipse-temurin:8-jre-jammy
WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/codie.jar codie.jar

# Expose port
EXPOSE 8080

# Run Spring Boot
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-Xmx256m", "-jar", "codie.jar"]
