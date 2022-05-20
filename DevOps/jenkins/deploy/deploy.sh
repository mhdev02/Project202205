#!/bin/bash

echo "*********************"
echo "***** Deploying *****"
echo "*********************"

pwd 

echo backend > DevOps/jenkins/deploy/.cred
echo $BUILD_TAG >> DevOps/jenkins/deploy/.cred
echo $ID >> DevOps/jenkins/deploy/.cred
echo $PASSWORD >> DevOps/jenkins/deploy/.cred

scp -i /opt/key DevOps/jenkins/deploy/.cred ec2-user@$IP:/home/ec2-user/.cred
scp -i /opt/key DevOps/jenkins/deploy/publish ec2-user@$IP:/home/ec2-user/publish
ssh -i /opt/key ec2-user@$IP "/home/ec2-user/publish" 
