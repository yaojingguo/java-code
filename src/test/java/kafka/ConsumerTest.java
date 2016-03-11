package kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

public class ConsumerTest {

  @Test
  public void testConsume() {
    Properties props = new Properties();
    props.put("bootstrap.servers", "knode-1:9092");
    props.put("group.id", "consumer-tutorial");
    props.put("key.deserializer", StringDeserializer.class.getName());
    props.put("value.deserializer", StringDeserializer.class.getName());
    try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);) {
      consumer.subscribe(Arrays.asList(ProducerTest.topic));
      
      while (true) {
        ConsumerRecords<String, String> records = consumer.poll(1000);
        for (ConsumerRecord<String, String> record: records)
          System.out.println(record.offset() + ": " + record.value());
      } 
    }

  }
}
