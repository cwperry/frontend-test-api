FROM openjdk:12-jdk-alpine
LABEL maintener="christopher.perry@employbridge.com"

WORKDIR /home/app
ADD /target/frontend-api*.jar /home/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/home/app/app.jar"]
