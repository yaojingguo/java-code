package algorithm;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;

public class KSubsetsTest {
  @Test
  public void testGenerate() {
    Integer array[] = new Integer[20];
    for (int i = 0; i < array.length; i++)
      array[i] = i;
    KSubsets<Integer> ks = new KSubsets<Integer>(array, 10);
    ks.generate();
  }
}
