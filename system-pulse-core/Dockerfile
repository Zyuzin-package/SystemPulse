FROM maven:3.9-eclipse-temurin-26 AS build

WORKDIR /workspace

COPY . .

RUN mvn -pl system-pulse-core -am package -DskipTests

FROM eclipse-temurin:26-jre

WORKDIR /app

COPY --from=build /workspace/system-pulse-core/target/system-pulse-core-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
