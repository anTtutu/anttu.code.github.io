package com.anttu.secret.rsa;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 描述
 *
 * @ClassName：RSAUtils
 * @Description：RSA 加密算法工具类
 * @author：Anttu
 * @Date：24/8/2021 15:45
 */
public class RSAUtils {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";

    // 测试公钥
    public static String publicKeyText = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApBYbJ8fZKXX0jXOLxhwEN6Re427z/RzO\n" +
            "y/XuFHiWA0FevYf+vgYGNciyjGbTY5IcmDqDl1aB0oHJFPFtbXF31Kt66g4q6g4o1JJI37TzYMRk\n" +
            "RCzj4GFXIv4SUXluAcyPGPf0AsnlfN+T4jGsP6k8Mi1U3jeB/ammcmXggrbcdz8ArH+UqBaI/Yjl\n" +
            "ScqB0ovIv3nsDsHPd+pChZpXqBtdYhTBk8Pg9HPERzdbcSSEOllM6RMOPVy9jCckjnf+KQZbrdFk\n" +
            "vJbKlsVR/7QKOnLqwIyj0IIEgZtX7kG/rUivgKjbqlFx3zGTScp3QbNBPXmWy2nvC/B0SPSOg91m\n" +
            "neLPzwIDAQAB";
    // 测试私钥
    public static String privateKeyText = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCkFhsnx9kpdfSNc4vGHAQ3pF7j\n" +
            "bvP9HM7L9e4UeJYDQV69h/6+BgY1yLKMZtNjkhyYOoOXVoHSgckU8W1tcXfUq3rqDirqDijUkkjf\n" +
            "tPNgxGRELOPgYVci/hJReW4BzI8Y9/QCyeV835PiMaw/qTwyLVTeN4H9qaZyZeCCttx3PwCsf5So\n" +
            "Foj9iOVJyoHSi8i/eewOwc936kKFmleoG11iFMGTw+D0c8RHN1txJIQ6WUzpEw49XL2MJySOd/4p\n" +
            "Blut0WS8lsqWxVH/tAo6curAjKPQggSBm1fuQb+tSK+AqNuqUXHfMZNJyndBs0E9eZbLae8L8HRI\n" +
            "9I6D3Wad4s/PAgMBAAECggEASfuBoO0VuADsE78OPXOyXxgohGkpb21GvGWVQ/uev7cArXoc3LbA\n" +
            "pA1qm61dEF73r5dqb5jcjfpgnKXCTf9EJUpv8AoVRVS/CiEu5FalCXNjNe37jNa2wBtITR3wALWn\n" +
            "q31Yss0vQJ9aATmeTVHTx1fzFQJ4BNQyLRBMKwOh4c13w1rbaGpT67iuzbdsxNjgir9iPkzMHs8E\n" +
            "jc3fKgBq+bN8mbJpHm41azQInlA5ToG0ng2S190DIpjGl6thC5RL3ZvpRRprEb0vT3zExZLvWlP5\n" +
            "NI5dANmzLZ43rp98oH82zsDnfeJHgIGpXuNx3dxP6LLrek/zJCmAAOt4gq57YQKBgQDgKgsy6PQW\n" +
            "Imi1PBFE93mS/YUzaQAwMXJFm09SiQ7XDxQYaDxKAPIjpFqQOHtWV3tOEZFNwOV/NRMPBXmq00Gw\n" +
            "SpcDCSzPPXKjc+jTvOZwMitNVsTB4F0a2iahaxwZ2GNMiUnoPFEIOuYG47nv3UUnEujTn53K158A\n" +
            "PFEb2PGOXwKBgQC7Y85hj/R8SBChzYq1ycpVZngfLwNU7Ykwz8LxBdveyXbdMXbPVcqyiBRWan6/\n" +
            "k0HpUtcAnDXcCbanjBPzyOmxmaeR2lVepAtLJJ5b+Ykk1iNGprltFpOVTj5m/89IzYj+ZUZhj3ST\n" +
            "CgfZGKZJzRq7y6TGBjXVkLm2xPdsD+ZUkQKBgQCmhRUEoKCRwMXfu9toTB533vSQGDQXmOO2aYUn\n" +
            "XsEzaPQYnXYL02AMsg7Ei/CzVxE2ET7Lb7wMHzUlN4AGMiDeAMrlam9rE86RNY2DnRPhzKL2NLq8\n" +
            "+xqu09dWmeaqHGDHfkT0y7m2T2qJO6noUYsJV7a1XiEtibeHyUbHVQU8KQKBgFvTjcfLVkAsxFG1\n" +
            "ulpY1xjATO7jB9vvRsIvnVSkzVjAGspLue/iF6EjA+xQ5WmVjL/8gzVeVxpLfEFgaEqXUsVgCXh0\n" +
            "qE7nTk11VVC22Fi/wquVedoWLR4GKJgqlPYVGiOnlzSpqrN/P9dHt/0EUCxkn4sWcf0Ena0Nmj1P\n" +
            "EUyxAoGAOCIL1HSsZOF+zZwx9ixXlAauW1C46fPfHQFeTYUrA/BUWzetx95Dz9SXCyDCXCal8sVD\n" +
            "RYy15LolWrlZ/hPXht7Q9su/Ezle3I0N7dUWf69ILlVflT81w0r69gQ7zT+xCZgszygWwNc86Yid\n" +
            "OLTp7H5xl8jKj/r8bvfFFMkMTz4=";

