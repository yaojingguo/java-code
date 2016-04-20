package language;

import org.junit.Test;

public class GenericTest {
  private static final int R = 256;
  
  private static class Node<E> {
    private E value;
//    private Node[] next = new Node<E>[R];
    private Node[] next = new Node[R];
  }
  @Test
  public void test() {
    
  }

}
