package nio;


import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import sun.nio.ch.DefaultSelectorProvider;


public class Tests {

  @Test
  public void fileChannel() throws IOException {
    Path path = Paths.get("file.txt");
    FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE);
    System.out.println(fileChannel.position());

    byte[] chars = {'A', 'B', 'C'};
    ByteBuffer buf = ByteBuffer.wrap(chars);

    fileChannel.write(buf);

    System.out.println(fileChannel.position());

    System.out.println(fileChannel.size());
  }

  @Test
  public void buffer() {
    ByteBuffer fill = ByteBuffer.allocateDirect(1);

    System.out.println(fill.position(0));
    System.out.println("content: " + fill.get());
    System.out.println("remaining: " + fill.remaining());
  }

  @Test
  public void provider() {
    System.out.println(DefaultSelectorProvider.create().getClass().getName());
  }
}
