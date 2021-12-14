FROM openjdk:11
VOLUME /tmp


ADD  /target/dispatcher.jar dispatcher.jar
EXPOSE 8001 8001
ENTRYPOINT ["java","-Xmx256m", "-jar","/dispatcher.jar"]
