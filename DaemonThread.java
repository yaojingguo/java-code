public class DaemonThread {
  public static void main(String[] args) {
    // exitWithRunningThread();
    // await();
    kill();
  }

  // System.exit terminates JVM execution even there is a running non-daemon
  // thread.
  private static void exitWithRunningThread() {
    Worker w = new Worker();
    w.start();
    int millis = 500;
    System.out.printf("waiting some time %dms...\n", millis);
    idle(500); 
    System.out.println("resumed execution.");
    System.exit(78);
  }

  public static class Worker extends Thread {
    @Override
    public void run() {
      System.out.println("I am running");
      idle(10 * 1000);
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

  private static void await() {
    Thread t = new Thread() {
      @Override
      public void run() {
        System.out.println("Thread sleeping...");
        idle(10 * 1000);
        System.out.println("Thread wake up");
      }
    };
    t.setDaemon(true);
    t.start();
    // With a deamon thread, the followning message does not show up.
    System.out.println("Main thread returning");  
  }
  // kill can't terminate the hook execution. But kill -9 can.
  private static void kill() {
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        System.out.println("sleeping...");
        idle(200 * 1000);
        System.out.println("woke up");
      }
    });
    System.out.println("finished main");
  }
}
