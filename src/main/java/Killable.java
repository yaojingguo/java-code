public class Killable {
  private static final ShutdownHook shutdownHook = new ShutdownHook();

  private static void halt() {
    System.out.println("System halting...");
  }

  private static class ShutdownHook extends Thread {
    @Override
    public void run() {
      halt();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    System.out.println("Adding shutdown hook...");
    Runtime.getRuntime().addShutdownHook(shutdownHook);

    for (;;)
      Thread.sleep(1 * 1000);
  }
}
