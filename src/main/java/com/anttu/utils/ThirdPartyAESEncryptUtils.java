/**
 * @author hk
 * @date 2017年10月24日 下午2:18:19
 */
package com.anttu.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * 与第三方H5互动的H5传输参数的加密工具类
 * 因为这个为了个php配套，base64算法利用apache的，同包名下的另外一个AES加解密工具类base64是自己实现的。可能php改造难度大
 * 
 * @author hk
 * @date 2017年10月24日 下午2:18:19
 */
public class ThirdPartyAESEncryptUtils
{
    private static Logger log = Logger.getLogger(ThirdPartyAESEncryptUtils.class);

    /**
     * AES加密方式
     */
    private static final String AES_TYPE = "AES/ECB/PKCS5Padding";

    /**
     * AES加密
     * keyStr 秘钥 16位
     * plainText 待加密字符
     * 
     * @author hk
     * @date 2017年10月24日 下午3:05:55
     * @param keyStr
     * @param plainText
     * @return
     */
    public static String AESEncrypt(String keyStr, String plainText)
    {
        byte[] encrypt = null;
        try
        {
            Key key = generateKey(keyStr);
            Cipher cipher = Cipher.getInstance(AES_TYPE);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypt = cipher.doFinal(plainText.getBytes());
        }
        catch (Exception e)
        {
            log.error("ThirdPartyAESEncryptUtils - AESEncrypt failed. ErrorMessage:" + e, e);
        }
        return new String(Base64.encodeBase64(encrypt));
    }

    /**
     * AES解密
     * keyStr 秘钥16位
     * encryptData 相同秘钥加密后的字符串，待解密的数据
     * 
     * @author hk
     * @date 2017年10月24日 下午3:06:39
     * @param keyStr
     * @param encryptData
     * @return
     */
    public static String AESDecrypt(String keyStr, String encryptData)
    {
        byte[] decrypt = null;
        try
        {
            Key key = generateKey(keyStr);
            Cipher cipher = Cipher.getInstance(AES_TYPE);
            cipher.init(Cipher.DECRYPT_MODE, key);
            decrypt = cipher.doFinal(Base64.decodeBase64(encryptData));
        }
        catch (Exception e)
        {
            log.error("ThirdPartyAESEncryptUtils - AESDecrypt failed. ErrorMessage:" + e, e);
        }
        return new String(decrypt).trim();
    }

    /**
     * 生成秘钥，入参16位字符串
     * 
     * @author hk
     * @date 2017年10月24日 下午3:08:44
     * @param key
     * @return
     * @throws Exception
     */
    private static Key generateKey(String key) throws Exception
    {
        try
        {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            return keySpec;
        }
        catch (Exception e)
        {
            log.error("ThirdPartyAESEncryptUtils - generateKey failed. ErrorMessage:" + e, e);
            throw e;
        }

    }

    public static void main(String[] args)
    {

        String keyStr = "dBbb4f75579d736C";

        String plainText = "purcotton2018";

        String encText = AESEncrypt(keyStr, plainText);
        String decString = AESDecrypt(keyStr, encText);

        System.out.println(encText);
        System.out.println(decString);

    }
}
