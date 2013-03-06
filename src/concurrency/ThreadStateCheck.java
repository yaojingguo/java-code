package concurrency;

import java.util.concurrent.*;
// Run this class. Use the following command to check thread state:
//   ps -L -eO stat | grep java | grep -v grep
// During looping 3 W threads are in Rl+ state. When they are sleeping, they are
// in Sl+ state.
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
            System.out.println("Sleeping..." + i);    
            Util.sleep(20 * 1000);
            latch.countDown();
        }
    }
    public static void main (String [] args) throws InterruptedException {
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
}
