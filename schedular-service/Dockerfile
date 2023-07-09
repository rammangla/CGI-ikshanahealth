FROM openjdk:11
ADD ./target/schedular-service-0.0.1-SNAPSHOT.jar /usr/src/schedular-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "schedular-service-0.0.1-SNAPSHOT.jar"]
