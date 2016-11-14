public class Recur {

  final int foo() {
    System.out.println("foo: " + a);
    return 10;
  }

  private int a = foo();

  public static void main(String[] args) {
    Recur r = new Recur();
    System.out.println("main: " + r.a);
  }

}
