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
}
