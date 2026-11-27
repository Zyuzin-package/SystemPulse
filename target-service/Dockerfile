FROM maven:3.9-eclipse-temurin-26 AS build

WORKDIR /workspace

COPY . .

RUN mvn -pl target-service -am package -DskipTests

FROM eclipse-temurin:26-jre

WORKDIR /app

COPY --from=build /workspace/target-service/target/target-service-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
