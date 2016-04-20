package language;

import org.junit.Test;

public class SuffixTest {

  @Test
  public void test() {
    show("suffixsort");
    show("sortsuffix");
  }

  private void show(String s) {
    int len = s.length();

    String suffixes[] = new String[len];

    for (int i = 0; i < len; i++) {
      suffixes[i] = s.substring(i) + "|" + s.substring(0, i);
    }

    for (int i = 0; i < len; i++)
      System.out.println(suffixes[i]);
    System.out.println();
  }

}
