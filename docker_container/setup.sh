#!/bin/sh

apt-get update && apt-get -y upgrade && apt-get install -y nano locate man openjdk-7-jdk openjdk-7-demo openjdk-7-doc openjdk-7-jre-headless openjdk-7-jre-lib openjdk-7-source
apt-get install -y --force-yes apt-transport-https
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -
echo "deb https://download.docker.com/linux/ubuntu trusty stable" >> /etc/apt/sources.list
apt-get update
apt-get install -y docker-ce

echo "export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64" >> ~/.bashrc
source ~/.bashrc
