package language;

import org.junit.Test;

import static com.google.common.truth.Truth.*;

public class StringTest {
  
  @Test
  public void testLowerCase() {
    assertThat("Φ".toUpperCase()).isEqualTo("Φ");
//    assertThat("ф".toUpperCase()).isEqualTo("Φ");
    assertThat("φ".toUpperCase()).isEqualTo("Φ");
  }

}
