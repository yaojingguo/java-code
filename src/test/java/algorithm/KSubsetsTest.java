package algorithm;

import org.junit.Test;

public class KSubsetsTest {
  @Test
  public void testSmall() {
    verify(3, 0);
    verify(3, 1);
    verify(3, 2);
    verify(3, 3);
  }
  @Test
  public void testBigN() {
    verify(100, 1);
    verify(100, 99);
  }

  @Test
  public void testBig() {
    // running cost is high
    // verify(20, 10);
  }
  private void verify(int n, int k) {
    System.out.printf("C(%d, %d)\n", n, k);  
    KSubsets<Integer> ks = new KSubsets<Integer>(n, k);
    ks.generate();
  }
}
