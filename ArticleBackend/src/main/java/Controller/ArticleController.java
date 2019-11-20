package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
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

    @PostMapping(value = "/Article")
    public void postArticle(@RequestBody KafkaMessage kafkaMessage) {
        kafkaTemplate.send(topic, kafkaMessage.toString());
    }
}
