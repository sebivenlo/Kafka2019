package SpringKafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(scanBasePackages = {"SpringKafka"})
public class SpringKafkaMain {
    /**
     * Logger that can be used to write messages to the console
     */
    private static Logger logger = LoggerFactory.getLogger(SpringKafkaMain.class);

    private Map<String, Integer> amountReads = new HashMap<>();

    @Autowired
    public SpringKafkaMain(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    // Run using gradlew bootRun or in IntelliJ use the gradle tasks in the right toolbar.
    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaMain.class, args);
    }

    /**
     * The template that is created using the KafkaProducerConfig
     */
    private final KafkaTemplate<String, String> template;

    /* TODO Assignment 2: Create a kafka listener/publisher
     * 1. Create a method listen that can listen on the kafka topic: "Article" and output the information to logger.info.
     *
     * The message is built up as follows:
     * {Ian, Politics, read}
     * The name is the username of the person browsing the news.
     * The second value represents the article that the user has read/created.
     * The third value is the action that stands for the type of operation the user performed, either a read or write of an article.
     *
     * 2. Knowing the above information, keep track of the number of times a certain article is read.
     *
     * 3. For every read of an article publish the current number of reads and the article name to a
     * different kafka topic called: "NrReads" using the kafka template.
     * Try it out using a listener on the "NrReads" topic.
     *
     * 4. Also whenever a write action is performed post the article name to a topic: "NewArticles".
     */
    @KafkaListener(topics = "Article")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info(cr.value().toString());
        String value = cr.value().toString();
        final String[] split = value.split(",");
        String name = split[0];
        String article = split[1];
        String action = split[2];

        // Assignment 2
        if (!amountReads.containsKey(article) && action.equals("Read")) {
            amountReads.put(article, 1);

            // Assignment 3
            template.send("NrReads", article + ", 1");
        } else if (action.equals("read")) {
            Integer integer = amountReads.get(article);
            amountReads.put(article, integer + 1);

            // Assignment 3
            template.send("NrReads", article + ", " + (integer + 1));
        }

        // Assignment 4
        if (action.equals("write")) {
            template.send("NewArticles", article);
        }
    }

    @KafkaListener(topics = "NrReads")
    public void listenReads(ConsumerRecord<?, ?> cr) {
        logger.info(cr.value().toString());
    }

    @KafkaListener(topics = "NewArticles")
    public void listenNewArticles(ConsumerRecord<?, ?> cr) {
        logger.info(cr.value().toString());
    }
}