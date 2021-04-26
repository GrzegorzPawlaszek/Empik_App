FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /opt/app

ARG JAR_FILE=target/empik-app.jar

COPY ${JAR_FILE} empik.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "empik.jar"]
