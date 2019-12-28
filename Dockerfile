FROM maven:3-jdk-8-slim

ADD core /app

WORKDIR /apps

EXPOSE 6790
EXPOSE 6789

CMD java -jar target/app.jar