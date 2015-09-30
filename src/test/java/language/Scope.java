package language;

public class Scope {
  private class A {}
  public class B extends A {}
  
  private static class X {}
  public class Y extends X {}
}
