package language;

public class ExceptionInThread {
  public static void main(String[] args) throws Exception {
    Thread t = new Thread() {
      @Override
      public void run() {
        try {
          throw new Exception();
        } catch (Exception ex) {
          throw new RuntimeException(ex);
        }
      }
     };
    t.start();

    System.out.println("sleeping in main...");
    Thread.sleep(10 * 1000);
    System.out.println("woke up");
  }
}
