package jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

public class ConfigDecryptUtils {
    public static void main(String[] args) {
        // 加密工具
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        // 加密配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        // 自己在用的时候更改此密码
        config.setPassword("yao");
        // 应用配置
        encryptor.setConfig(config);
        String ciphertext = "orcz3QFUAGTc8Zx6ggvGrg==";
        // 解密
        String plaintext = encryptor.decrypt(ciphertext);
        System.out.println(ciphertext + " : " + plaintext);
    }
}
