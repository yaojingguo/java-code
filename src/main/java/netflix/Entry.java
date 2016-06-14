package netflix;


import java.util.concurrent.Future;

import rx.Observable;

public class Entry {
  private static void one() {
    String s = new CommandHelloWorld("Bob").execute();
  }
 
  private static void two() {
    Future<String> s = new CommandHelloWorld("Bob").queue();  
  }
  
  private static void three() {
    Observable<String> s = new CommandHelloWorld("Bob").observe();  
  }
  
  public static void main(String[] args) {
    one();
    two();
    three();
  }

}
