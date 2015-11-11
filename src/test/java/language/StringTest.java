package language;

import static com.google.common.truth.Truth.*;

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

}
