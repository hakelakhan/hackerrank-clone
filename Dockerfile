FROM java:8
EXPOSE 8080:8080
ADD /target/codie.jar codie.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "codie.jar"]