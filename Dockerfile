# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean install -DskipTests

# Stage 2: Run the application
FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=builder /app/target/bank_client_management.jar ./bank_client_management.jar

CMD ["java", "-jar", "bank_client_management.jar"]