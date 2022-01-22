package com.anttu.dynamic.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;

import cn.hutool.core.util.ObjectUtil;

/**
 * 如果出现这种错误提醒：java.security.InvalidKeyException: Illegal key size
 *
 * 注意：美国软件出口限制，JDK默认使用的AES算法最高只能支持128位。如需要更高的支持需要从oracle官网下载
 * OracleJDK下载地址：https://www.oracle.com/java/technologies/javase-jce8-downloads.html
 * OpenJDK 下载地址：https://bugs.openjdk.java.net/browse/JDK-8170157
 * 更换JAVA_HOME/jre/lib/security目录下的：local_policy.jar和US_export_policy.jar。<br/>
 *
 * @ClassName：AESUtils
 * @Description：AES256加解密算法工具类demo, 对称加密，不适用于前后端传输、不适用于第三方对接，因为向量和密钥需要同时给第三方保存
 * @author：Anttu
 * @Date：6/9/2021 10:46
 */
public class AESUtils {
    /**
     * 密钥，最长64位，可以设置32位和64位，否着长度不合适会抛错
     */
    private static final String KEY = "4123EA7F726D12CF6FB39207295D612E400AEC78C437655F2C1BD46D48D27B80";
    /**
     * 向量，必须32位
     */
    private static final String IV = "F2155E34BBD45FDF8GBF368F8988F6C8";

    /**
     * 加密算法类型
     */
    private static final String ALGORITHM = "AES";
    /**
     * 密码算法填充类型
     */
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    /**
     * 加密的cipher
     */
    private static Cipher encryptCipher;
    static {
        try {
            byte[] key = DatatypeConverter.parseHexBinary(KEY);
            byte[] iv = DatatypeConverter.parseHexBinary(IV);
            SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            encryptCipher = Cipher.getInstance(CIPHER_ALGORITHM);
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 解密的cipher
     */
    private static Cipher decryptCipher;
    static {
        try {
            byte[] key = DatatypeConverter.parseHexBinary(KEY);
            byte[] iv = DatatypeConverter.parseHexBinary(IV);
            SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            decryptCipher = Cipher.getInstance(CIPHER_ALGORITHM);
            decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密
     * [简要描述]:<br/>
     * [详细描述]:<br/>
     *
     * @param data
     * @return
     * @throws Exception
     * @author cgtu
     */
    public static String encrypt(String data) throws Exception {
        if (StringUtils.isBlank(data)) {
            throw new Exception("data is null");
        }

        try {
            byte[] plainText = data.getBytes();
            if (ObjectUtil.isNull(encryptCipher)) {
                byte[] key = DatatypeConverter.parseHexBinary(KEY);
                byte[] iv = DatatypeConverter.parseHexBinary(IV);
                SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                encryptCipher = Cipher.getInstance(CIPHER_ALGORITHM);
                encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            }
            return DatatypeConverter.printBase64Binary(encryptCipher.doFinal(plainText));
        } catch (Exception e) {
            throw new Exception("encrypt is error");
        }
    }

    /**
     * 解密
     * [简要描述]:<br/>
     * [详细描述]:<br/>
     *
     * @param cryptData
     * @return
     * @throws Exception
     * @author cgtu
     */
    public static String decrypt(String cryptData) throws Exception {
        if (StringUtils.isBlank(cryptData)) {
            throw new Exception("cryptDate is null.");
        }

        try {
            if (ObjectUtil.isNull(decryptCipher)) {
                byte[] key = DatatypeConverter.parseHexBinary(KEY);
                byte[] iv = DatatypeConverter.parseHexBinary(IV);
                SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                decryptCipher = Cipher.getInstance(CIPHER_ALGORITHM);
                decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            }
            return new String(decryptCipher.doFinal(DatatypeConverter.parseBase64Binary(cryptData)));
        } catch (Exception e) {
            throw new Exception("decrypt is error.");
        }
    }

    public static void main(String[] args) throws Exception {
        String str = "123456";

        for (int i = 0; i < 10; i++) {
            String encrypt = AESUtils.encrypt(str);
            System.out.println("加密后:\n" + encrypt);
            System.out.println("=====================");
            String plant = AESUtils.decrypt(encrypt);
            System.out.println("解密后:\n" + plant);
        }
    }
}
