package language;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.google.common.truth.Truth.assertThat;

public class QueueTests {

  @Test
  public void testBasics() throws Exception {
    BlockingQueue<Integer> q = new LinkedBlockingQueue<>();
    int count = 3;

    fill(q, count);
    for (int i = 1; i <= count; i++) {
      assertThat(q.poll()).isEqualTo(i);
    }
    assertThat(q.size()).isEqualTo(0);
  }

  private void fill(BlockingQueue<Integer> q, int count) {
    for (int i = 1; i <= count; i++) {
      q.offer(i);
    }
  }
}
