package algorithm;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;

public class KSubsetsTest {
  @Test
  public void testSmall() {
    verify(3, 0);
    verify(3, 1);
    verify(3, 2);
    verify(3, 3);
  }
  @Test
  public void testBig() {
    verify(20, 10);
  }
  private void verify(int n, int k) {
    System.out.printf("C(%d, %d)\n", n, k);  
    KSubsets<Integer> ks = new KSubsets<Integer>(n, k);
    ks.generate();
  }
}
