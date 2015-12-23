package language;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class MapTest {
  
  @Test
  public void testTreeMap() {
    TreeMap map = new TreeMap();
    map.put("0-", "zero");
    map.put("1", "one");
    System.out.println(map);
  }
  
  @Test
  public void testLinkedHashMap() {
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    map.put("0-", "zero");
    map.put("1", "one");
    System.out.println(map);
    for (Map.Entry entry : map.entrySet()) {
    }
  }
  
  @Test
  public void testNullValues() {
    Map map = new HashMap();
    map.put("one", null);
    map.put("two", null);
    System.out.println(map);
  }
  
  void func() {
  }
  
  @Test
  public void testName() {
    int funct = 1;
  }
}
