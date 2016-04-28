package net.myrts.gcp;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Properties;

public class KafkaZkRemoteTest {
    public static final String TEST_TOPIC = "test";
    public static final String HOST = "192.168.99.100:9092";
    public static final String ZK_HOST = "192.168.99.100:2181";

    private static Logger logger = Logger
            .getLogger(KafkaZkRemoteTest.class);

    @Test
    public void shouldSendProducerStrings() {
        //create kafka producer without zookeeper on localhost:
        Properties props = new Properties();
        //Use broker.list to bypass zookeeper:
        props.put("bootstrap.servers", HOST);
        props.put("metadata.broker.list", HOST);
        // props.put("metadata.broker.list", "192.168.59.100:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        //send one message to local kafka server:
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TEST_TOPIC, "test-id" + i ,"In this section I include an example of this Latin verse, which begins Beati qui esuriunt / Et sitiunt (from British Library MS Harley 913 fol. 59r-59v), as edited and translated in Wr PSE pp. 224-30. This poem, written in the manuscript as prose but with alternating four- and three-beat lines and intricate rhyme schemes characteristic of goliardic lyrics, dates from the beginning of the fourteenth century (reign of Edward I) and is entitled by Wright \"Song on the Venality of the Judges.\" I have checked Wright's edition against a photostat of the manuscript. I include but slightly modernize Wright's translation.\n" +
                    "\n" +
                    "One of the chief documents in the Middle English Abuse of Money tradition is The Simonie, also known as \"On the Evil Times of Edward II\" and \"Symonie and Couetise\" (Index § 4165; Supplement § 1992). The anonymous author of The Simonie, which Wright dates to about 1321, complains that those who govern abuse their power egregiously -- so much so that God has sent famines and plagues as punishments for wrongdoing. A dominant motif of the poem is that the poor man -- \"Godes man\" -- stands outside the doors of court while the rich man, bearing gifts, is welcomed inside (lines 9-30, 55-66, 121-44, 169-80). It offers traditional estates satire that begins with the court of Rome and high prelates and proceeds through the clerical ranks (including monks, parsons, and friars) to knights, squires, justices, bailiffs, sheriffs, beadles, and merchants.3 The linking of anticlerical satire and the abuse of money anticipates Piers Plowman. Like Piers Plowman, The Simonie is lively and vivid, with touches of arch wit. A newly-installed parson will spend money so quickly that the corn in his barn will not be eaten by mice (lines 69-70). What kind of \"penance\" do monks perform? \"Hii weren sockes in here shon [shoes], and felted botes above\" (line 146). Those who live according to a monastic rule live a life of ease rather than easing the lives of others (lines 151-56). A false physician will \"wagge his urine in a vessel of glaz,\" swear that the patient is sicker than he really is, and comfort the anxious wife. The author adds that such a doctor may know \"no more than a gos [goose] wheither he wole live or die\" (lines 211-21). On a few occasions the author includes something like dialogue, as when the false physician says to the housewife, \"Dame, for faute [lack] of helpe, thin housebonde is neih [almost] slain\" (line 216), or when the beggar in the street cries out, \"Allas, for hungger I die / Up rihte!\" (lines 400-01). There are several apocalyptic passages in the poem. The author points to recent natural disasters as evidence of divine disfavor; and in a memorable sequence he alludes to an English gamen, game, in which people begin cursing one another on Monday. And now, he says, God has abandoned the land, sending a great \"derthe\" that has caused a bushel of wheat to soar to \"foure shillinges or more\" (line 393). Wr regards this as a reference to the great famine of 1315 and its consequences. The poem contains colorful language, snatches of song, and proverbs. The new parson, rather than reading the Bible, \"rat on the rouwe-bible\" (\"reads\" the fiddle [line 88]); he will discharge \"a prest of clene lyf\" and then replace him with \"a daffe\" (lines 97, 99). A wanton priest will provide himself with \"a gay wench of the newe jet\" and, \"when the candel is oute,\" \"clateren cumpelin\" (\"recite compline\" [lines 118-20])." );
            producer.send(producerRecord);
        }
        producer.close();
    }
}