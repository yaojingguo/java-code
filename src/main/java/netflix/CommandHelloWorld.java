package netflix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandHelloWorld extends HystrixCommand<String> {

  private final String name;

  public CommandHelloWorld(String name) {
    super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
    this.name = name;
  }

  @Override
  protected String run() {
    System.out.printf("go\n");
    return "Hello " + name + "!";
  }
}
