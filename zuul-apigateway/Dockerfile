FROM java:8

RUN mkdir /src

WORKDIR /src

ADD target/zuul-apigateway-1.0.0.jar /src/zuul-apigateway-1.0.0.jar

EXPOSE 8080

RUN sh -c 'touch /src/zuul.jar'

ENTRYPOINT ["java", "-jar","/src/zuul-apigateway-1.0.0.jar"]
