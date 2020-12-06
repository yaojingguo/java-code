package concurrency.locks;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class FIFOMutex {
  private final AtomicBoolean locked = new AtomicBoolean(false);
  private final Queue<Thread> waiters = new ConcurrentLinkedQueue<Thread>();

  public void lock() {
    boolean wasInterrupted = false;
    Thread current = Thread.currentThread();
    waiters.add(current);

    // Block while not first in queue or cannot acquire lock
    while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
      LockSupport.park(this);
      // ignore interrupts while waiting
      if (Thread.interrupted()) wasInterrupted = true;
    }

    waiters.remove();
    // reassert interrupt status on exit
    if (wasInterrupted) current.interrupt();
  }

  public void unlock() {
    locked.set(false);
    LockSupport.unpark(waiters.peek());
  }

  static class Worker extends Thread {
    private FIFOMutex mutex;

    public Worker(FIFOMutex mutex) {
      this.mutex = mutex;
    }
    @Override
    public void run() {
      mutex.lock();
      relax(1000 * 4);
      mutex.unlock();
    }
  }

  static void relax(long millis) {
    try {
      System.out.printf("Relaxing %d\n", millis);
      Thread.sleep(millis);
      System.out.printf("Woke up\n");
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) {
    FIFOMutex mutex = new FIFOMutex();
    Worker w1 = new Worker(mutex);
    Worker w2 = new Worker(mutex);
    w1.start();
    w2.start();
  }
}
