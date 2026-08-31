# Stage 1: Build — dùng Maven + JDK đầy đủ để compile ra file .jar
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
# Copy pom.xml trước để tận dụng Docker layer cache cho dependency
COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn -B clean package -DskipTests

# Stage 2: Run — chỉ lấy JRE nhẹ (~200MB) thay vì JDK đầy đủ (~800MB)
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
