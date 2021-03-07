package reflection;

public class Foo {
  private String name;

  public Foo(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "name: " + name;
  }
}
