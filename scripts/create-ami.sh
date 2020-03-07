#!/bin/bash

# When lunching the instance, the following components must be installed

INSTANCE_ID=$(curl --silent --show-error --retry 3 http://169.254.169.254/latest/meta-data/instance-id)
INSTANCE_REGION=$(curl -s http://169.254.169.254/latest/dynamic/instance-identity/document | grep region | awk -F\" '{print $4}')
ENVIRONMENT=$(aws ec2 describe-tags --filters "Name=resource-id,Values=${INSTANCE_ID}" "Name=key,Values=Environment" --region=${INSTANCE_REGION} --output=text | cut -f5)
NAME=$(aws ec2 describe-tags --filters "Name=resource-id,Values=${INSTANCE_ID}" "Name=key,Values=Name" --region us-east-1 --output text --query "Tags[*].Value")

# Install java
sudo yum install java-11-amazon-corretto-headless

# Install updates and dependencies
amazon-linux-extras install -y epel
yum -y update
yum -y install ruby wget

# Install the AWS CodeDeploy agent
cd ~
wget https://aws-codedeploy-us-east-1.s3.us-east-1.amazonaws.com/latest/install
chmod +x ./install
./install auto
rm -rf install

# Install CloudWatch Agent
rpm -Uhv https://s3.amazonaws.com/amazoncloudwatch-agent/amazon_linux/amd64/latest/amazon-cloudwatch-agent.rpm
aws s3 cp s3://harri-deployments/resources/cloudwatch-agent/amazon-cloudwatch-agent.json /opt/aws/amazon-cloudwatch-agent/etc/amazon-cloudwatch-agent.json --region=us-east-1
sed -i "s/harri-ms-name/${NAME}/g" /opt/aws/amazon-cloudwatch-agent/etc/amazon-cloudwatch-agent.json
systemctl restart amazon-cloudwatch-agent.service