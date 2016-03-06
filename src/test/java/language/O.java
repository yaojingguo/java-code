package language;

class A {
}

class B extends A {
  @Override
  public boolean equals(Object obj) {
    System.out.println("Object");
    return super.equals(obj);
  }

  public boolean equals(A obj) {
    System.out.println("A");
    return true;
  }

  public boolean equals(B obj) {
    System.out.println("B");
    return true;
  }
}


public class O {
  public static void main(String[] args) {
    B b = new B();
    b.equals(new B());
    b.equals(new A());
    b.equals(new Object());
    b.equals((Object) new B());
  }
}
