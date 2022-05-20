#!/bin/bash

cp -f Backend/target/*.jar DevOps/jenkins/build/

echo "*********************************"
echo "***** Building Docker Image *****"
echo "*********************************"

cd DevOps/jenkins/build/ && docker build -t $ID/backend:$BUILD_TAG . --no-cache
cd ScriptCrawler/server/ && docker build -t $ID/server:$BUILD_TAG . --no-cache
