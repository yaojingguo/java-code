/*

SIGINT        2       Term    Interrupt from keyboard
SIGTERM      15       Term    Termination signal (default for kill command)

SIGKILL       9       Term    Kill signal

ShutdownHook can catch SIGINT and SIGTERM. CTRL-C send SIGINT signal.
*/
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
