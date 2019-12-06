package SpringKafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

    /**
     * TODO Assignment 1: Add the configuration
     * <p>
     * Method that can produce the factory that can create Kafka templates
     *
     * @return a DefaultKafkaProducerFactory with correct properties
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        // Config properties Hashmap to put in the kafka config
        Map<String, Object> configProps = new HashMap<>();
        // Set the bootstrap address provided to the correct property for the bootstrap servers config
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        /* Now set the values for KEY_SERIALIZER_CLASS and VALUE_SERIALIZER_CLASS to the
           StringSerializer from org.apache.kafka.common.serialization.StringSerializer */
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Bean that exposes the KafkaTemplate for use in the main class
     *
     * @return A new KafkaTemplate with required properties to connect with Kafka
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
