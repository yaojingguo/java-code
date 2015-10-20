package io;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class FileOutputStreamTest {

  private String pathname = "data1.txt";

  @Test
  public void testAppend() throws IOException {
    try (FileOutputStream out = new FileOutputStream(pathname, false)) {
      out.write(0xFF & 'x');
    }
  }
}
