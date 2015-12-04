package crypto;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

public class GCMCipherTest {
  private static String cipherName = "AES/GCM/NoPadding";
  private static String ENC = "UTF-8";

  GCMParameterSpec gcmSpec() {
    return new GCMParameterSpec(128, new byte[16]);
  }

  Cipher cipher(int mode) throws Exception {
    SecretKeySpec key = key();
    Cipher c = Cipher.getInstance(cipherName);
    GCMParameterSpec gcm = gcmSpec();
    c.init(mode, key, gcm);
    return c;
  }

  @Test
  public void test() throws Exception {
    Cipher ec = cipher(Cipher.ENCRYPT_MODE);
    String dataStr = "xx";
    byte[] encrypted = ec.doFinal(bytes("xx"));
    Cipher dc = cipher(Cipher.DECRYPT_MODE);
    byte[] decrypted = dc.doFinal(encrypted);
    String decryptedStr = new String(decrypted, ENC);
    System.out.println(new String(decrypted));
    org.junit.Assert.assertEquals(dataStr, decryptedStr);
  }

  static SecretKeySpec key() {
    try {
      byte[] key = "ThisIsASecretKey".getBytes(ENC);
      if (key.length != 16) {
        throw new IllegalArgumentException("Invalid key size.");
      }
      return new SecretKeySpec(key, "AES");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  byte[] bytes(String s) {
    try {
      return s.getBytes("UTF-8");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
