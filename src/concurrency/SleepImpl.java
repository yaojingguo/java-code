package concurrency;

// A possible implementation of sleep
public class SleepImpl {  
  public static void main (String [] args) throws InterruptedException {  
    System.out.println("sleep 1 s");  
    Sleeper.sleep(1000);  
    System.out.println("sleep 5 s");    
    Sleeper.sleep(5 * 1000);  
    System.out.println("sleep 10 s");    
    Sleeper.sleep(10 * 1000);  
    System.out.println("sleep 20 s");    
    Sleeper.sleep(20 * 1000);  
  }  
}  

class Sleeper {  
  public static void sleep(long millis) throws InterruptedException {  
    Object monitor = new Object();  
    synchronized(monitor) {  
      monitor.wait(millis);  
    }  
  }  
}  
