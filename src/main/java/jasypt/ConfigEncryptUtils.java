package jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

/**
 *把密文放到配置文件中的时候要注意：
 * ENC(密文)
 * @author 杨尚川
 */
public class ConfigEncryptUtils {
    public static void main(String[] args){
        //加密工具
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        //加密配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        //自己在用的时候更改此密码
        config.setPassword("yao");
        //应用配置
        encryptor.setConfig(config);
        String plaintext="root";
        //加密
        String ciphertext=encryptor.encrypt(plaintext);
        System.out.println(plaintext + " : " + ciphertext);
    }
}