package kafka;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Test;

public class ProducerTest {

  public static String topic = "apiTopic";

  private static KafkaProducer<String, String> createProducer() throws UnknownHostException {
    Properties config = new Properties();
    config.put("client.id", InetAddress.getLocalHost().getHostName());
    config.put("bootstrap.servers", "knode-1:9092");
    config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    return new KafkaProducer<>(config);
  }

  @Test
  public void testSend() throws Exception {
    try (KafkaProducer<String, String> kp = createProducer();) {
      ProducerRecord<String, String> record = new ProducerRecord<>(topic, "key1", "value70");
      Future<RecordMetadata> future = kp.send(record);
      System.out.println("writting...");
      future.get();
      System.out.println("written");
    }
  }

  public static void main(String[] args) throws Exception {
    try (KafkaProducer<String, String> kp = createProducer();) {
      ProducerRecord<String, String> record = new ProducerRecord<>(topic, "key2", "value2");
      kp.send(record, new Callback() {
        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
          System.out.printf("topic: %s\n", metadata.topic());
          System.out.printf("parition: %d\n", metadata.partition());
          System.out.printf("offset: %d\n", metadata.offset());
        }
      });
    }
  }

}
