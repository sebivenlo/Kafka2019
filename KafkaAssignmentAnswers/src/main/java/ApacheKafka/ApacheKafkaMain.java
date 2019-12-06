package ApacheKafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ApacheKafkaMain {
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    private final static String TOPIC = "Article";
    private final static String NRREADSTOPIC = "NrReads";
    private final static String NEWARTICLESTOPIC = "NewArticles";

    private final static String CONSUMER_GROUP_ID = "CONSUMER";

    private static Logger logger = LoggerFactory.getLogger(ApacheKafkaMain.class);

    private static Map<String, Integer> nrReadsMap = new HashMap<>();

    private static final KafkaConsumer<String, String> consumer = getConsumer();
    private static final KafkaProducer<String, String> producer = getProducer();

    /**
     * TODO Assignment 2: Subscribing and publishing
     * 1. Subscribe to the TOPIC that is provided and poll it to read incoming messages, you can use a loop to
     * keep reading new incoming messages.
     *
     * The message is built up as follows:
     * {'Ian', 'Politics', 'read'}
     * 1. The name is the username of the person browsing the news.
     * 2. The article represents the article that the user has read/created.
     * 3. The action stands for the type of operation the user performed, either a read or write of an article.
     *
     * 2. Knowing the above information, keep track of the number of times a certain article is read.
     * Use the KafkaMessage received from polling the consumer. You can clean up the Message with the String.split and
     * String.replace method.
     *
     * 3. For every read of an article publish the message and the number of reads to a
     * different kafka topic called: "NrReads" using the kafka template.
     * Try it out using a listener on the "NrReads" topic.
     *
     * 4. Also whenever a write action is performed publish the article to a topic: "NewArticles".
     */
    public static void main(String[] args) {
        logger.info("Main started");
        consumer.subscribe(Collections.singletonList(TOPIC));
        boolean stop = false;
        while(!stop) {
            final ConsumerRecords<String, String> polledRecords = consumer.poll(Duration.ofSeconds(10));
            final Iterable<ConsumerRecord<String, String>> records = polledRecords.records(TOPIC);
            for (ConsumerRecord record : records) {
                logger.info("Record is: " + record.value().toString());
                final String value = record.value().toString();
                final String[] split = value.split(",");
                String name = split[0];
                String article = split[1];
                String action = split[2];
                if (!nrReadsMap.containsKey(article) && action.equals("read")) {
                    nrReadsMap.put(article, 1);
                    logger.info("First time " + article + " is read");
                    ProducerRecord<String, String> producerRecord = new ProducerRecord<>(NRREADSTOPIC, article, String.valueOf(1));
                    producer.send(producerRecord);
                } else if(action.equals("read")) {
                    final Integer nrReads = nrReadsMap.get(article) + 1;
                    nrReadsMap.put(article, nrReads);
                    logger.info("Adding a read to article: " + article);
                    ProducerRecord<String, String> producerRecord = new ProducerRecord<>(NRREADSTOPIC, article, String.valueOf(nrReads));
                    producer.send(producerRecord);
                }
                if (action.equals("write")) {
                    logger.info("New incoming article: " + article);
                    ProducerRecord<String, String> producerRecord = new ProducerRecord<>(NEWARTICLESTOPIC, article, action);
                    producer.send(producerRecord);
                }
            }

        }
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
    private static KafkaConsumer<String, String> getConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_ID);
        return new KafkaConsumer<>(props);
    }
}
