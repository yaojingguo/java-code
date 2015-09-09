package language;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;


public class MethodTest {
  
  @Test
  public void testVarArgs() {
    func("one", "two");
    func();
  }
  
  private void func(String... names) {
    List<String> list = new ArrayList<>();
    Collections.addAll(list, names);
    if (list.size() == 0) 
      list.add("zero");
    for (int i = 0; i < list.size(); i++)
      System.out.printf("%d: %s", i, list.get(i));
    System.out.printf("\n");
  }
}
