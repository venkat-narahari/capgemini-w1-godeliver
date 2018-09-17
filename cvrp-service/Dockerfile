# pull image of jdk

FROM java:8-jre

# copy jar file to docker file system

ADD ./target/cvrp-service-0.0.1-SNAPSHOT.jar /usr/app/cvrp-service-0.0.1-SNAPSHOT.jar

# java -jar <jar fileName>

EXPOSE 8084

WORKDIR usr/app

ENTRYPOINT ["java","-jar", "cvrp-service-0.0.1-SNAPSHOT.jar"]
