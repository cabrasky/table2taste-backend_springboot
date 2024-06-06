FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY --from=builder /app/target/myapp.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
