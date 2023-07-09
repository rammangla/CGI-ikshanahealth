FROM openjdk:11
ADD ./target/doctor-service-spring-boot.jar /usr/src/doctor-service-spring-boot.jar
#EXPOSE 9087
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "doctor-service-spring-boot.jar"]
