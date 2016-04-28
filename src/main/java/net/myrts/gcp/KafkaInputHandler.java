package net.myrts.gcp;

import gate.*;
import gate.cloud.batch.Batch;
import gate.cloud.batch.DocumentID;
import gate.cloud.io.DocumentData;
import gate.cloud.io.StreamingInputHandler;
import gate.util.GateException;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.Decoder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author <a href="mailto:vzhovtiuk@gmail.com">Vitaliy Zhovtyuk</a>
 *         Date: 4/26/16
 *         Time: 6:57 PM
 */
public class KafkaInputHandler implements StreamingInputHandler {

    /**
     * Document IDs that are already complete after a previous run of this
     * batch.
     */
    protected Set<String> completedDocuments;


    private static Logger logger = Logger
            .getLogger(KafkaInputHandler.class);

    private ConsumerConnector consumerConnector;
    private ConsumerIterator<String, String> consumerIterator;
    private String mimeType;

    @Override
    public void config(Map<String, String> map) throws IOException, GateException {
        final Properties consumerProps = new Properties();
        consumerProps.put("zookeeper.connect", map.get("zkHost"));
        consumerProps.put("bootstrap.servers", map.get("kafkaHost"));
        consumerProps.put("group.id", "test");
        consumerProps.put("enable.auto.commit", "true");
        consumerProps.put("auto.commit.interval.ms", "1000");
        consumerProps.put("session.timeout.ms", "30000");
        consumerProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        final ConsumerConfig consumerConfig = new ConsumerConfig(consumerProps);
        consumerConnector = kafka.consumer.Consumer.createJavaConsumerConnector(consumerConfig);
        mimeType = map.get("mime");
        String topic = map.get("topic");
        Map<String, Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic, 1);
        Decoder<String> decoder = String::new;

        Map<String, List<KafkaStream<String, String>>> consumerMap
                = consumerConnector.createMessageStreams(topicCountMap, decoder, decoder);
        List<KafkaStream<String, String>> streams = consumerMap.get(topic);
        KafkaStream<String, String> kafkaStream = streams.get(0);
        consumerIterator = kafkaStream.iterator();
    }

    @Override
    public void init() throws IOException, GateException {

    }

    @Override
    public void close() throws IOException, GateException {
        consumerConnector.commitOffsets();
        consumerConnector.shutdown();
    }

    @Override
    public void startBatch(Batch batch) {
        completedDocuments = batch.getCompletedDocuments();
        if (completedDocuments != null && completedDocuments.size() > 0) {
            logger.info("Restarting failed batch - " + completedDocuments.size()
                    + " documents already processed");
        }
    }

    @Override
    public DocumentData nextDocument() throws IOException, GateException {
        while (consumerIterator.hasNext()) {
            MessageAndMetadata<String, String> messageAndMetadata = consumerIterator.next();
            String messageStr = messageAndMetadata.message();
            String id = messageAndMetadata.key();
            if (id == null || "".equals(id)) {
                // can't find an ID, assume this is a "delete" or similar and
                // ignore it
                if (logger.isDebugEnabled()) {
                    logger.debug("No ID found in JSON object " + messageStr + " - ignored");
                }
            } else if (completedDocuments.contains(id)) {
                // already processed, ignore
            } else {
                DocumentID documentID = new DocumentID(id);
                FeatureMap docParams = Factory.newFeatureMap();
                docParams.put(Document.DOCUMENT_STRING_CONTENT_PARAMETER_NAME,
                        messageAndMetadata.message());
                if (mimeType != null) {
                    docParams.put(Document.DOCUMENT_MIME_TYPE_PARAMETER_NAME, mimeType);
                }
                try {
                    Document gateDoc =
                            (Document) Factory.createResource("gate.corpora.DocumentImpl",
                                    docParams, Utils.featureMap(
                                            GateConstants.THROWEX_FORMAT_PROPERTY_NAME,
                                            Boolean.TRUE), documentID.getIdText());
                    return new DocumentData(gateDoc, documentID);
                } catch (Exception e) {
                    // logger.warn("Error encountered while parsing object with ID " + id
                    //       + " - skipped", e);
                }
            }
        }


        return null;
    }


    public DocumentData getInputDocument(DocumentID id) throws IOException,
            GateException {
        throw new UnsupportedOperationException(
                "KafkaInputHandler can only operate in streaming mode");
    }
}
