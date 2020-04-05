package jwe;

import com.google.common.primitives.Bytes;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.List;

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
    assertThat(base64Sign).isEqualTo("dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk");

    String expectedJws =
        "eyJ0eXAiOiJKV1QiLA0KICJhbGciOiJIUzI1NiJ9."
            + "eyJpc3MiOiJqb2UiLA0KICJleHAiOjEzMDA4MTkzODAsDQogImh0dHA6Ly9leGFtcGxlLmNvbS9pc19yb290Ijp0cnVlfQ."
            + "dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk";
    assertThat(base64Header + "." + base64Payload + "." + base64Sign).isEqualTo(expectedJws);
  }

  // TODO(yaojingguo): complete this
  @Test
  public void testRFC7919A_1() throws Exception {
    // A.2.1.  JOSE Header
    String header = "{\"alg\":\"RSA1_5\",\"enc\":\"A128CBC-HS256\"}";
    assertThat(Base64URL.encode(header).toString()).isEqualTo("eyJhbGciOiJSU0ExXzUiLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0");

    // A.2.2.  Content Encryption Key (CEK)
    int[] cekInts = {
      4, 211, 31, 197, 84, 157, 252, 254, 11, 100, 157, 250, 63, 170, 106, 206, 107, 124, 212, 45,
      111, 107, 9, 219, 200, 177, 0, 240, 143, 156, 44, 207
    };
    byte[] cek = toBytes(cekInts);

    // A.2.3.  Key Encryption
//    String base64e = "AQAB";
//    String base64d =
//        "VFCWOqXr8nvZNyaaJLXdnNPXZKRaWCjkU5Q2egQQpTBMwhprMzWzpR8Sxq"
//            + "1OPThh_J6MUD8Z35wky9b8eEO0pwNS8xlh1lOFRRBoNqDIKVOku0aZb-ry"
//            + "nq8cxjDTLZQ6Fz7jSjR1Klop-YKaUHc9GsEofQqYruPhzSA-QgajZGPbE_"
//            + "0ZaVDJHfyd7UUBUKunFMScbflYAAOYJqVIVwaYR5zWEEceUjNnTNo_CVSj"
//            + "-VvXLO5VZfCUAVLgW4dpf1SrtZjSt34YLsRarSb127reG_DUwg9Ch-Kyvj"
//            + "T1SkHgUWRVGcyly7uvVGRSDwsXypdrNinPA4jlhoNdizK2zF2CWQ";
//    byte[] e = new Base64(base64e).decode();
//    byte[] d = new Base64(base64d).decode();
//    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//    KeySpec keySpec = new RSAPublicKeySpec(new BigInteger(e), new BigInteger(d));
//    PublicKey publicKey = keyFactory.generatePublic(keySpec);

    // A.2.4.  Initialization Vector
    int[] ivInts = {3, 22, 60, 12, 43, 67, 104, 105, 108, 108, 105, 99, 111, 116, 104, 101};
    byte[] iv = toBytes(ivInts);
    assertThat(Base64URL.encode(iv).toString()).isEqualTo("AxY8DCtDaGlsbGljb3RoZQ");

  }

  private byte[] toBytes(int[] ints) {
    List<Byte> ls = new ArrayList<>();
    for (int i : ints) {
      ls.add((byte) i);
    }
    return Bytes.toArray(ls);
  }

  private void infoArray(byte[] bytes) {
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < bytes.length; i++) {
      if (i != 0) {
        sb.append(", ");
      }
      sb.append(String.format("%d", 0xff & bytes[i]));
    }
    sb.append("]");
    System.out.printf("array: %s", sb.toString());
  }

  @Test
  public void testRFC7919A_2() {}

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
