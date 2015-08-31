package concurrency;

import java.lang.reflect.Field;

public class FinalUsage {
  static void test_final_default_value() {
    Con c = new Con();
  }
  static void test_final_reflection() {
    try {
      Person p = new Person();
      System.out.println("name: " + p.name);
      Class cl = p.getClass();
      Field f = cl.getDeclaredField("name");
      f.setAccessible(true);
      f.set(p, "xiaoyu");
      System.out.println("name: " + p.name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  public static void main(String[] args) {
    test_final_default_value();
    test_final_reflection();
  }

  static class Con {
    private final int field1;
    public Con() {
      Thread t = new Thread() {
        public void run() {
          System.out.println("before assignment: " + field1);
        }
      };
      t.start();
      Util.join(t);
      field1 = 10;
      System.out.println("after assignment: " + field1);
    }
  }

  static class Person {
    public final String name;
    public Person() {
      name = "jingguo";
    }
  }
}
