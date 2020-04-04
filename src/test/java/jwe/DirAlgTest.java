package jwe;

import com.nimbusds.jose.util.Base64URL;
import org.junit.Before;
import org.junit.Test;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import static com.google.common.truth.Truth.assertThat;

public class DirAlgTest {
  private String data = "hello, world";
  private SecretKey key;
  private Base64URL encryptionIv;

  @Before
  public void setUp() throws Exception {
    // Generate symmetric 128 bit AES key
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(128);
    key = keyGen.generateKey();
  }

  @Test
  public void testEncryptionAndDecryption() throws Exception {
    String jweString = encrypt();
    decrypt(jweString);
  }

  public String encrypt() throws Exception {
    System.out.printf("encrypting...\n");

    JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128GCM);
    System.out.printf("JWE header: %s\n", header);

    // Set the plain text
    Payload payload = new Payload(data);

    // Create the JWE object and encrypt it
    JWEObject jweObject = new JWEObject(header, payload);
    System.out.printf("iv before encryption: %s\n", jweObject.getIV());
    jweObject.encrypt(new DirectEncrypter(key));
    encryptionIv = jweObject.getIV();
    System.out.printf("iv after encryption: %s\n", encryptionIv);
    // Serialise to compact JOSE form...
    System.out.printf("JWE Object: %s\n", jweObject);
    String jweString = jweObject.serialize();
    System.out.printf("JWE string: %s\n", jweString);

    return jweString;
  }

  public void decrypt(String jweString) throws Exception {
    System.out.printf("encrypting...\n");
    // Parse into JWE object again...
    JWEObject jweObject = JWEObject.parse(jweString);
    Base64URL decryptionIv = jweObject.getIV();
    System.out.printf("iv after decryption: %s\n", decryptionIv);
    assertThat(decryptionIv).isEqualTo(encryptionIv);

    // Decrypt
    jweObject.decrypt(new DirectDecrypter(key));

    // Get the plain text
    Payload payload = jweObject.getPayload();
    assertThat(payload.toString()).isEqualTo(data);
  }
}
