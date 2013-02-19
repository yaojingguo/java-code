package concurrency;

import java.util.concurrent.*;

class Item {
  @Override
  public String toString() {
    return "item-0";
  }
}

class Producer extends Thread {
  private BlockingQueue<Item> queue;
  public Producer(BlockingQueue<Item> queue) {
    this.queue = queue;
  }
  @Override
  public void run() {
    try {
      System.out.println(this + " putting an item...");  
      Item item = new Item();
      queue.put(item);
      System.out.println(this + " put an " + item);  
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}

class Consumer extends Thread {
  private BlockingQueue<Item> queue;
  public Consumer(BlockingQueue<Item> queue) {
    this.queue = queue;
  }
  @Override
  public void run() {
    try {
      System.out.println(this + " taking an item...");  
      Item item = queue.take();
      System.out.println(this + " took an " + item);  
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}

public class ProducerConsumer {
  public static void main (String [] args) {
    BlockingQueue<Item> queue = new ArrayBlockingQueue<Item>(1);
    Producer p = new Producer(queue);
    Consumer c = new Consumer(queue);
    p.start();
    c.start();
  }
}
