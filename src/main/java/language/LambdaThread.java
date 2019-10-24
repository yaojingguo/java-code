package language;

public class LambdaThread {

  public static void main(String[] args) throws Exception {
    // new Thread(() -> // your code here).start();
    new Thread(
            () -> {
              try {
                Thread.sleep(5000);
              } catch (Exception ex) {
                throw new RuntimeException(ex);
              }
            })
        .start();
    Thread.sleep(5000);
  }
}
