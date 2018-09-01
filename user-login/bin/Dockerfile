# pull image of jdk
FROM java:8-jre

# copy jar file to docker file system
ADD ./target/user-login-0.0.1-SNAPSHOT.jar /usr/app/user-login-0.0.1-SNAPSHOT.jar

Expose 8082

# java -jar <jar file name>
WORKDIR usr/app
ENTRYPOINT ["java", "-jar", "user-login-0.0.1-SNAPSHOT.jar"]
