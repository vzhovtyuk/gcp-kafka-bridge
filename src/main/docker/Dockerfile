FROM java:8

MAINTAINER Vitaliy Zhovtyuk <vzhovtiuk@gmail.com>

RUN apt-get update && apt-get install -y \
      curl
      
RUN mkdir -p /opt/gate/gcp

WORKDIR /opt/gate/gcp

COPY gcp-2.5 gcp-2.5

COPY workdir workdir

COPY gcp-kafka-plugin-jar-with-dependencies.jar gcp-2.5/lib
 
RUN ["chmod", "+x",  "/opt/gate/gcp/workdir/clean.sh"]

RUN ["chmod", "+x",  "/opt/gate/gcp/workdir/run.sh"]

WORKDIR /opt/gate/gcp/workdir

CMD ["/opt/gate/gcp/workdir/clean.sh"]

CMD ["/opt/gate/gcp/workdir/run.sh"]
 