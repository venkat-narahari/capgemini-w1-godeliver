# pull image of jdk
FROM java:8-jre

# copy jar file to docker file system
ADD ./target/cvrp-service-0.0.1-SNAPSHOT.jar /usr/app/cvrp-service-0.0.1-SNAPSHOT.jar

EXPOSE 9078

# java -jar <jar file name>
WORKDIR usr/app
ENTRYPOINT ["java", "-jar", "cvrp-service-0.0.1-SNAPSHOT.jar"]
