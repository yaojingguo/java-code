package language;

import org.junit.Test;

public class DoubleTest {

  @Test
  public void testEquality() {
    // Find values such that (a == b) is true but x.equals(y) is false.
    System.out.println(0.0 == -0.0);
    System.out.println(Double.valueOf(0.0).equals(Double.valueOf(-0.0)));
    
    // Find values such that (a == b) is false but x.equals(y) is true.
    System.out.println(Double.NaN == Double.NaN);
    System.out.println(Double.valueOf(Double.NaN).equals(Double.valueOf(Double.NaN)));
  }
}
