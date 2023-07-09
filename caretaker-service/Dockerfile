FROM openjdk:11
ADD ./target/caretaker-service-0.0.1-SNAPSHOT.jar /usr/src/caretaker-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "caretaker-service-0.0.1-SNAPSHOT.jar"]
