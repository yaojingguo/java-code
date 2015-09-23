package language;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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
  
  @Test
  public void testConsumer() {
    System.out.println(invoke(this::setMap));
  }
  
  private Map invoke(Consumer<Map> setMap) {
    Map map = new HashMap();
    setMap.accept(map);
    return map;
  }
  
  private void setMap(Map map) {
    map.put("name", "yaojingguo");
  }
  
  @Test
  public void testCastToString() {
//    Object d = new Double(1.1);
//    String s = (String) d;
//    System.out.println(s);
//    String s = "abc";
//    System.out.println(s.toString());
//    System.out.println(null instanceof String);
//    Integer a = (Integer) null;
    String s = "1";
    Object o = s;
    Double num = (Double) o;
  }
  
  @Test
  public void testIntegerEquals() {
    Integer i1 = new Integer(1);
    Integer i2 = new Integer(1);
    System.out.println(i1 == i2);
    System.out.println(i1.equals(i2));
  }
  
  @Test
  public void testStringHashCode() {
    System.out.println("Foo".hashCode());
  }
}
