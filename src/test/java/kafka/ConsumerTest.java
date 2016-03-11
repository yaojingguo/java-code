package kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

public class ConsumerTest {

  private static KafkaConsumer<String, String> createConsumer() {
    Properties props = new Properties();
    props.put("bootstrap.servers", "knode-1:9092");
    props.put("group.id", "consumer-tutorial");
    props.put("key.deserializer", StringDeserializer.class.getName());
    props.put("value.deserializer", StringDeserializer.class.getName());
    return new KafkaConsumer<>(props);
  }

  private volatile boolean running = true;

  @Test
  public void testConsume() {
    Thread flager = new Thread() {
      @Test
      public void run() {
        try {
          Thread.sleep(10 * 1000);
          running = false;
        } catch (InterruptedException ie) {
          throw new RuntimeException(ie);
        }
      }
    };
    flager.start();

    try (KafkaConsumer<String, String> consumer = createConsumer();) {
      consumer.subscribe(Arrays.asList(ProducerTest.topic));
      while (running) {
        ConsumerRecords<String, String> records = consumer.poll(1000);
        if (records.count() > 0)
          for (ConsumerRecord<String, String> record : records)
            System.out.println(record.offset() + ": " + record.value());
        else
          System.out.println("got nothing");
      }
    }
  }

  @Test
  public void testWakeup() {
    try (KafkaConsumer<String, String> consumer = createConsumer();) {
      Thread killer = new Thread() {
        @Override
        public void run() {
          try {
            Thread.sleep(20 * 1000);
            System.out.println("waking up...");
            consumer.wakeup();
          } catch (InterruptedException ie) {
            throw new RuntimeException();
          }
        }
      };
      killer.start();

      consumer.subscribe(Arrays.asList(ProducerTest.topic));
      while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
        for (ConsumerRecord<String, String> record : records)
          System.out.println(record.offset() + ": " + record.value());
      }
    } catch (WakeupException e) {
      System.out.println("woken");
    }
  }
}
