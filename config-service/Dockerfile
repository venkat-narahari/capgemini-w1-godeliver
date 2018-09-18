FROM java:8-jre

RUN mkdir /src

WORKDIR /src

ADD target/configuration-service-1.0.0.jar /src/configuration-service-1.0.0.jar

EXPOSE 9071

RUN sh -c 'touch /src/configuration-service-1.0.0.jar'

ENTRYPOINT ["java", "-jar","/src/configuration-service-1.0.0.jar"]
