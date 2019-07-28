package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;




public class Tests {

  public void testFileChannel() throws IOException {
    Path path = Paths.get("file.txt");
    FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE);
    System.out.println(fileChannel.position());

    byte[] chars = {'A', 'B', 'C'};
    ByteBuffer buf = ByteBuffer.wrap(chars);

    fileChannel.write(buf);

    System.out.println(fileChannel.position());

    System.out.println(fileChannel.size());
  }
}
