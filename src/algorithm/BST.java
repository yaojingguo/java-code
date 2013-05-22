package algorithm;

public class BST {
  private Node root;

  public BST insert(int v) {
    insert(new Node(v));
    return this;
  }

  public void delete(Node z) {
    if (z.left == null)
      transplant(z, z.right);
    else if (z.right == null) 
      transplant(z, z.left);
    else {
      Node y = min(z.right);
      if (y.parent != z) {
        transplant(y, y.right);
        y.right = z.right;
        y.right.parent = y;
      }
      transplant(z, y);
      y.left = z.left;
      y.left.parent = y;
    }
  }

  public Node min() {
    assert root != null;
    return min(root);
  }

  public Node max() {
    assert root != null;
    return max(root);
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
    System.out.println();  
  }

  private void inorder(Node x) {
    if (x != null) {
      System.out.print("(");  
      inorder(x.left);
      System.out.print(" " + x);  
      inorder(x.right);
      System.out.print(")");  
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

  public int minKey() {
    return min().key;
  }
  
  public int maxKey() {
    return max().key;
  }

  private Node min(Node x) {
    while (x.left != null) 
      x = x.left;
    return x;
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

  private void transplant(Node u, Node v) {
    if (u.parent == null)
      root = v;
    else if (u.parent.left == u)
      u.parent.left = v;
    else 
      u.parent.right = v;
    if (v != null)
      v.parent = u.parent;
  }
  
  private Node max(Node x) {
    while (x.right != null)
      x = x.right;
    return x;
  }

  static class Node {
    int key;
    Node left;
    Node right;
    Node parent;
    Node(int key) {
      this.key = key;
    }
    @Override
    public String toString() {
      return String.valueOf(key);
    }
  }
}

