/**
 * 创建人  :hk
 * 创建时间:2018年12月28日
 */
package com.anttu.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * [简要描述]:C# AES加解密接口<br/>
 * [详细描述]:<br/>
 *
 * @author hk
 * @version 1.0, 2018年12月28日 下午9:22:35
 * @since JDK1.8
 */
@SuppressWarnings("restriction")
public class ThirdPartyAESEncryForCSHARP
{
    private static Logger log = Logger.getLogger(ThirdPartyAESEncryForNodeJS.class);

    public static final String DEFAULT_CODING = "utf-8";

    /**
     * AES的加密函数
     *
     * @param str
     *            传入需要加密的字符
     * @param key
     *            传入一个16位长度的密钥。否则报错
     * @return 执行成功返回加密结果，否则报错
     * @throws Exception
     *             抛出一个加密异常
     */
    public static String AESEncrypt(String str, String key) throws Exception
    {
        try
        {
            if (str == null || key == null)
            {
                return null;
            }
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(DEFAULT_CODING), "AES"));
            byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
            return new BASE64Encoder().encode(bytes);
        }
        catch (Exception e)
        {
            log.error("ThirdPartyAESEncryForCSHARP - AESDecrypt failed. ErrorMessage:" + e, e);
            return null;
        }
    }

    /**
     * AES的解密函数
     *
     * @param str
     *            传入需要解密的字符
     * @param key
     *            传入一个16位长度的密钥。否则报错
     * @return 执行成功返回加密结果，否则报错
     * @throws Exception
     *             抛出一个解密异常
     */
    public static String AESDecrypt(String str, String key) throws Exception
    {
        try
        {
            if (str == null || key == null)
            {
                return null;
            }
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(DEFAULT_CODING), "AES"));
            byte[] bytes = new BASE64Decoder().decodeBuffer(str);
            bytes = cipher.doFinal(bytes);
            return new String(bytes, "utf-8");
        }
        catch (Exception e)
        {
            log.error("ThirdPartyAESEncryForCSHARP - AESDecrypt failed. ErrorMessage:" + e, e);
            return null;
        }

    }

    public static void main(String[] args) throws Exception
    {
        String key = "1234567890abcdef";
        String value = "test,123";
        System.out.println("加密：" + AESEncrypt(value, key));
        System.out.println("解密：" + AESDecrypt(AESEncrypt(value, key), key));
    }

}
