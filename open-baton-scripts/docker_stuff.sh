#!/bin/sh


# run container
# !!!! Attention: Check the IP !!!!
docker run -d -h openbaton-rabbitmq -p 8080:8080 -p 5672:5672 -p 15672:15672 -p 8443:8443 -e RABBITMQ_BROKERIP=192.168.178.54 neoklosch/open_baton_docker_test

# build Open Baton project
./gradlew clean googleJavaFormat build

# copy jar to Open Baton in docker
docker cp ./build/libs/open_baton_docker_plugin-1.0-SNAPSHOT.jar gracious_ritchie:/usr/lib/openbaton/plugins/vim-drivers/openbaton-plugin-vimdriver-docker-1.0-SNAPSHOT.jar

# run container and give them a specific name
docker run -d --name first_container -h openbaton-rabbitmq -p 8080:8080 -p 5672:5672 -p 15672:15672 -p 8443:8443 -e RABBITMQ_BROKERIP=192.168.178.24 1e38eecc6c2b

# enable rabbitmq management
docker exec 7ad1d7102a98 rabbitmq-plugins enable rabbitmq_management

# build image
docker build -t neoklosch/open_baton_docker_test .
docker build -t neoklosch/open_baton_docker_ems_test .

# commit image
docker commit bbd42c2ce2a4 neoklosch/open_baton_docker_test
docker commit bbd42c2ce2a4 neoklosch/open_baton_docker_ems_test

# upload image
docker push neoklosch/open_baton_docker_test
docker push neoklosch/open_baton_docker_ems_test

# start dummy VNFM
cd /opt/openbaton/dummy-vnfm-amqp && ./dummy-vnfm.sh start

# run pop container and expose docker socket
docker run --name pop-01 -p 8081:8080 -p 5673:5672 -p 15673:15672 -p 8444:8443 -p 61614:61613 -v /var/run/docker.sock:/var/run/docker.sock -d neoklosch/open_baton_docker_ems_test
docker run --name pop-02 -p 8082:8080 -p 5674:5672 -p 15674:15672 -p 8445:8443 -p 61615:61613 -v /var/run/docker.sock:/var/run/docker.sock -d neoklosch/open_baton_docker_ems_test
