package language;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class DateTimeTest {

  @SuppressWarnings("all")
  @Test
  public void testLocalDate() {
    LocalDate now = LocalDate.now();
    System.out.println(now);
    System.out.println(now.minusDays(6));

    Map map = new HashMap();
    map.put("abc", 1.1);
    map.put("def", "x");
    System.out.println(map);
  }

  @Test
  public void testLocalDateTime() {
    LocalDateTime now = LocalDateTime.now();
    System.out.println(now);
    System.out.println(now.toInstant(ZoneOffset.UTC));
  }

  @Test
  public void testNullToString() {
    System.out.println("abc" + null);
  }

  public static void a() {
    System.out.println("a begin");
    b();
    System.out.println("a end");
  }

  private static void b() {
    System.out.println("b begin");
    c();
    System.out.println("b end");
  }

  @SuppressWarnings("unused")
  private static void c() {
    System.out.println("c begin");
    if (true) throw new RuntimeException();
    System.out.println("c end");
  }

  public static void main(String[] args) {
    try {
      System.out.println("main begin");
      a();
      System.out.println("main end");
    } catch (Throwable th) {
      throw th;
    }
  }
}
