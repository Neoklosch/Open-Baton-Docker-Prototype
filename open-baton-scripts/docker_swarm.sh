#!/bin/sh


# start swarm and master
docker swarm init

# get token
docker swarm join-token -q worker

# list all nodes
docker node ls

# start worker 01
docker run -d --privileged --name worker-01 --hostname=worker-01 -p 12375:2375 docker:17.05.0-dind
# add worker 01 to swarm
docker --host=localhost:12375 swarm join --token SWMTKN-1-07k0qy2o2onf1j8uysnf2pe4uympcb69s0jd50k2no7jteg2ve-0tx8xju65fz348do4y7x3gpr3 192.168.178.66:2377

# start worker 02
docker run -d --privileged --name worker-02 --hostname=worker-02 -p 22375:2375 docker:17.05.0-dind
# add worker 02 to swarm
docker --host=localhost:22375 swarm join --token SWMTKN-1-07k0qy2o2onf1j8uysnf2pe4uympcb69s0jd50k2no7jteg2ve-0tx8xju65fz348do4y7x3gpr3 192.168.178.66:2377

# start a service
docker service create --replicas 2 --name nginx-swarm nginx

# check state of service
docker service ls

# remove service
docker service rm nginx-swarm

# get infos for specific service
docker service ps nginx-swarm

# scale up
docker service update --replicas 5 nginx-swarm


# https://www.youtube.com/watch?v=KC4Ad1DS8xU
