#pull image of jdk
FROM java:8-jre

#copy jar file to docker file system
ADD ./target/logistics-service-0.0.1-SNAPSHOT.jar /usr/app/logistics-service-0.0.1-SNAPSHOT.jar

Expose 8086

#java -jar <jar fileName>
WORKDIR usr/app
ENTRYPOINT ["java","-jar","logistics-service-0.0.1-SNAPSHOT.jar"]