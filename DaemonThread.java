public class DaemonThread {
  public static void main(String[] args) {
    Worker w = new Worker();
    // w.setDaemon(true);
    w.start();
    idle(500); 
    System.exit(78);
  }

  public static class Worker extends Thread {
    @Override
    public void run() {
      System.out.println("I am running");
      idle(2000);
      System.out.println("exiting");
    }
  }

  private static void idle(int millis) {
    try {
      Thread.sleep(millis);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private static void test1() {
    Thread t = new Thread() {
      @Override
      public void run() {
        try { 
          System.out.println("Thread sleeping...");
          Thread.sleep(30 * 1000);
          System.out.println("Thread wake up");
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    };
    t.start();
    System.out.println("Main thread exit");  
    System.exit(0);
  }
}
