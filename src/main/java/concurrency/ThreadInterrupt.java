package concurrency;

public class ThreadInterrupt {
  static class Worker1 extends Thread {
    public void run() {
      try {
        work();
      } catch (InterruptedException e) {
        System.out.println("interrupt status: " + isInterrupted());
        e.printStackTrace();
      }
    }

    private void work() throws InterruptedException {
      for (;;);
      // if (interrupted()) throw new InterruptedException();
    }
  }

  static class Worker2 extends Thread {
    public void run() {
      try {
        work();
      } catch (InterruptedException e) {
        System.out.println("interrupt status: " + isInterrupted());
        e.printStackTrace();
      }
    }

    private void work() throws InterruptedException {
      for (;;)
        if (isInterrupted()) throw new InterruptedException();
    }
  }

  static class Sleeper extends Thread {
    @Override
    public void run() {
      try {
        Thread.sleep(10 * 60 * 1000);
      } catch (InterruptedException itEx) {
        System.out.println("Interrupted when sleeping");
        throw new RuntimeException(itEx);
      }
    }
  }

  public static void main(String[] args) throws Exception {
//    Thread t;
//    t = new Worker1();
//    t.start();
//    t.interrupt();
    
    Sleeper sleeper = new Sleeper();
    sleeper.start();
    Thread.sleep(100);
    sleeper.interrupt();

    // t = new Worker2();
    // t.start();
    // t.interrupt();
  }
}
