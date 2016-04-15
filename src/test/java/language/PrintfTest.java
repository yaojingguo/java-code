package language;

import org.junit.Test;

public class PrintfTest {
  
  @Test
  public void test() {
    System.out.printf("%06X\n", 0xA);
    double result = Double.POSITIVE_INFINITY + 10;
    System.out.printf("%b\n", result > Double.POSITIVE_INFINITY);
  }

}
