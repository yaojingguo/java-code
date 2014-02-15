package concurrency;

import java.util.concurrent.*;
// Run this class. Use the following command to check thread state:
//   ps -L -O stat,lwp,nlwp --pid 
// During looping 3 W threads are in Rl+ state. When they are sleeping, they are
// in Sl+ state. R means running or runnable. S means interruptible sleep.
//
// operation  state
// ---------------
// loop        Rl+
// synchonized Sl+
// wait        Sl+
// 
public class ThreadStateCheck {
    static class W extends Thread {
        CountDownLatch latch;
        public W(CountDownLatch latch) {
            this.latch = latch;
        }
        public void run() {
            System.out.println("looping...");    
            long i;
            for (i = 0; i < 10L * 1000L * 1000L * 1000L; i++)
                ;
            System.out.println("Sleeping " + i + "...");    
            Util.sleep(20 * 1000);
            latch.countDown();
        }
    }
    static void test1() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(3);
        W ws[] = new W[3];
        for (int i = 0; i < 3; i++) {
            ws[i] = new W(latch);
            ws[i].start();
        }
        System.out.println("await");    
        latch.await();
        System.out.println("passed latch");    
    }
    static void test2() throws InterruptedException {
      final Object lock = new Object();
      Thread t = new Thread() {
        public void run() {
          System.out.println("ready to acquire");  
          synchronized (lock) {
            System.out.println("lock acquired");  
          }
        }
      };
      synchronized(lock) {
        t.start();
        Thread.sleep(300 * 1000);
      }
    }
    static void test3() throws InterruptedException {
      final Object monitor = new Object();
      Thread t = new Thread() {
        public void run() {
          synchronized(monitor) {
            System.out.println("wait");  
            try {
              monitor.wait();
            } catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
            System.out.println("wake up");  
          };
        };
      };
      t.start();
      Thread.sleep(300 * 1000);
      synchronized(monitor) {
        monitor.notify();
      }
    }

    public static void main (String [] args) throws InterruptedException {
      test3();
    }
}
