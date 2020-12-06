package scott.forthedition;

import java.util.HashMap;

public class Inspection {
  public static void main(String[] args) throws Exception {
//    zero();
//    one();
//    two();
//    three();
    four();
  }

  static void zero() {
    int[] A = new int[10];
    System.out.printf("class name: %s\n", A.getClass().getName());

    System.out.printf("class name: %s\n", "".getClass().getName());

    String[] C = new String[10];
    System.out.printf("class name: %s\n", C.getClass().getName());

    Foo[][] D = new Foo[10][10];
    System.out.printf("class name: %s\n", D.getClass().getName());
  }

  static void one() {
    HashMap<String, Integer> map = new HashMap<>();
    map.put("one", 1);
    System.out.printf("class name: %s\n", map.get("one").getClass().getName());
  }

  static void two() {
    Worker w = new Programmer();
    System.out.printf("class name: %s\n", w.getClass().getName());

    System.out.printf("class name: %s\n", Worker.class.getName());
  }

  static void three() throws ClassNotFoundException {
    Class stringClass = Class.forName("java.lang.String");
    System.out.printf("string class: %s\n", stringClass);
    Class intArrayClass = Class.forName("[I");
    System.out.printf("int array class: %s\n", intArrayClass);
  }

  static void four() {
    Worker w = new Programmer();
    System.out.printf("super class: %s\n", w.getClass().getSuperclass());

    Class[] classes = w.getClass().getClasses();
    System.out.printf("class count: %d\n", classes.length);
    System.out.printf("classes:\n");
    for (Class c: classes) {
      System.out.printf("\t%s\n", c.getName());
    }
  }
}

class Worker {

}

class Programmer extends Worker {
  public class First {

  }

  public class Second {

  }

}

class Foo {

}