    /**
     * RSA密钥长度必须是64的倍数，默认是1024
     */
    public static final int KEY_SIZE = 2048;

    /**
     * 生成密钥对
     *
     * @return 秘钥对map
     */
    private static void generateKeyBytes() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            System.out.println("公钥:\n" + Base64.encodeBase64String(publicKey.getEncoded()));
            System.out.println("私钥:\n" + Base64.encodeBase64String(privateKey.getEncoded()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param keyBytes 公钥数组
     * @return 真正符合规范的公钥
     */
    private static PublicKey restorePublicKey(byte[] keyBytes) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            return factory.generatePublic(x509EncodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param keyBytes 秘钥数组
     * @return 真正符合规范的私钥
     */
    private static PrivateKey restorePrivateKey(byte[] keyBytes) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            return factory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密，三步走。
     *
     * @param key       key公钥
     * @param plainText 原文
     * @return 密文数组
     */
    private static byte[] rsaEncode(PublicKey key, byte[] plainText) {

        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String rsaEncode(String publicKeyStr, String plainText) {
        if (StringUtils.isBlank(publicKeyStr) || StringUtils.isBlank(plainText)) {
            return "";
        }
        byte[] publicKeyByte = Base64.decodeBase64(publicKeyStr);
        PublicKey publicKey = restorePublicKey(publicKeyByte);
        return Base64.encodeBase64String(rsaEncode(publicKey, plainText.getBytes()));
    }

    public static String rsaDecode(String privateKeyStr, String encodeText) {
        if (StringUtils.isBlank(privateKeyStr) || StringUtils.isBlank(encodeText)) {
            return "";
        }
        byte[] privateKeyByte = Base64.decodeBase64(privateKeyStr);
        PrivateKey privateKey = restorePrivateKey(privateKeyByte);
        return rsaDecode(privateKey, Base64.decodeBase64(encodeText));
    }

    /**
     * 解密，三步走。
     *
     * @param key         key私钥
     * @param encodedText 原文
     * @return 原文数组
     */
    private static String rsaDecode(PrivateKey key, byte[] encodedText) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(encodedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        // 测试加密算法
        String planStr = "123456";
        System.out.println("原文:"+planStr);
        System.out.println("公钥："+publicKeyText);
        String s = rsaEncode(publicKeyText, planStr);
        System.out.println("密文:"+s);

        System.out.println("私钥："+privateKeyText);
        String s1 = rsaDecode(privateKeyText, s);
        System.out.println("明文："+s1);

        // 生成公钥、私钥对，也可以用另外GenerateSecrteRSAKey
//        generateKeyBytes();
    }
}
