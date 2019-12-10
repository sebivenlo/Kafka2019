# Kafka2019
Kafka workshop 2019 repository.
The workshop is provided by Bader Ammoun and Ian Sultanis.

## Required programs for the workshop
1. First of all make sure to have docker and docker-compose installed.

2. The project is running on JDK 11, so make sure that is correctly installed as well.

## Starting up the project
1. First clone the repository.

2. Then run `docker-compose up` in the root of the repository, docker will now download and start kafka and zookeeper for you.
Note: If you are on Windows and you are using docker toolbox make sure to port forward the ports 9092 and 2181 from your virtualbox that docker uses. You can do so in the settings -> network -> advanced in the virtualbox gui.

3. Before you start working on the assignment make sure to start the ArticleBackend project. 
Use the command `./gradlew bootRun` on Linux/Mac or just `gradlew bootRun` on Windows while in the root of that project, you can also start it from IntelliJ or another IDE if it has support for gradle.

4. You should now be able to work on the assignment provided, you can choose the ApacheKafka assignment or the SpringKafkaAssignment by looking in the packages of the project.

5. The frontend can be used to send messages to the backend and in turn the backend will publish those messages to the "Article" topic.

## Documentation for the libraries

### Apache Kafka documentation
https://kafka.apache.org/documentation/
And also the javadocs provided like for instance: 
https://kafka.apache.org/23/javadoc/index.html?org/apache/kafka/clients/producer/KafkaProducer.html

### Spring Kafka documentation
https://docs.spring.io/spring-kafka/reference/html/ Chapter 3 and onwards.
