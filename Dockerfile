# Possui dois estágios de build
# Imagem do maven para fazero build inicial (gerar o jar)
FROM maven:3.6.0-jdk-11-slim AS primeiro-build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Imagem do jdk para executar o jar
FROM openjdk:11-jre-slim
COPY --from=primeiro-build /home/app/target/websocket-0.0.1-SNAPSHOT.jar /usr/local/lib/websocket.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/websocket.jar"]
