package language;

import org.junit.Test;

import java.util.Locale;

public class FormatTest {
  @Test
  public void test() {
    for (int i : new int[]{0, 1, -1}) {
      System.out.printf("%010d\n", i);
    }
    for (int i : new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE +1, Integer.MAX_VALUE+2}) {
      System.out.printf("%d, %x\n", i, i);
    }
  }
}
