#!/bin/bash

echo "***** Testing *****"

docker run --rm -v "${WORKSPACE}/Backend":/app -v "${WORKSPACE}/Backend":/root/.m2/ -w /app maven:3-alpine "$@"
