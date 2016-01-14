package language;

import org.junit.Test;

public class SyntaxTest {

  @Test
  public void testElse() {
    if (true) 
      if (true)
        System.out.println("one");
      else
        System.out.println("two");
  }

}
