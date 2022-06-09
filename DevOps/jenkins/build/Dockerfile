FROM openjdk:8-jre-alpine

RUN mkdir /app

COPY *.jar /app/backend.jar

ARG IP
ARG JWT_SECRET

ENV SERVERIP=$SERVER_IP
ENV JWT_SECRET=$JWT_SECRET

CMD java -jar /app/backend.jar