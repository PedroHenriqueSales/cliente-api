FROM openjdk:11

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}

WORKDIR /opt/crud

COPY target/crud*.jar crud.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 5885
EXPOSE 8080

CMD java -jar crud.jar --spring.profiles.active=${PROFILE}