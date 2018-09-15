# pull image of jdk

FROM java:8-jre

# copy jar file to docker file system

ADD ./target/order-details-0.0.1-SNAPSHOT.jar /usr/app/order-details-0.0.1-SNAPSHOT.jar

# java -jar <jar fileName>

EXPOSE 8081

WORKDIR usr/app

ENTRYPOINT ["java","-jar", "order-details-0.0.1-SNAPSHOT.jar"]
