package language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ToStringTest {
  
  @Test
  public void test() {
    int seam[] = new int[]{10, 20, 30};
    String s = Arrays.asList(seam).toString();
    System.out.printf("array: %s\n", seam);
    System.out.printf("seam: %s\n", s);
    
    List ls = new ArrayList();
    ls.add(10);
    ls.add(20);
    ls.add(30);
    
    System.out.printf("list: %s\n", ls);
  }

}
