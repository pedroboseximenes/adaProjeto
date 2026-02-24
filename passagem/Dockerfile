FROM maven:3.9.12-eclipse-temurin-25-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/passagem-1.0.0.jar /app/passagem/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/passagem/app.jar"]