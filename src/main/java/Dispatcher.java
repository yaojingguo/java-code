import java.util.concurrent.ThreadLocalRandom;

class Processor extends Thread {
    private Cond cond;
    private int seconds;

    public Processor(Cond status, int seconds, int no) {
        this.cond = status;
        this.seconds = seconds;
        setName("processor-" + no);
    }

    @Override
    public void run() {
        try {
            // The output might be scrambled since no coordination is used here.
            System.out.printf("running %s\n", getName());
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        } finally {
            cond.release();
        }
    }
}

class Cond {
    private int limit;
    private int count;

    public Cond(int limit) {
        this.count = 0;
        this.limit = limit;
    }

    public int acquire() throws InterruptedException {
        synchronized (this) {
            while (count >= limit)
                wait();
            return count++;
        }
    }

    public void release() {
        synchronized (this) {
            count--;
            notify();
        }
    }
    
    public void reset(int limit) {
        synchronized (this) {
            int oldLimit = this.limit;
            this.limit = limit;
            notify();
            System.out.printf("limit updated from %d to %d\n", oldLimit, limit);
        }
    }

    @Override
    public String toString() {
        return "condition{count: " + count + ", limit: " + limit + "}";
    }
}

public class Dispatcher {
    public static void main(String[] args) throws InterruptedException {
        final Cond cond = new Cond(5);

        // Invoke reset after some time
        Thread operator = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException ie) {
                    throw new RuntimeException(ie);
                }
                cond.reset(100);
            }
        };
        operator.start();

        for (;;) {
            int no = cond.acquire();
            // If the overhead to create a thread is a concern, a thread pool can be used.
            Processor processor = new Processor(cond, nextInt(5), no);
            processor.start();
        }
    }

    // Random int in the range [1, max]
    private static int nextInt(int max) {
        return ThreadLocalRandom.current().nextInt(max) + 1;
    }
}
