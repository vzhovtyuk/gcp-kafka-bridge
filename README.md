# gcp-kafka-bridge
Kafka implementation for GATE (https://gate.ac.uk/gate) and GATE Cloud Paralleliser (https://gate.ac.uk/gcp) Input and Output handlers. Allows to send GATE documents to GCP and consume back GATE document with annotations in form on kafka message.
The example of GCP batch file configuration:

    <input topic="test_in" zkHost="192.168.99.100:2181" kafkaHost="192.168.99.100:9092" mimeType="text/xml"
           class="net.myrts.gcp.KafkaInputHandler"/>

    <output topic="test_out" 
            kafkaHost="192.168.99.100:9092"
            class="net.myrts.gcp.KafkaOutputHandler">
    </output>

Parameters zkHost and kafkaHost are optional, if they omitted values taken from $KAFKA_HOST and $KAFKA_ZOOKEEPER_CONNECT environment variables. Normally those values provided in docker-compose file.
This means that GCP will consume messages from topic topic_in Kafka host 192.168.99.100, process in form of GATE document and send message to topic test_out of 192.168.99.100:9092 Kafka host.

Technical details:
Solution tested with Kafka 0.8.0.2 and GCP 2.5.
GCP binaries available in project and can be run ANNIE default application. In order to run GCP please execute the following:

1. Copy resulting jar (containing all dependencies) to GCP/workdir/lib
2. Navigate to working directory GCP/workdir
3. Clean existing pathes. Execute sh bin/clean.sh
4. Run GCP. Execute sh bin/run.sh

# Run in docker container

## Build docker image for gcp

1. Make sure docker daemon is running
2. Execute in root folder mvn clean package docker:build (this should build gcp image make is avaiable locally)


## Run in docker-compose
1. Install docker-compose
2. Execute in root folder docker-compose up -d (this should run zookeeper, kafka and gcp services and make it available)


