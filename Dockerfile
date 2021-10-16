FROM maven:3.6.0-jdk-11-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean -Dmaven.test.skip package

FROM openjdk:11-jdk-slim
COPY --from=build /usr/src/app/target/spring-db2-0.0.1-SNAPSHOT.jar /usr/app/app.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]