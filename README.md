
[![CircleCI](https://circleci.com/gh/stackroute/capgemini-w1-godeliver.svg?style=svg)](https://circleci.com/gh/stackroute/capgemini-w1-godeliver)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d7e8141b1ef24d05b50df2877566b21f)](https://www.codacy.com/app/MeghnaW19/capgemini-w1-godeliver_2?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=stackroute/capgemini-w1-godeliver&amp;utm_campaign=Badge_Grade)

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