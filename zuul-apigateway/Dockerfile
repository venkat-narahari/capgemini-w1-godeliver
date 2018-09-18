FROM java:8-jre

RUN mkdir /src

WORKDIR /src

ADD target/zuul-apigateway-1.0.0.jar /src/zuul-apigateway-1.0.0.jar

EXPOSE 9072

RUN sh -c 'touch /src/zuul.jar'

ENTRYPOINT ["java", "-jar","/src/zuul-apigateway-1.0.0.jar"]
