package concurrency;

public class Daemon {
  public static void main(String[] args) throws Exception {
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
