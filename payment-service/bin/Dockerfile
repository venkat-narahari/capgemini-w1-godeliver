# pull image of jdk

FROM java:8-jre

# copy jar file to docker file system

ADD ./target/payment-service-0.0.1-SNAPSHOT.jar /usr/app/payment-service-0.0.1-SNAPSHOT.jar

# java -jar <jar fileName>

EXPOSE 9091

WORKDIR usr/app

ENTRYPOINT ["java","-jar", "payment-service-0.0.1-SNAPSHOT.jar"]
