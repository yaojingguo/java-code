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
            for (;;) 
                if (interrupted())
                    throw new InterruptedException();
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
                if (isInterrupted())
                    throw new InterruptedException();
        }
    }

    public static void main (String [] args) {
        Thread t;
        t = new Worker1();
        t.start();
        t.interrupt();
        t = new Worker2();
        t.start();
        t.interrupt();
    }
}
