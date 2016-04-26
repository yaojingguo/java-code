package language;

import static com.google.common.truth.Truth.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StringTest {

  @Test
  public void testLowerCase() {
    assertThat("Φ".toUpperCase()).isEqualTo("Φ");
    assertThat("φ".toUpperCase()).isEqualTo("Φ");
  }


  @Test
  public void testFormat() {
    List list = new ArrayList();
    list.add("item1");
    String msg = String.format("1: %s, 2: %s, 3: %s", "one", "two", list);
    System.out.println(msg);
  }
  
  @Test
  public void testCharsetName() {
    assertThat(StandardCharsets.UTF_8.name()).isEqualTo("UTF-8");
  }
  
  @Test
  public void testList() {
    List<Character> ls = new ArrayList<>();
    ls.add('a');
    ls.add('b');
    System.out.printf(listToString(ls));
  }
  
  
  private String listToString(List<Character> ls) {
    StringBuilder sb = new StringBuilder();
    for (char c: ls)
      sb.append(c);
    return sb.toString();
  }
}
