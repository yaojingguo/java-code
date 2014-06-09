package concurrency;
/**
 * Bounded buffer as specified by the paper "Monitors: An Operating System
 * Structuring Concept".
 */
public class BoundedBuffer {
    private static final int N = 3;
    private int count;
    private int lastPosition;
    private int buffer[];

    private Object monitor;

    public BoundedBuffer() {
        count = 0;
        lastPosition = 0;
        buffer = new int[N];
        monitor = new Object();
        monitor = monitor;
    }

    public int remove() {
        int item;
        synchronized (monitor) {
            while (count == 0) 
                Util.wait(monitor);
            item = buffer[(lastPosition - count) % N];
            count--;
            monitor.notifyAll();
        }
        return item;
    }

    public void append(int item) {
        synchronized (monitor) {
            while (count == N) 
                Util.wait(monitor);
            buffer[lastPosition % N] = item;
            lastPosition++; // Integer overflow does not matter.
            count++;
            monitor.notifyAll();
        }
    }

    public static void append(BoundedBuffer buf, int item) {
        System.out.printf("appending: %d...\n", item);
        buf.append(item); 
    }

    public static void remove(BoundedBuffer buf) {
        System.out.printf("removing\n");
        System.out.printf("removed: %d\n", buf.remove());
    }

    public static void main (String [] args) {
       final BoundedBuffer buf = new BoundedBuffer();

       Thread worker = new Thread() {
           public void run() {
               remove(buf);
           }
       };
       worker.start();
    
       append(buf, 1);
       append(buf, 2);
       append(buf, 3);
    }
}
