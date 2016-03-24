import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Work implements Callable<String> {
  @Override
  public String call() throws Exception {
    System.out.println("call: before sleep");
    Thread.sleep(2 * 1000);
    System.out.println("call: after sleep");
    return "LV";
  }
}


public class FutureUsage {
  public static void main(String[] args) throws Exception {
//    Runtime.getRuntime().addShutdownHook(new Thread() {
//      @Override
//      public void run() {
//         System.out.println("shutdown hook invoked");
//      }
//    });

    ExecutorService executor = Executors.newFixedThreadPool(1);
    Work w = new Work();
    Future<String> f = executor.submit(w);
//    System.out.println(f.get());
    executor.shutdown();
  }
}
