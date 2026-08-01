# Stage 1: Build stage
FROM maven:3-eclipse-temurin-26-alpine AS build
WORKDIR /app

# 1. Copy the root POM and all module POMs first to cache dependencies
COPY pom.xml .
COPY common-lib/pom.xml common-lib/
COPY auth-service/pom.xml auth-service/
COPY budget-service/pom.xml budget-service/
COPY email-service/pom.xml email-service/
COPY frontend-service/pom.xml frontend-service/
COPY transaction-service/pom.xml transaction-service/
COPY user-service/pom.xml user-service/

# 2. Go offline to download dependencies
RUN mvn dependency:go-offline -B

# 3. Copy source code for the library and the specific service
COPY common-lib/src ./common-lib/src
COPY user-service/src ./user-service/src

# 4. Build the common library and the user-service
RUN mvn clean install -DskipTests -pl common-lib,user-service -am

# Stage 2: Runtime stage
FROM eclipse-temurin:26-jre-alpine
WORKDIR /app

# Install curl for healthchecks (Alpine uses apk)
RUN apk add --no-cache curl

# Copy the built jar from the user-service target folder
COPY --from=build /app/user-service/target/*.jar app.jar

EXPOSE 8504
ENTRYPOINT ["java", "-jar", "app.jar"]