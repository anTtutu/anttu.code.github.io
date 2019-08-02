/**
 * @author hk
 * @date 2017年10月25日 下午2:44:57
 */
package com.anttu.password;

import static java.lang.Character.isDigit;
import static java.lang.Character.toUpperCase;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

/**
 * 一个多平台密码管理工具，只需要自己输入钥匙密码，然后添加上平台名称，系统帮你生成复杂的密码，不可逆\不上传云\不存储本地
 * 比如：123456 + taobao，生成16位和32位的复杂密码
 * 复制花密的源码，少许调整
 *
 * @author hk
 * @date 2017年10月25日 下午2:44:57
 */
public class MultiPlatformStrongPasswordManagerUtils
{
    private static Logger log = Logger.getLogger(MultiPlatformStrongPasswordManagerUtils.class);

    private static final String UTF_8 = "UTF-8";

    private static final String HMAC_MD5 = "HmacMD5";

    private static final char[] DIGITS_LOWER =
    {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static String encrypt(String passwordText, String keyText)
    {
        if (passwordText.length() > 0 && keyText.length() > 0)
        {
            try
            {
                String md5one = hmacMd5(passwordText, keyText);
                String md5two = hmacMd5(md5one, "BuprdVdI73zk83vrTiTpZYqsb0vAF92");
                String md5three = hmacMd5(md5one, "9AuNTWpm4hKIwmho98IjNrAhXBVQxy3y");
                String rule = md5three;
                StringBuilder source = new StringBuilder(md5two);
                final String str = "1btxBPpF8nhb+Bse";
                for (int i = 0; i <= 31; ++i)
                {
                    if (!isDigit(source.charAt(i)))
                    {
                        if (str.indexOf(rule.charAt(i)) > -1)
                        {
                            source.setCharAt(i, toUpperCase(source.charAt(i)));
                        }
                    }
                }
                String code32 = source.toString();
                char code1 = code32.charAt(0);
                String code16 = null;
                if (!isDigit(code1))
                {
                    code16 = code32.substring(0, 16);
                }
                else
                {
                    code16 = "K" + code32.substring(1, 16);
                }
                return code16;
            }
            catch (Exception e)
            {
                log.error(String.format("Error occured while encrypting password \"%s\" with key \"%s\"", passwordText,
                        keyText), e);
            }
        }
        return "";
    }

    /**
     * hmacMd5加密
     *
     * @author hk
     * @date 2017年10月25日 下午2:53:22
     * @param dataText
     * @param keyText
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static String hmacMd5(String dataText, String keyText)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException
    {
        byte[] dataBytes = dataText.getBytes(UTF_8);
        byte[] keyBytes = keyText.getBytes(UTF_8);

        Mac mac = Mac.getInstance(HMAC_MD5);
        Key key = new SecretKeySpec(keyBytes, HMAC_MD5);
        mac.init(key);

        byte[] resultBytes = mac.doFinal(dataBytes);
        String resultText = encodeHexString(resultBytes);
        return resultText;
    }

    /**
     * 字节码转成16进制
     *
     * @author hk
     * @date 2017年10月25日 下午2:52:32
     * @param data
     * @return
     */
    private static String encodeHexString(byte[] data)
    {
        int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++)
        {
            out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_LOWER[0x0F & data[i]];
        }
        return new String(out);
    }

    public static void main(String[] args)
    {
        System.out.println(encrypt("123456", "taobao"));
        System.out.println(encrypt("123456", "taobao1"));
        System.out.println(encrypt("123456", "taobao2"));
        System.out.println(encrypt("123456", "jd"));
        System.out.println(encrypt("123456", "suning"));
    }
}
