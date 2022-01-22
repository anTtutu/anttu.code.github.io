package com.anttu.dynamic.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 1.CBC模式下，需要加入偏移量，否则会报错，我的报错是：java.security.InvalidKeyException: Parameters missing
 * 解决方法：加入偏移量参数（如何加偏移量可百度），或者可以把CBC换成ECB，可以不加偏移量
 * 2.Nopadding 在此类型下 密钥 和 加密内容 都必须要16字节的倍数,在不满足前条件的情况下会抛异常。
 * 解决方法：可以把Nopadding换成PKCS5Padding或PKCS7Padding，或者手动给加密内容填充至16字节的倍数。
 * private static final String ALGORITHM_STR = "AES/CBC/NoPadding";
 *
 * @ClassName：AesEncryptUtils
 * @Description：
 * @author：Anttu
 * @Date：27/10/2021 20:43
 */
public class AESEncryptUtils {
    private static final String KEY = "d7b85c6e414dbcda";
    private static final String ALGORITHM_STR = "AES/CBC/PKCS5Padding";

    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    public static byte[] base64Decode(String base64Code) throws Exception {
        return Base64.decodeBase64(base64Code);
    }

    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(256);
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(256);
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    public static void main(String[] args) throws Exception {
        String content = "{name:\"lynn\",id:1}";
        System.out.println("加密前：" + content);

        String encrypt = aesEncrypt(content, KEY);
        System.out.println(encrypt.length() + ":加密后：" + encrypt);

        String decrypt = aesDecrypt("H9pGuDMV+iJoS8YSfJ2Vx0NYN7v7YR0tMm1ze5zp0WvNEFXQPM7K0k3IDUbYr5ZIckTkTHcIX5Va/cstIPrYEK3KjfCwtOG19l82u+x6soa9FzAtdL4EW5HAFMmpVJVyG3wz/XUysIRCwvoJ20ruEwk07RB3ojc1Vtns8t4kKZE=", "d7b85f6e214abcda");
        System.out.println("解密后：" + decrypt);
    }
}
