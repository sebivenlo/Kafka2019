package SpringKafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication(scanBasePackages = {"SpringKafka"})
public class SpringKafkaMain {
  /**
   * Logger that can be used to write messages to the console
   */
  private static Logger logger = LoggerFactory.getLogger(SpringKafkaMain.class);

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
   * {Ian, Politics, Read}
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
}

