package language;

import org.junit.Test;

public class ArrayTest {

  @Test
  public void testArraycopy() {
    int[] src = new int[] {1, 2, 3};
    int[] dest = new int[3];
    System.arraycopy(src, 0, dest, 0, 3);
    for (int n : dest)
      System.out.println(n);
  }

}
