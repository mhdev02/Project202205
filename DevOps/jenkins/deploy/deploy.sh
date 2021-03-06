#!/bin/bash

echo "*********************"
echo "***** Deploying *****"
echo "*********************"

echo backend > DevOps/jenkins/deploy/.cred
echo server >> DevOps/jenkins/deploy/.cred
echo $BUILD_TAG >> DevOps/jenkins/deploy/.cred
echo $ID >> DevOps/jenkins/deploy/.cred
echo $PASSWORD >> DevOps/jenkins/deploy/.cred
echo $SERVER_IP >> DevOps/jenkins/deploy/.cred
echo $REDIS_IP >> DevOps/jenkins/deploy/.cred
echo $REDIS_PORT >> DevOps/jenkins/deploy/.cred
echo $DB_URL >> DevOps/jenkins/deploy/.cred
echo $DB_USER >> DevOps/jenkins/deploy/.cred
echo $DB_PASSWORD >> DevOps/jenkins/deploy/.cred
echo $JWT_SECRET >> DevOps/jenkins/deploy/.cred

scp -i $KEY DevOps/jenkins/deploy/.cred ec2-user@$SERVER_IP:/home/ec2-user/.cred
scp -i $KEY DevOps/jenkins/deploy/publish ec2-user@$SERVER_IP:/home/ec2-user/publish
ssh -i $KEY ec2-user@$SERVER_IP "/home/ec2-user/publish" 

echo " " > DevOps/jenkins/deploy/.cred