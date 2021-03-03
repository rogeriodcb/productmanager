FROM openjdk:13

EXPOSE 8080

RUN mkdir -p /opt/application/jar
WORKDIR /opt/application/jar
COPY ./Back-End-Server/target/productmanager-0.0.1-SNAPSHOT.jar /opt/application/jar/back-end-server.jar

ENTRYPOINT [ "java", \ 
    "-jar", \ 
    "/opt/application/jar/back-end-server.jar" ]