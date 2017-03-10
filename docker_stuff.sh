#!/bin/sh



docker run -d -h openbaton-rabbitmq -p 8080:8080 -p 5672:5672 -p 15672:15672 -p 8443:8443 -e RABBITMQ_BROKERIP=192.168.178.24 openbaton/standalone
./gradlew clean googleJavaFormat build
docker cp ./build/libs/open_baton_docker_plugin-1.0-SNAPSHOT.jar inspiring_archimedes:/usr/lib/openbaton/plugins/vim-drivers/openbaton-plugin-vimdriver-docker-1.0-SNAPSHOT.jar
