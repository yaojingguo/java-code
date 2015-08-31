
package concurrency;

import java.util.concurrent.*;

public class SynchronousQueueUsage {
  static void testPutAndTake() {
    final SynchronousQueue<Integer> q = new SynchronousQueue<Integer>();
    Thread putter = new Thread() {
      public void run() {
        try {
          Thread.sleep(10 * 1000);
          q.put(1);
          System.out.println("Put success");  
        } catch (InterruptedException ie) {
          throw new RuntimeException(ie);
        }
      }
    };
    Thread taker = new Thread() {
      public void run() {
        try {
          q.take();
          System.out.println("Take success");  
        } catch (InterruptedException ie) {
          throw new RuntimeException(ie);
        }
      }
    };
    putter.start();
    taker.start();
  }

  static void testOffer() {
    SynchronousQueue<Integer> q = new SynchronousQueue<Integer>();
    System.out.println(q.offer(10));  
  }

  public static void main (String [] args) {
    testOffer();
    testPutAndTake();
  }
}
