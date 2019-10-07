package language;

public class ExceptionInMain {
  public static void main(String[] args) throws Exception {
    Thread t =
        new Thread() {
          @Override
          public void run() {
            try {
              System.out.println("sleeping in t...");
              Thread.sleep(10 * 1000);
              System.out.println("woke up");

            } catch (Exception ex) {
              throw new RuntimeException(ex);
            }
          }
        };
    t.start();

    throw new Exception();
  }
}
