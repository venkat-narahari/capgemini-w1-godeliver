## [capgemini-w1-godeliver](https://github.com/venkat-narahari/capgemini-w1-godeliver)

The Project is consists of Logistics and Ecommerce related microservices with technology stack that includes Spring, Angular, BootStrap, Java, HTML, CSS, JavaScript, TypeScript, MongoDB, MySQL, Neo4J, Kafka, Docker, AWS EC2, Jenkins.
The primary motivation is to develop a logistics vehicle routing algorithm to distribute and deliver resources in the optimal manner. The mock Book Shop Ecommerce web application is created to present the business indicators of the logistics microservices.

## [Documentation](https://drive.google.com/file/d/1lX8qhK-x3icp_Jse0DC9kwfacq3GwyYb/view?usp=sharing)

 ![Logistics Architecture](/docs/img/LogisticsArchitecture.png)
 
 ![Book Shop Home Page](/docs/img/BookShopHomePage.png)

 ![Book Shop Book Store](/docs/img/BookShopBooks.png)

 ![Book Shop Mobile Site](/docs/img/BookShopHomePageMobileVersion.png)

 ![Logistics Visualization 01](/docs/img/LogisticsVisualization01.png)

 ![Logistics Visualization 02](/docs/img/LogisticsVisualization02.png)
 
 ![Logistics Visualization 03](/docs/img/LogisticsVisualization03.png)
 

## CICD
[![CircleCI](https://circleci.com/gh/stackroute/capgemini-w1-godeliver.svg?style=svg)](https://circleci.com/gh/stackroute/capgemini-w1-godeliver)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a00da8809a034f3fa8259cf2991e1fb2)](https://www.codacy.com/app/MeghnaW19/capgemini-w1-godeliver?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=stackroute/capgemini-w1-godeliver&amp;utm_campaign=Badge_Grade)

# Java Maven CircleCI 2.0 configuration file
#
# Check https://circleci.com/docs/2.0/language-java/ for more details
#
version: 2
jobs:
  build:
    docker:
      # specify the version you desire here
      - image: circleci/openjdk:8-jdk
      
      # Specify service dependencies here if necessary
      # CircleCI maintains a library of pre-built images
      # documented at https://circleci.com/docs/2.0/circleci-images/
      # - image: circleci/postgres:9.4

    working_directory: ~/repo

    environment:
      # Customize the JVM maximum heap limit
      MAVEN_OPTS: -Xmx3200m
    
    steps:
      - checkout

      # Download and cache dependencies
      - restore_cache:
          keys:
          - v1-dependencies-{{ checksum "pom.xml" }}
          # fallback to using the latest cache if no exact match is found
          - v1-dependencies-

      - run: mvn dependency:go-offline

      - save_cache:
          paths:
            - ~/.m2
          key: v1-dependencies-{{ checksum "pom.xml" }}
        
      # run tests!
      - run: mvn integration-test
