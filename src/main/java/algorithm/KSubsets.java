package algorithm;

import java.util.BitSet;

public class KSubsets<E> {
  private int n;
  private int k;
  private BitSet binary;

  public KSubsets(int n, int k) {
    check(n, k);
    this.n = n;
    this.k = k;
    binary = new BitSet(n);
  }

  public void generate() {
    generate(n, k, binary);
  }

  private static void check(int n, int k) {
    assert k >= 0 && n > 0 && n >= k;
  }

  /**
   * C(n, k) = C(n - 1, k) +  C(n-1, k-1).
   * Some dynamic programming techinques can be used here for caching.
   */
  private static void generate(int n, int k, BitSet binary) {
    check(n, k);
    // System.out.printf("C(%d, %d)\n",  n, k);
    if (n == k)
      binary.set(0, n);
    if (k == 0)
      binary.clear(0, n);
    if (n == k || k == 0) {
      System.out.println(binary);  
      return;
    }
    binary.clear(n - 1);
    generate(n - 1, k, binary);
    binary.set(n - 1);
    generate(n - 1, k - 1, binary);
  }
}
