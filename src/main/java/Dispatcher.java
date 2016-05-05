class Processor extends Thread {
    private Status status;

    public Processor(Status status) {
        this.status = status;
    }

    @Override
    public void run() {
        try {
            // TODO: is log4j.info thread safe?. 
            // TODO: access the update state of status.count in a thread-safe way
            System.out.printf("running %s\n with count=", getName(), status.count);
            // TODO: use random sleep value
            Thread.sleep(2 * 1000);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        } finally {
            synchronized (status.monitor) {
                status.count--;
                status.monitor.notify();
            }
        }
    }
}


class Status {
    // TODO: method to update limit value
    public int limit;
    public int count;
    public final Object monitor;

    public Status(int limit) {
        this.limit = limit;
        this.monitor = new Object();
    }
    // TODO: toString method
}


public class Dispatcher {

    public static void main(String[] args) {
        Status status = new Status(10);
        // TODO: more printf here
        // TODO: 
        for (;;) {
            synchronized (status.monitor) {
                while (status.count >= status.limit)
                    try {
                        status.monitor.wait();
                    } catch (InterruptedException ie) {
                        throw new RuntimeException(ie);
                    }
                status.count++;
            }
            // TODO: the overhead to create a thread is a not a big deal.
            Processor processor = new Processor(status);
            processor.start();
        }
    }
}
