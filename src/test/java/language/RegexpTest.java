package language;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexpTest {

  // http://www.programcreek.com/2013/08/backreferences-in-java-regular-expressions/
  @Test
  public void restBackReferences1() {
    String str = "123123";
    Pattern p = Pattern.compile("(\\d\\d\\d)\\1");
    Matcher m = p.matcher(str);
    System.out.println(m.groupCount());
    while (m.find()) {
      String word = m.group();
      System.out.println(word + " " + m.start() + " " + m.end());
    }
  }
  
  @Test
  public void restBackReferences2() {
    String pattern = "\\b(\\w+)\\b[\\w\\W]*\\b\\1\\b";
    Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    String phrase = "unique is not duplicate but unique, Duplicate is duplicate.";
    Matcher m = p.matcher(phrase);
    while (m.find()) {
      String val = m.group();
      System.out.println("Matching subsequence is \"" + val + "\"");
      System.out.println("Duplicate word: " + m.group(1) + "\n");
    }  
  }
  
  @Test
  public void testAppendReplacement() {
    Pattern p = Pattern.compile("cat");
    Matcher m = p.matcher("one cat two cats in the yard");
    StringBuffer sb = new StringBuffer();
    while (m.find()) {
        m.appendReplacement(sb, "dog");
    }
    m.appendTail(sb);
    System.out.println(sb.toString());
  }

}
