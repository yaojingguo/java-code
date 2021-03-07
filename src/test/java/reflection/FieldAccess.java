package reflection;

import org.junit.Test;

import java.lang.reflect.Field;

public class FieldAccess {

  @Test
  public void test() throws Exception {
    Class cls = Foo.class;
    Foo xiaoyu = new Foo("xiaoyu");
    Field field = cls.getDeclaredField("name");
    field.setAccessible(true);
    String name = (String) field.get(xiaoyu);
    System.out.printf("name: %s\n", name);
  }
}
