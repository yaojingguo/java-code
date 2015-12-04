package concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Usage {
  // A reader can't acquire the write lock
  public static void test1() {
    ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    Lock r = rw.readLock();
    Lock w = rw.writeLock();

    r.lock();
    w.lock();
  }
  // A write can acquire the reader lock
  public static void test2() {
    ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    Lock r = rw.readLock();
    Lock w = rw.writeLock();

    w.lock();
    r.lock();
  }
  public enum LockType {
    S, X;
  }
  public static void test3() {
    Map<Integer, LockType> map = new HashMap<Integer, LockType>();
    System.out.println(map.get(LockType.S));    
  }
  public static class Generator extends Thread {
    private final Object outer;
    private final Object inner;
    public Generator(Object outer, Object inner) {
      this.outer = outer;
      this.inner = inner;
    }
    @Override
    public void run() {
      synchronized (outer) {
        synchronized (inner) {
          inner.notify();
        }
      }
    }
  }
  public static class Receiver extends Thread {
    private final Object outer;
    private final Object inner;
    public Receiver(Object outer, Object inner) {
      this.outer = outer;
      this.inner = inner;
    }
    @Override
    public void run() {
      synchronized (outer) {
        synchronized (inner) {
          System.out.println("waiting...");    
          try {
            inner.wait();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }
      }
    }
  }
  public static void test4() {
    final Object outer = new Object();
    final Object inner = new Object();
    Receiver r = new Receiver(outer, inner);
    System.out.println("starting receiver...");    
    r.start();
    try {
      Thread.sleep(5 * 1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("starting generator...");    
    Generator g = new Generator(outer, inner);
    g.start();
  }
  public static void test5() {
    final Object monitor = new Object();
    final Semaphore s = new Semaphore(1);
    try { 
      s.acquire(1); 
      System.out.println("parent acquired");  
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    Thread t = new Thread() {
      @Override public void run() {
      synchronized (monitor) {
        System.out.println("child acquiring...");  
        try { 
          // acquire will not release the monitor.
          s.acquire(1); 
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      }
    };
    t.start();
    Util.sleep(5 * 1000);
    synchronized (monitor) {
      System.out.println("acquired the monitor");  
    }
  }
  public static void main (String [] args) {
    test5();
  }
}
