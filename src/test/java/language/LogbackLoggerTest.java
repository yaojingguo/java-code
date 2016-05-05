package language;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackLoggerTest {

  private Logger log = LoggerFactory.getLogger(getClass());

  @Test
  public void test() {
    log.info("info message");
    log.error("error message");
  }
}
