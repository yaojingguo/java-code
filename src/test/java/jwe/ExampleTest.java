package jwe;

import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.security.Key;

import static com.google.common.truth.Truth.assertThat;

public class ExampleTest {
  // https://tools.ietf.org/html/rfc7519#section-3.1
  // https://tools.ietf.org/html/rfc7515#appendix-A.1
  @Test
  public void testRFC7519Section_3_1() throws Exception {
    String header = "{\"typ\":\"JWT\",\r\n \"alg\":\"HS256\"}";
    String expectedHeaderAsciis =
        "[123, 34, 116, 121, 112, 34, 58, 34, 74, 87, 84, 34, 44, 13, 10, 32, 34, 97, 108, 103, 34, 58, 34, 72, 83, 50, 53, 54, 34, 125]";
    assertThat(toAsciis(header)).isEqualTo(expectedHeaderAsciis);
    String base64Header = Base64URL.encode(header).toString();
    assertThat(base64Header).isEqualTo("eyJ0eXAiOiJKV1QiLA0KICJhbGciOiJIUzI1NiJ9");

    String payload =
        "{\"iss\":\"joe\",\r\n"
            + " \"exp\":1300819380,\r\n"
            + " \"http://example.com/is_root\":true}";
    String base64Payload = Base64URL.encode(payload).toString();
    assertThat(base64Payload)
        .isEqualTo(
            "eyJpc3MiOiJqb2UiLA0KICJleHAiOjEzMDA4MTkzODAsDQogImh0dHA6Ly9leGFtcGxlLmNvbS9pc19yb290Ijp0cnVlfQ");

    String data = base64Header + "." + base64Payload;

    Mac mac = Mac.getInstance("HmacSHA256");
    String base64Key =
        "AyM1SysPpbyDfgZld3umj1qzKObwVMkoqQ-EstJQLr_T-1qS0gZH75aKtMN3Yj0iPS4hcgUuTwjAzZr1Z9CAow";
    byte[] byteBytes = new Base64(base64Key).decode();
    Key key = new SecretKeySpec(byteBytes, "HmacSHA256");
    mac.init(key);
    byte[] sign = mac.doFinal(data.getBytes());
    String base64Sign = Base64URL.encode(sign).toString();
    assertThat(base64Sign)
        .isEqualTo("dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk");

    String expectedJws =
        "eyJ0eXAiOiJKV1QiLA0KICJhbGciOiJIUzI1NiJ9."
            + "eyJpc3MiOiJqb2UiLA0KICJleHAiOjEzMDA4MTkzODAsDQogImh0dHA6Ly9leGFtcGxlLmNvbS9pc19yb290Ijp0cnVlfQ."
            + "dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk";
    assertThat(base64Header + "." + base64Payload + "." + base64Sign).isEqualTo(expectedJws);
  }

  @Test
  public void testRFC7919A_1() {

  }

  @Test
  public void testRFC7919A_2() {

  }
  
  private String toAsciis(String s) {
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < s.length(); i++) {
      if (i > 0) {
        sb.append(String.format(", ", i));
      }
      sb.append((int) s.charAt(i));
    }
    sb.append("]");
    return sb.toString();
  }

}
