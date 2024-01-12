FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD /build/libs/alocador-ativos-api.jar /opt/api.jar
ENTRYPOINT exec java -jar /opt/api.jar
