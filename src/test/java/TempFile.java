

import java.io.File;
import java.io.IOException;

public class TempFile {
  
  public static void main(String[] args) throws IOException {
    File f = File.createTempFile("prefix", "suffix");
    System.out.println(f);
  }

}
