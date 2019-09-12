FROM maven:3.6.1-jdk-12 AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package

FROM openjdk:12-jdk-alpine
LABEL maintener="christopher.perry@employbridge.com"

COPY --from=MAVEN_TOOL_CHAIN /tmp/target/frontend-api*.jar /home/app/app.jar
WORKDIR /home/app
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/home/app/app.jar"]