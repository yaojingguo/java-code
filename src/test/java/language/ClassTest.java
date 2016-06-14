package language;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ClassTest {
  
  @Test
  public void test() {
    List ls = new ArrayList();
    System.out.println(ls.getClass().getCanonicalName());
  }

}
