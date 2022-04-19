# define base docker image
FROM openjdk:11
LABEL maintainer="henokcodes"
ADD target/atm-0.0.1-SNAPSHOT.jar atm-service.jar
ENTRYPOINT ["java", "-jar", "atm-service.jar"]
