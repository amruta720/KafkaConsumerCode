import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.protocol.types.Field;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class KafkaSimpleConsumer {
    public static void main(String[] args) {
        // Set up Kafka consumer properties
        Properties properties = loadProperties("config.txt");

        // Create Kafka consumer
        Consumer<String, GenericRecord> consumer = new KafkaConsumer<>(properties);

        String topic = "topic_3";
        // Subscribe to the topic
        consumer.subscribe(Collections.singletonList(topic));

        //Store aggregated data
        Map<String, Map<String, Long>> aggregatedData= new HashMap<>();

        // Poll for messages
        while (true) {
            ConsumerRecords<String, GenericRecord> records = consumer.poll(Duration.ofMillis(100)); // Adjust the duration as needed
           // System.out.println("Consuming records");
            records.forEach(record -> {
                System.out.printf("Consumed record with key %s and device_id %s%n and upc %s%n", record.key(),record.value().get("device_id"), record.value().get("upc").toString());

            });
        }
    }
    private static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = KafkaSimpleConsumer.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.out.println("Sorry BABU, unable to find " + fileName);
                return properties;
            }

            // Load a properties file from class path
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }
}
