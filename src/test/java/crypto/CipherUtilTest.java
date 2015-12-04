package crypto;
import javax.crypto.Cipher;

import org.junit.Test;

public class CipherUtilTest {
  static String cipherName = "AES/CBC/PKCS5Padding";
  static String d16 = "0123456789012345";
  static String d2 = "01";

  @Test
  public void test() throws Exception {
    verify(d2);
    verify(d16);
  }
  
  void verify(String str) throws Exception {
    Cipher ec = CipherUtil.encryptCipher();
    Cipher dc = CipherUtil.decryptCipher();
    System.out.println("input: " + str);
    byte[] data = str.getBytes("UTF-8");
    byte[] encrypted = ec.doFinal(data);
    byte[] decrypted = dc.doFinal(encrypted);
    String decryptedStr = new String(decrypted, "UTF-8");
    System.out.println("decrypted: " + decryptedStr);
    org.junit.Assert.assertEquals(str, decryptedStr);
  }
  
  void info(byte[] data) {
    System.out.printf("encrypted(%d): ", data.length);
    for (byte b: data) {
      System.out.printf("%02x", b & 0xFF);
    }
    System.out.println();
  }
}
