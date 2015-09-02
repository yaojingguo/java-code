package language;

import java.math.BigDecimal;

import org.junit.Test;

public class FloatTest {
  
  @Test
  public void testFloat() {
    Float val = Float.valueOf(0.1f);
    System.out.println(val);
    System.out.printf("%x\n", Float.floatToIntBits(0.1f));
    
    int number = 0b10011001100110011001101;
    System.out.println("===========");
    System.out.println(number);
    
    BigDecimal t = new BigDecimal(number);
    t = t.divide(new BigDecimal(1 << 23));
    System.out.println(t);
    t = t.add(new BigDecimal(1));
    t = t.divide(new BigDecimal(16));
    System.out.println(t);
    
    BigDecimal one = new BigDecimal(1);
    one = one.divide(new BigDecimal(2));
    System.out.println(one);
    System.out.println("== toString ==");
    System.out.println(Float.toString(0.1f));
  }
  
  @Test
  public void testFloatToString() {
    System.out.println(Float.toString(Float.NaN));
    System.out.println(Float.toString(Float.POSITIVE_INFINITY));
    System.out.println(Float.toString(Float.NEGATIVE_INFINITY));
    System.out.println(0.0);
    System.out.println(-0.0);
    System.out.println(100.99f);
    System.out.println(10000000f - 1);
    
    System.out.println("~~~~~~~~~~~~~~~~~~~~");
    System.out.println(0.1);
    System.out.printf("%f\n", 0.1);
    System.out.printf("%.14f\n", 0.1);
    float f = 0.1f;
    System.out.printf("%f\n", f);
    System.out.printf("%.14f\n", f);
  }
  
  @Test
  public void testVarArgs() {
    func("one", "two");
  }
  
  private void func(String... names) {
    for (int i = 0; i < names.length; i++)
      System.out.printf("%d: %s", i, names[i]);
  }
}
