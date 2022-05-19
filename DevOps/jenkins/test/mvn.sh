#!/bin/bash

echo "***** Testing *****"

WORKSPACE=/home/ec2-user

docker run --rm -v $WORKSPACE/Backend:/app -v /root/.m2/:/root/.m2/ -w /app maven:3-alpine "$@"
