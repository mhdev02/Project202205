#!/bin/bash

echo "*******************************************"
echo "***** Pushing the image to Docker Hub *****"
echo "*******************************************"

IMAGE1="backend"
IMAGE2="server"

echo $PASSWORD | docker login -u $ID --password-stdin

docker push $ID/$IMAGE1:$BUILD_TAG
docker push $ID/$IMAGE2:$BUILD_TAG

echo "***** Deleting the image after pushing *****"

docker rmi -f $ID/$IMAGE1:$BUILD_TAG $ID/$IMAGE2:$BUILD_TAG