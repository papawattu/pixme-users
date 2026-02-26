# syntax=docker/dockerfile:1.4

# Build stage: use Maven with JDK 25
FROM maven:3.9-eclipse-temurin-25-alpine AS build

WORKDIR /app

# Copy pom.xml and .mvn config first for dependency caching
COPY pom.xml ./
COPY .mvn ./.mvn

# Download dependencies (separate layer for caching)
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src

# Build the Spring Boot fat JAR.
# Run compile + jar + spring-boot:repackage directly to avoid
# the native-maven-plugin execution bound to the 'package' phase
# (it requires GraalVM which is not available in this image).
RUN mvn compile jar:jar spring-boot:repackage -B -DskipTests

# Runtime stage: slim JDK 25 image
FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Launch with --enable-preview and --enable-native-access flags
ENTRYPOINT ["java", "--enable-preview", "--enable-native-access=ALL-UNNAMED", "-jar", "app.jar"]
