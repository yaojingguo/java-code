package language;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

public class TimeTest {
  
  @Test
  public void test() {
    String s = "10:56:02  18:36:22";
    String[] fields = s.split(" +");
    String left = fields[0];
    String right = fields[1];
    LocalTime begin = LocalTime.parse(left);
    LocalTime end = LocalTime.parse(right);
    Duration duration = Duration.between(begin, end);
    long minutes = duration.toMinutes();
    System.out.printf("hours: %f\n", minutes / 60.0);
  }

}
