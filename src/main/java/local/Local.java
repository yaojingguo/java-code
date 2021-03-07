package local;

import static java.lang.Thread.*;

public class Local {
  public static void main(String[] args) {
    new Worker().start();
    new Worker().start();
  }
}

class Worker extends Thread {
  @Override
  public void run() {
    int id = ThreadId.get();
    System.out.printf("1. thread: %s, id: %d\n", currentThread().getId(), id);
    try {
    sleep(2 * 1000);
    } catch (InterruptedException ie) {
      throw new RuntimeException(ie);
    }
    System.out.printf("1. thread: %s, id: %d\n", currentThread().getId(), id);
  }
}