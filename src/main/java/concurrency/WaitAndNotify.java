package concurrency;

public class WaitAndNotify {
   public static void main (String [] args) {
     Object monitor = new Object();
     Receiver r = new Receiver(monitor);
     r.start();
     Sender s = new Sender(monitor);
     s.start();
   }
}
class Sender extends Thread {
  private Object monitor;
  public Sender(Object m) {
    monitor = m;
  }
  @Override
  public void run() {
    synchronized(monitor) {
      System.out.println("ready to notify");  
      monitor.notify();
      System.out.println("notified");  
      Util.sleep(10000);
      System.out.println("ready to release lock");  
    }
  }
}
class Receiver extends Thread {
  private Object monitor;
  public Receiver(Object m) {
    monitor = m;
  }
  @Override
  public void run() {
    synchronized(monitor) {
      System.out.println("ready to wait");  
      /*
       * wait returns only after the notifying thread releases the monitor.
       */
      Util.wait(monitor);
      System.out.println("wakened");  
    }
  }
}
