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
}
