/**
 * 创建人  :hk
 * 创建时间:2018年12月28日
 */
package com.anttu.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URLEncoder;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 * [简要描述]:nodejs AES加解密接口<br/>
 * [详细描述]:<br/>
 *
 * @author hk
 * @version 1.0, 2018年12月28日 下午9:22:35
 * @since JDK1.8
 */
public class ThirdPartyAESEncryForNodeJS
{
    private static Logger log = LogManager.getLogger(ThirdPartyAESEncryForNodeJS.class);

    public static final String DEFAULT_CODING = "utf-8";

    /**
     * [简要描述]:解密<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param encrypted
     *            待解密的加密报文
     * @param seed
     *            16位密钥
     * @return
     * @throws Exception
     */
    public static String AESDecrypt(String encrypted, String seed)
    {
        try
        {
            byte[] keyb = seed.getBytes(DEFAULT_CODING);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(keyb);
            SecretKeySpec skey = new SecretKeySpec(thedigest, "AES");
            Cipher dcipher = Cipher.getInstance("AES");
            dcipher.init(Cipher.DECRYPT_MODE, skey);

            byte[] clearbyte = dcipher.doFinal(toByte(encrypted));
            return new String(clearbyte);
        }
        catch (Exception e)
        {
            log.error("TirdPartyAESEncryForNodeJS - AESDecrypt failed. ErrorMessage:" + e, e);
            return null;
        }
    }

    /**
     * [简要描述]:加密<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param content
     *            待加密的明文字符串
     * @param key
     *            16位密钥
     * @return
     * @throws Exception
     */
    public static String AESEncrypt(String content, String key)
    {
        try
        {
            byte[] input = content.getBytes(DEFAULT_CODING);

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(key.getBytes(DEFAULT_CODING));
            SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skc);

            byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
            int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
            ctLength += cipher.doFinal(cipherText, ctLength);

            return parseByte2HexStr(cipherText);
        }
        catch (Exception e)
        {
            log.error("TirdPartyAESEncryForNodeJS - AESEncrypt failed. ErrorMessage:" + e, e);
            return null;
        }
    }

    /**
     * [简要描述]:字符串转字节数组<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param hexString
     * @return
     */
    private static byte[] toByte(String hexString)
    {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
        {
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        }
        return result;
    }

    /**
     * [简要描述]:字节转16进制数组<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[])
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++)
        {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1)
            {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        // 待加密字符串
        String content = "test1,123";
        // 密钥
        String key = "1234567890abcdef";

        try
        {
            // 加密结果
            String encryptStr = ThirdPartyAESEncryForNodeJS.AESEncrypt(content, key);
            // 加密后urlencode结果
            String encryptStr2URLEncode = URLEncoder.encode(encryptStr, DEFAULT_CODING);
            // 解密结果
            String decryptStr = ThirdPartyAESEncryForNodeJS.AESDecrypt(encryptStr, key);

            System.out.println("参数明文：" + content);
            System.out.println("加密结果：" + encryptStr);
            System.out.println("加密后urlencode结果：" + encryptStr2URLEncode);
            System.out.println("解密结果：" + decryptStr);
        }
        catch (Exception e)
        {
            log.error("ThirdPartyAESEncryForNodeJS - urlencode error, ErrorMessage:" + e, e);
        }
    }
}
