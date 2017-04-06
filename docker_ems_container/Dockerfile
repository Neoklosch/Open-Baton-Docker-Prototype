FROM openbaton/docker-ems

MAINTAINER Neoklosch version: 0.1

ADD ./setup.sh /tmp/setup.sh
COPY conf.ini /etc/openbaton/ems/conf.ini
RUN /bin/bash /tmp/setup.sh
