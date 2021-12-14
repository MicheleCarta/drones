FROM openjdk:11
VOLUME /tmp

ADD  /target/drone.jar drone.jar
EXPOSE 8003 8003
ENTRYPOINT ["java","-Xmx256m", "-jar","/drone.jar"]
