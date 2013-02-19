package concurrency;

public class InterruptBeforeWait {  
  public static void test1() {
    Runner r = new Runner();  
    r.start();     
    r.interrupt();  
    System.out.println("interrupted");    
  }
  public static void test2() {
    Runner r = new Runner();  
    /*
     * A interrupt before the thread is started has no effect.
     */
    r.interrupt();  
    r.start();     
    System.out.println("interrupted");    
  }
  public static void main (String [] args) {  
    test1();
    // test2();
  }  
}  

class Runner extends Thread {  
  @Override  
  public void run() {  
    for (int i = 0; i < 1000; i++)  
      System.out.println("NO: " + i);    
    System.out.println("interruption status: " + isInterrupted());  
    synchronized (this) {  
      Util.wait(this);  
    }  
  }  
}  
