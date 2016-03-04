package language;

import org.junit.Test;

/*

import math

e = math.exp(1.0)
print e

a = 20.0 * e / 2.0
a = a * a
a = a * math.exp(-20)
print a
print "prob: %.10f\n" % a
*/
public class MathTest {
  
  @Test
  public void testProb() {
    double e = Math.exp(1.0);
    System.out.println(e);
    
    double a = (20 * e /  2);
    a = a * a;
    a = a * Math.exp(-20);
    System.out.printf("%.10f\n", a);
  }

}
