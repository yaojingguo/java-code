package concurrency;

public class BoundedBuffer {
    private static final int N = 3;
    private int count;
    private int lastPosition;
    private int buffer[];

    /*
     * In Java, there is no way to associate 1 lock with 2 different conditions.  
     * So nonFull and nonEmpty are the same object.
     */
    private Object nonFull;
    private Object nonEmpty;

    public BoundedBuffer() {
        count = 0;
        lastPosition = 0;
        buffer = new int[N];
        nonFull = new Object();
        nonEmpty = nonFull;
    }

    public int remove() {
        int item;
        synchronized (nonEmpty) {
            while (count == 0) 
                Util.wait(nonEmpty);
            item = buffer[(lastPosition - count) % N];
            count--;
            nonFull.notifyAll();
        }
        return item;
    }

    public void append(int item) {
        synchronized (nonFull) {
            while (count == N) 
                Util.wait(nonFull);
            buffer[lastPosition % N] = item;
            lastPosition++;
            count++;
            nonEmpty.notifyAll();
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
