package language;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

public class AtomicTest {
  private static AtomicReference<Object> objRef = new AtomicReference<Object>();

  static class Worker extends Thread {
    @Override
    public void run() {
      objRef.compareAndSet(null, new Object());
      System.out.printf("obj: %s\n", objRef.get());
    }
  }

  public static void main(String[] args) {
    Worker w1 = new Worker();
    Worker w2 = new Worker();
    w1.start();
    w2.start();
  }
  
  @Test
  public void testTimeUnit() {
    System.out.println(TimeUnit.SECONDS.toMillis(1));
  }
}
