import java.io.IOException;


public class Close {
  
  public static void main(String[] args) {
    closeStdin();
  }
  
  // Shows the effect of closing stdin
  static void closeStdin() {
    int ch = 0;
    try {
      System.in.close();
      ch = System.in.read();
      System.out.printf("\nch: %c\n", ch);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  // Shows the effect of closing stdout
  static void closeStdout() {
    System.out.close();
    System.out.println("ABC");   
  }
  
  // Shows the effect of closing stderr
  static void closeStderr() {
    System.err.close();
    System.err.println("ABC");
  }
}
