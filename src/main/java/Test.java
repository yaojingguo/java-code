public class Test {

  int h = 29;
  {
    h = 30;
  }
  int x = giveH();


  public int giveH() {
    return h;
  }

  public static void main(String args[]) {
    Test t = new Test();
    System.out.println("x: " + t.x);
    System.out.println("h: " + t.h);
  }
}
