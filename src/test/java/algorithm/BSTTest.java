package algorithm;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithm.BST.Node;

public class BSTTest {
  @Test
  public void test() {
    BST tree = new BST();
    tree.insert(2).insert(1).insert(3);
    tree.inorder();
    assertEquals(1, tree.minKey());
    assertEquals(3, tree.maxKey());
    tree.insert(8).insert(10).insert(11);
    assertEquals(1, tree.minKey());
    assertEquals(11, tree.maxKey());
    Node n10 = tree.search(10);
    assertEquals(10, n10.key);
    Node n11 = tree.successor(n10);
    assertEquals(11, n11.key);
    assertEquals(8, tree.predecessor(n10).key);
    tree.inorder();
    tree.delete(n11);
    tree.inorder();
    assertEquals(10, tree.maxKey());
  }
}
