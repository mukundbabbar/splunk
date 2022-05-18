#!/bin/bash

mvn clean package -DskipTests=true

cp ./target/java-app-services.jar ./docker/java-app-services.jar

docker build -t java-app-services -f docker/Dockerfile ./docker
