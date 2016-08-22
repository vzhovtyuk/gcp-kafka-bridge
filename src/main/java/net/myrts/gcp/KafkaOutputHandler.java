package net.myrts.gcp;

import gate.Document;
import gate.cloud.batch.AnnotationSetDefinition;
import gate.cloud.batch.DocumentID;
import gate.cloud.io.OutputHandler;
import gate.util.GateException;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 *
 * @author <a href="mailto:vzhovtiuk@gmail.com">Vitaliy Zhovtyuk</a>
 *         Date: 4/26/16
 *         Time: 6:57 PM
 */
public class KafkaOutputHandler implements OutputHandler {

    /**
     * Document IDs that are already complete after a previous run of this
     * batch.
     */
    protected Set<String> completedDocuments;


    private static Logger logger = Logger
            .getLogger(KafkaOutputHandler.class);

    private String mimeType;
    private KafkaProducer<String, String> producer;
    private String topic;

    @Override
    public void config(Map<String, String> map) throws IOException, GateException {
        topic = map.get("topic");
        String brokerHost = map.getOrDefault("kafkaHost", System.getenv("KAFKA_HOST"));

        if (StringUtils.isEmpty(brokerHost)) {
            throw new IllegalArgumentException("Kafka host is not defined neither in hadler config nor in KAFKA_HOST environment variable");
        }

        Properties props = new Properties();
        props.put("bootstrap.servers", brokerHost);
        props.put("metadata.broker.list", brokerHost);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    @Override
    public void setAnnSetDefinitions(List<AnnotationSetDefinition> list) {

    }

    @Override
    public List<AnnotationSetDefinition> getAnnSetDefinitions() {
        return null;
    }

    @Override
    public void init() throws IOException, GateException {

    }

    @Override
    public void outputDocument(Document document, DocumentID documentID) throws IOException, GateException {
        ProducerRecord<String, String> producerRecord
                = new ProducerRecord<>(topic, documentID.getIdText(), document.toXml());
        logger.info("Produced document - " + producerRecord.key() + " to topic " + topic);
        producer.send(producerRecord);
    }

    @Override
    public void close() throws IOException, GateException {
        producer.close();
    }
}
