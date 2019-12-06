package Controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
    private KafkaTemplate<String, String> kafkaTemplate;

    private final String topic = "Article";

    @Autowired
    public ArticleController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @CrossOrigin
    @PostMapping(value = "/Article")
    public void postArticle(@RequestBody KafkaMessage kafkaMessage) {
        kafkaTemplate.send(topic, kafkaMessage.toString());
    }

    @KafkaListener(topics = "Article")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        System.out.println(cr.value().toString());
    }
}
