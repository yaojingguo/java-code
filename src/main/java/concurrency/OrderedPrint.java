package concurrency;

public class OrderedPrint {
  private static int dfa[] = {1, 2, 0};
  private static Object lock = OrderedPrint.class;
  private static int curId = 0;

  static class Printer extends Thread {
    private int id;

    public Printer(int id) {
      this.id = id;
    }

    @Override
    public void run() {
      try {
        for (;;) {
          synchronized (lock) {
            while (curId != id)
              lock.wait();
            System.out.printf("%d", id);
            if (curId == 2) System.out.printf("\n");
            curId = dfa[curId];
            lock.notifyAll();
          }
        }
      } catch (InterruptedException ex) {
        throw new RuntimeException(ex);
      }
    }
  }

  public static void main(String[] args) {
    Printer array[] = new Printer[3];
    for (int i = 0; i < 3; i++)
      array[i] = new Printer(i);
    for (int i = 0; i < 3; i++)
      array[i].start();
  }
}
