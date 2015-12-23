package language;
// Use `java -XshowSettings:all` to show heap size settings 
public class OutOfMemory {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    Object[] table = new Object[32];
    int _1G = 1024 * 1024 * 1024;
    for (int i = 0; i < 2; i++) {
      table[i] = new byte[_1G];
    }
  }
}
