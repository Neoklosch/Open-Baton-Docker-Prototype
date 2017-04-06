apt-get update && apt-get -y upgrade && apt-get install -y nano locate man  git-core
apt-get install -y --force-yes apt-transport-https
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -
echo "deb https://download.docker.com/linux/ubuntu trusty stable" >> /etc/apt/sources.list
apt-get update
apt-get install -y docker-ce

service ems stop
apt-get purge ems-0.15-SNAPSHOT
apt-get install -y ems-0.21

rm -Rf /opt/openbaton/ems
cd /opt/openbaton/
git clone https://github.com/openbaton/ems.git
cd /opt/openbaton/ems/
chmod +x *.py

service ems start
