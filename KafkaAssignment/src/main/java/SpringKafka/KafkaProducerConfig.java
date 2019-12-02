package SpringKafka;

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
     *
     * Method that can produce the factory that can create Kafka templates
     * @return a DefaultKafkaProducerFactory with correct properties
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        // Config properties Hashmap to put in the kafka config
        Map<String, Object> configProps = new HashMap<>();

        // Set the bootstrap address provided to the correct property for the bootstrap servers config

        /* Now set the values for KEY_SERIALIZER_CLASS and VALUE_SERIALIZER_CLASS to the
           StringSerializer from org.apache.kafka.common.serialization.StringSerializer */
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Bean that exposed the KafkaTemplate for use in the main class
     * @return A new KafkaTemplate with required properties to connect with Kafka
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}