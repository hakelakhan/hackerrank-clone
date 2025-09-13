FROM openjdk:8-jdk
EXPOSE 8080
COPY target/codie.jar codie.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "codie.jar"]