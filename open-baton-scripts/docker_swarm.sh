#!/bin/sh

echo "create cluster and store hash in system variable called sid..."
sid=$(docker run swarm create)

echo "create swarm master..."
docker-machine create -d virtualbox --swarm --swarm-master --swarm-discovery token://$sid swarm-master
# docker-machine create -d virtualbox swarm-master

echo "create first node..."
docker-machine create -d virtualbox --engine-label itype=frontend --swarm --swarm-discovery token://$sid swarm-node-01
# docker-machine create -d virtualbox worker-01

echo "create second node..."
docker-machine create -d virtualbox --swarm --swarm-discovery token://$sid swarm-node-02

echo "create third node..."
docker-machine create -d virtualbox --swarm --swarm-discovery token://$sid swarm-node-03

echo "link to the swarm via master..."
eval "$(docker-machine env --swarm swarm-master)"

echo "list machines..."
docker-machine ls

echo "show info"
docker info

echo "link to a specific machine..."
eval "$(docker-machine env swarm-master)"

echo "login to one of the machines..."
docker-machine ssh swarm-master

echo "start container..."
docker run -itd --name engmgr nginx

# https://www.youtube.com/watch?v=KC4Ad1DS8xU
