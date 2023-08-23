FROM openjdk:11
EXPOSE 8083
ADD target/second-service-docker.jar second-service-docker.jar
ENTRYPOINT ["java","-jar","/second-service-docker.jar"]