FROM maven:3.9-eclipse-temurin-26 AS build

WORKDIR /workspace

COPY pom.xml .
COPY system-pulse-lib/pom.xml system-pulse-lib/pom.xml
COPY system-pulse-core/pom.xml system-pulse-core/pom.xml
COPY target-service/pom.xml target-service/pom.xml
COPY system-pulse-lib/src system-pulse-lib/src
COPY target-service/src target-service/src

RUN mvn -pl target-service -am package -DskipTests

FROM eclipse-temurin:26-jre

WORKDIR /app

COPY --from=build /workspace/target-service/target/target-service-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
