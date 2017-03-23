#!/bin/sh

apt-get update && apt-get -y upgrade && apt-get install -y nano locate man screen git-core openjdk-7-jdk openjdk-7-demo openjdk-7-doc openjdk-7-jre-headless openjdk-7-jre-lib openjdk-7-source
apt-get install -y --force-yes apt-transport-https
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -
echo "deb https://download.docker.com/linux/ubuntu trusty stable" >> /etc/apt/sources.list
apt-get update
apt-get install -y docker-ce

echo "export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64" >> ~/.bashrc
source ~/.bashrc

# install dummy VNFM
mkdir /opt/openbaton
cd /opt/openbaton
git clone https://github.com/openbaton/dummy-vnfm-amqp.git
cd /opt/openbaton/dummy-vnfm-amqp
./dummy-vnfm.sh compile

# install docker plugin
mkdir /opt/openbaton-docker-plugin
cd /opt/openbaton-docker-plugin
git clone https://github.com/Neoklosch/Open-Baton-Docker-Prototype.git
cp /opt/openbaton-docker-plugin/Open-Baton-Docker-Prototype/jar/open_baton_docker_plugin-1.0-SNAPSHOT.jar /usr/lib/openbaton/plugins/vim-drivers/openbaton-plugin-vimdriver-docker-1.0-SNAPSHOT.jar