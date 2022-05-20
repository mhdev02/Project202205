#!/bin/bash

echo "************************"
echo "***** Building jar *****"
echo "************************"

docker run --rm -v "${WORKSPACE}/Backend":/app -v "${WORKSPACE}/Backend":/root/.m2/ -w /app maven:3-alpine "$@" 