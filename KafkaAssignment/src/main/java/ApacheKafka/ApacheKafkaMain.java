package ApacheKafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ApacheKafkaMain {
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    private final static String TOPIC = "Article";

    private final static String CONSUMER_GROUP_ID = "CONSUMER";

    private static Logger logger = LoggerFactory.getLogger(ApacheKafkaMain.class);

    private static Map<String, Integer> nrReadsMap = new HashMap<>();

    /**
     * TODO Assignment 2: Subscribing and publishing
     * 1. Subscribe to the TOPIC that is provided and poll it to read incoming messages, you can use a loop to
     * keep reading new incoming messages.
     *
     * The message is built up as follows:
     * {name='Ian', article='Politics', action='Read'}
     * The name is the username of the person browsing the news.
     * The article represents the article that the user has read/created.
     * The action stands for the type of operation the user performed, either a read or write of an article.
     *
     * 2. Knowing the above information, keep track of the number of times a certain article is read.
     * Use the KafkaMessage received from polling the consumer. You can clean up the Message with the String.split and
     * String.replace method.
     *
     * 3. For every read of an article publish the message and the number of reads to a
     * different kafka topic called: "NrReads" using the kafka template.
     * Try it out using a listener on the "NrReads" topic.
     *
     * 4. Also whenever a write action is performed publish the article name to a topic: "NewArticles".
     */
    public static void main(String[] args) {
        logger.info("Main started");
    }

    /**
     * Gets a producer that can be used to produce Kafka messages
     */
    private static KafkaProducer<String, String> getProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    /*
     * TODO Assignment 1: Add a Kafka consumer
     * Make a static method getConsumer() that returns a KafkaConsumer<String, String> with the correct config
     */
}
