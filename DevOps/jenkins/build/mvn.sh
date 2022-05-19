#!/bin/bash

echo "***** Building jar *****"

docker run --rm -v ./Backend:/app -v /root/.m2/:/root/.m2/ -w /app maven:3-alpine "$@"
