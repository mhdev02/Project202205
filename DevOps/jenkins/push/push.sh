#!/bin/bash

echo "***** Pushing the image to Docker Hub *****"

IMAGE="backend"

echo $PASSWORD | docker login -u $ID --password-stdin

docker push $ID/$IMAGE:$BUILD_TAG

echo "***** Deleting the image after pushing *****"

docker rmi -f $ID/$IMAGE:$BUILD_TAG