#!/bin/bash

echo backend > /home/ec2-user/.cred
echo $BUILD_TAG >> /home/ec2-user/.cred
echo $ID >> /home/ec2-user/.cred
echo $PASSWORD >> /home/ec2-user/.cred

scp -i $KEY /home/ec2-user/.cred ec2-user@$IP:/home/ec2-user/.cred
scp -i $KEY ./deploy/publish ec2-user@$IP:/home/ec2-user/publish
ssh -i $KEY ec2-user@$IP "/home/ec2-user/publish" 
