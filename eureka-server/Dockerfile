FROM java:8-jre
MAINTAINER teja "tejakathari007@gmail.com"

RUN mkdir /src

WORKDIR /src

COPY target/eureka-server-1.0.0.jar /src/eureka-server-1.0.0.jar

EXPOSE 9070

RUN sh -c 'touch /src/eureka-server-1.0.0.jar'

ENTRYPOINT ["java","-jar","/src/eureka-server-1.0.0.jar"]
