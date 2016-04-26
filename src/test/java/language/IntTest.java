package language;

import org.junit.Test;

public class IntTest {
  
  @Test
  public void test() {
    int max = Integer.MAX_VALUE;
    int overflow = max + 10;
    System.out.printf("max: %d\n", max);
    System.out.printf("%d\n", max * max);
  }

  
  @Test
  public void test2() {
    long hash = 81;
    long Q = 139;
    hash = (hash + Q - 64 * 9 % Q) % Q;
    hash = (hash * 10 + 7) % Q;
    System.out.printf("hash: %d\n", hash);
  }
}
