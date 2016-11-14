class Counter {
  private int val = 0;
  public synchronized int safeGet() {
    return val;
  }
  public int unsafeGet() {
    return val;
  }
  public synchronized void increment() {
    val++;
  }
}

class Writer extends Thread {
  private Counter c;
  public Writer(Counter c) {
    this.c = c;
  }
  @Override
  public void run() {
    c.increment();
  }
}

class Reader extends Thread {
  private Counter c;
  public Reader(Counter c) {
    this.c = c;
  }
  @Override
  public void run() {
    int unsafeCount = c.unsafeGet();
    int safeCount = c.safeGet();
    if (unsafeCount > safeCount)
      throw new RuntimeException("unsafe count is larger than safe count");
  }
}

public class UnsafeRead {
  public static void main(String[] args) {
    Counter c = new Counter();
    Writer w = new Writer(c);
    Reader r = new Reader(c);
    w.start();
    r.start();
  }
}