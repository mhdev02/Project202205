#!/bin/bash

echo "***** Pushing the image to Docker Hub ***"

IMAGE="backend"

docker login -u $ID -p $PASSWORD

docker tag $IMAGE:$BUILD_TAG $ID/$IMAGE:$BUILD_TAG

docker push $ID/$IMAGE:$BUILD_TAG

sudo docker rmi -f $ID/$IMAGE:$BUILD_TAG