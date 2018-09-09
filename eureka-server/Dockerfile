FROM java:8-jre

RUN mkdir /src

WORKDIR /src

ADD target/eureka-server-1.0.0.jar /src/eureka-server-1.0.0.jar

EXPOSE 8761

RUN sh -c 'touch /src/eureka-server-1.0.0.jar'

ENTRYPOINT ["java","-jar","/src/eureka-server-1.0.0.jar"]
