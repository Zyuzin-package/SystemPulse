FROM maven:3.9-eclipse-temurin-26 AS build

WORKDIR /workspace

COPY pom.xml .
COPY system-pulse-lib/pom.xml system-pulse-lib/pom.xml
COPY system-pulse-core/pom.xml system-pulse-core/pom.xml
COPY target-server/pom.xml target-server/pom.xml
COPY system-pulse-lib/src system-pulse-lib/src
COPY target-server/src target-server/src

RUN mvn -pl target-server -am package -DskipTests

FROM eclipse-temurin:26-jre

WORKDIR /app

COPY --from=build /workspace/target-server/target/target-server-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
