package concurrency;

public class ThreadState {
  static void test_blocked_state() {
    final Object m = new Object();
    Thread t = new Thread() {
      public void run() {
        synchronized (m) {
        };
      }
    };
    synchronized (m) {
      t.start();
      Util.sleep(10);
      System.out.println("Thread state: " + t.getState());    
    }
  }
  static volatile int start = 0;
  static void test_wait_state() {
    final Object m = new Object();
    final Thread t1 = new Thread() {
      public void run() {
        synchronized (m) {
          start = 1;
          Util.wait(m);
        }
      }
    };
    t1.start();
    Util.sleep(10 * 1000);
    System.out.println("state state: " + t1.getState());  
  }
  public static void main (String [] args) {
    test_blocked_state();
    test_wait_state();
  }
}
