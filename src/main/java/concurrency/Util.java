package concurrency;

public class Util {
  public static void sleep(int mills) {
    try {
      Thread.sleep(mills);
    } catch (InterruptedException ex) {
      throw new RuntimeException(ex);
    }
  }
  public static void wait(Object monitor) {
    try {
      monitor.wait();
    } catch (InterruptedException ex) {
      throw new RuntimeException(ex);
    }
  }
  public static void sleep(long mills) {
    try {
      Thread.sleep(mills);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
  public static void join(Thread t) {
    try {
      t.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
