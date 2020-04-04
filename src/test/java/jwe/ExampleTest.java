package jwe;


import com.nimbusds.jose.util.Base64URL;
import org.junit.Test;

public class ExampleTest {
  // Play with https://tools.ietf.org/html/rfc7515#appendix-A.1
  @Test
  public void test() {
//    System.out.printf("%s", Base64URL.encode("{\"alg\":\"RSA-OAEP\",\"enc\":\"A256GCM\"}"));
    String header = "{\"typ\":\"JWT\",\r\n \"alg\":\"HS256\"}";
  }
}
