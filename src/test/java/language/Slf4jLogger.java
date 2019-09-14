package language;

import org.apache.log4j.Logger;
import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class Slf4jLogger {
  
  private static Logger log = Logger.getLogger(Slf4jLogger.class);
  
  public static void main(String[] args) {
    log.error("error message");
    log.info("info message");
    System.out.printf("go");
  }

}
