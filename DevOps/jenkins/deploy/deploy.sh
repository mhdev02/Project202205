#!/bin/bash

echo "*********************"
echo "***** Deploying *****"
echo "*********************"

echo backend > DevOps/jenkins/deploy/.cred
echo server >> DevOps/jenkins/deploy/.cred
echo $BUILD_TAG >> DevOps/jenkins/deploy/.cred
echo $ID >> DevOps/jenkins/deploy/.cred
echo $PASSWORD >> DevOps/jenkins/deploy/.cred
echo $IP >> DevOps/jenkins/deploy/.cred
echo $REDIS_IP >> DevOps/jenkins/deploy/.cred
echo $REDIS_PORT >> DevOps/jenkins/deploy/.cred

scp -i $KEY DevOps/jenkins/deploy/.cred ec2-user@$IP:/home/ec2-user/.cred
scp -i $KEY DevOps/jenkins/deploy/publish ec2-user@$IP:/home/ec2-user/publish
ssh -i $KEY ec2-user@$IP "/home/ec2-user/publish" 

echo " " > DevOps/jenkins/deploy/.cred

docker rmi $ID/backend:$BUILD_TAG
docker rmi $ID/server:$BUILD_TAG