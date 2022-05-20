#!/bin/bash

echo "***** Pushing the image to Docker Hub *****"

IMAGE="backend"

docker login -u $ID -p $PASSWORD

docker push $ID/$IMAGE:$BUILD_TAG

echo "***** Deleting the image after pushing *****"

docker rmi -f $ID/$IMAGE:$BUILD_TAG