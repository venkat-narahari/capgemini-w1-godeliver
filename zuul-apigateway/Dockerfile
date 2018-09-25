FROM java:8-jre

MAINTAINER teja "tejakathari007@gmail.com"

RUN mkdir /src

WORKDIR /src

COPY target/zuul-apigateway-1.0.0.jar /src/zuul-apigateway-1.0.0.jar

EXPOSE 9088

RUN sh -c 'touch /src/zuul.jar'

ENTRYPOINT ["java", "-jar","/src/zuul-apigateway-1.0.0.jar"]
