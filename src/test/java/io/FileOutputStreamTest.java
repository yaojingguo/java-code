package io;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class FileOutputStreamTest {

  private String pathname1 = "data1.txt";
  private String pathname2 = "data2.txt";

  @Test
  public void testAppend() throws IOException {
    try (FileOutputStream out = new FileOutputStream(pathname1, false)) {
      out.write(0xFF & 'x');
    }
    
    try (FileOutputStream out = new FileOutputStream(pathname2, true)) {
      out.write(0xFF & 'x');
    }
  }
}
