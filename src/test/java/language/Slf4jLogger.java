package language;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLogger {
  
  private Logger log = LoggerFactory.getLogger(getClass());
  
  @Test
  public void test() {
    log.error("error message");
    log.info("info message");
  }

}
