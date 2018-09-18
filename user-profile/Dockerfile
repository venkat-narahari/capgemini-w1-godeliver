# pull image of jdk
FROM java:8-jre

# copy jar file to docker file system
ADD ./target/user-profile-0.0.1-SNAPSHOT.jar /usr/app/user-profile-0.0.1-SNAPSHOT.jar

Expose 9073

# java -jar <jar fileName>
WORKDIR usr/app
ENTRYPOINT ["java","-jar","user-profile-0.0.1-SNAPSHOT.jar"]
