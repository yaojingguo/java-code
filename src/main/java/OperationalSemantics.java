
public class OperationalSemantics {
  void method(int arg1) {
    System.out.println("method");
  }
  
  static OperationalSemantics e0() {
    System.out.println("e0");
    return new OperationalSemantics();
  }
  
  static int createInt() {
    System.out.println("createInt");
    return 10;
  }
  
  
  public static void main(String[] args) {
    e0().method(createInt());
  }
}
