#pull image of jdk
FROM java:8-jre


#copy jar file to docker file system
ADD ./target/book-service-0.0.1-SNAPSHOT.jar /usr/app/book-service-0.0.1-SNAPSHOT.jar

#java -jar <jar file name>
WORKDIR usr/app
ENTRYPOINT ["java","-jar", "book-service-0.0.1-SNAPSHOT.jar"]
