#!/bin/bash

echo "***************************"
echo "***** Testing Backend *****"
echo "***************************"

docker run --rm -v "${WORKSPACE}/Backend":/app -v "${WORKSPACE}/Backend":/root/.m2/ -w /app maven:3.8.5-jdk-8 "$@"