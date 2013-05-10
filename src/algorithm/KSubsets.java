package algorithm;

import java.util.*;

public class KSubsets<E> {
  private E array[];
  private int n;
  private int k;
  private BitSet binary;

  public KSubsets(E[] array, int k) {
    this.array = array;
    this.n = array.length;
    // check(n, k);
    this.k = k;
    binary = new BitSet(n);
  }

  public void generate() {
    generate(n, k, binary);
  }

  private static void check(int n, int k) {
    assert k >= 0 && n > 0 && n >= k;
    // System.out.println("n = " + n + ", k = " + k);  
  }

  /**
   * C(n, k) = C(n - 1, k) +  (n-1, k-1)
   */
  private static void generate(int n, int k, BitSet binary) {
    check(n, k);
    if (n == k)
      binary.set(0, n);
    if (k == 0)
      binary.clear(0, n);
    if (n == k || k == 0) {
      // System.out.println(binary);  
      return;
    }
    binary.clear(n - 1);
    generate(n - 1, k, binary);
    binary.set(n - 1);
    generate(n - 1, k - 1, binary);
  }

}
