package language;

import java.io.Closeable;
import java.io.IOException;

import org.junit.Test;



class One implements Closeable {
  @Override
  public void close() throws IOException {
    System.out.println("close one");
  }
}

class Two implements Closeable {
  @Override
  public void close() throws IOException {
    System.out.println("close two");
  }
}

public class TryTest {

  @Test
  public void testTry() throws IOException {
    try (One one = new One(); Two two = new Two();) {
    }
  }

}
