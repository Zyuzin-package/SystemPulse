FROM maven:3.9-eclipse-temurin-26 AS build

WORKDIR /workspace

COPY pom.xml .
COPY system-pulse-lib/pom.xml system-pulse-lib/pom.xml
COPY system-pulse-core/pom.xml system-pulse-core/pom.xml
COPY target-server/pom.xml target-server/pom.xml
COPY system-pulse-core/src system-pulse-core/src

RUN mvn -pl system-pulse-core -am package -DskipTests

FROM eclipse-temurin:26-jre

WORKDIR /app

COPY --from=build /workspace/system-pulse-core/target/system-pulse-core-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
