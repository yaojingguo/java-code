package language;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jLogger {
  private Logger log = LogManager.getLogger(getClass());
  
  @Test
  public void test() {
    log.error("error message");
    log.info("info message");
  }

}
