FROM openjdk:11
ADD ./target/book-appointment-service-0.0.1-SNAPSHOT.jar /usr/src/book-appointment-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "book-appointment-service-0.0.1-SNAPSHOT.jar"]
