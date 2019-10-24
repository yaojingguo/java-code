package language;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
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

  @Test
  public void foo() {
    Map<String, Integer> map = new HashMap<>();
    map.put("one", 1);
    map.put("two", 2);
    map.put("three", 3);
    System.out.printf("map: %s\n", map);

    Set<String> keys = map.keySet();
    keys.remove("one");

    System.out.printf("set: %s\n", keys);
    System.out.printf("map: %s\n", map);
  }
  
  void func() {
  }
  
  @Test
  public void testName() {
    int funct = 1;
  }
}
