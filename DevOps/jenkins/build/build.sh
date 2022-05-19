#!/bin/bash

cp -f Backend/target/*.jar DevOps/jenkins/build/

echo "***** Building Docker Image *****"

cd DevOps/jenkins/build/ && docker build -t backend:$BUILD_TAG . --no-cache
