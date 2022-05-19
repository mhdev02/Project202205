#!/bin/bash

cp -f ../../Backend/target/*.jar ./build/

echo "***** Building Docker Image *****"

cd ./build/ && docker build -t backend:$BUILD_TAG . --no-cache
