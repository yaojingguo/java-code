package crypto;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CipherUtil {
  private static String cipherName = "AES/CBC/PKCS5Padding";
  private static byte[] key = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5};
  private static byte[] iv = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5};

  private static SecretKeySpec keySpec() {
    return new SecretKeySpec(key, "AES");
  }

  private static IvParameterSpec ivSpec() {
    return new IvParameterSpec(iv);
  }

  public static Cipher encryptCipher() {
    return cipher(Cipher.ENCRYPT_MODE);
  }

  public static Cipher decryptCipher() {
    return cipher(Cipher.DECRYPT_MODE);
  }

  private static Cipher cipher(int mode) {
    try {
      Cipher cipher = Cipher.getInstance(cipherName);
      cipher.init(mode, keySpec(), ivSpec());
      return cipher;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
