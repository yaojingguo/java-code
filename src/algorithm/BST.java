package algorithm;

public class BST {
  private Node root;

  public void insert(int v) {
    insert(new Node(v));
  }

  private void insert(Node x) {
    Node y = root;
    Node z = null;
    while (y != null) {
      z = y;
      if (x.key < y.key)
        y = y.left;
      else
        y = y.right;
    }
    x.parent = z;
    if (z == null) {
      root = x;
    } else if (x.key < z.key) {
      z.left = x;
    }  else {
      z.right = x;
    }
  }

  public Node min() {
    assert root != null;
    return min(root);
  }

  private Node min(Node x) {
    while (x.left != null) 
      x = x.left;
    return x;
  }

  public Node max() {
    assert root != null;
    return max(root);
  }

  private Node max(Node x) {
    while (x.right != null)
      x = x.right;
    return x;
  }

  public Node search(int v) {
    Node x = root;
    while (x != null && x.key != v)
      if (v < x.key)
        x = x.left;
      else
        x = x.right;
    return x;
  }

  public void inorder() {
    inorder(root);
  }

  private void inorder(Node x) {
    if (x != null) {
      inorder(x.left);
      System.out.println(x);  
      inorder(x.right);
    }
  }
        
  public Node successor(Node x) {
    if (x.right != null)
      return min(x.right);
    Node y = x.parent;
    while (y != null && y.right == x)
      y = y.parent;
    return y;
  }

  public Node predecessor(Node x) {
    if (x.left != null)
      return max(x.left);
    Node y = x.parent;
    while (y != null && y.left == x)
      y = y.parent;
    return y;
  }
  
  // TODO delete
  
  static class Node {
    int key;
    Node left;
    Node right;
    Node parent;
    Node(int key) {
      this.key = key;
    }
  }
}

