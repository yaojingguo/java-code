package concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

class RW {
  private int i = 0;

  public synchronized void acquireExclusive() throws InterruptedException {
    while (i != 0)
      this.wait();
    i = -1;
  }
  public synchronized void acquireShared() throws InterruptedException {
    while (i < 0)
      this.wait();
    i++;
  }
  public synchronized void releaseExclusive() {
    i = 0;
    this.notifyAll();
  }
  public synchronized void releaseShared() {
    i--;
    if (i == 0)
      this.notify();
  }
}
public class Locking {
  public static void test_lock_by_other() {
    final ReentrantLock lock = new ReentrantLock();
    lock.lock();
    Thread worker = new Thread() {
      @Override
      public void run() {
        lock.unlock();
      }
    };
    worker.start();
    for (;;);
  }
  public static void test_RW() {
    try {
      final RW rw = new RW();
      System.out.println("locking by parent...");
      rw.acquireExclusive();
      Thread worker = new Thread() {
        @Override
        public void run() {
          System.out.println("unlocking by child...");    
          rw.releaseExclusive();
          System.out.println("unlocked by child");    
        }
      };
      worker.start();
      for (;;);
    } catch (InterruptedException e) {
      throw new RuntimeException(e); 
    }
  }
  public static void test_semaphore() {
    try {
      final Semaphore s = new Semaphore(1);
      System.out.println("acquire once");    
      s.acquire();
      Thread worker = new Thread() {
        @Override
        public void run() {
          try {
            // release in another thread
            System.out.println("releasing in child");    
            s.release();
            System.out.println("released in child");    
          } catch (Throwable e) {
            throw new RuntimeException(e);
          }
        }
      };
      worker.start();
      Thread.sleep(50);
      System.out.println("acquire twice");    
      s.acquire();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
  public static void main (String [] args) {
    //test_lock_by_other();
    //test_RW();
    test_semaphore();
  }
}
