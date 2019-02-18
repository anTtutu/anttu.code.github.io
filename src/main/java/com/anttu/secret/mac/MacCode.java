/**
 * @author hk
 * @date 2017年10月27日 下午2:01:39
 */
package com.anttu.secret.mac;

import java.security.Security;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

/**
 * MAC算法工具类
 * 对于HmacMD5、HmacSHA1、HmacSHA256、HmacSHA384、HmacSHA512应用的步骤都是一模一样的
 *
 * @author hk
 * @date 2017年10月27日 下午2:01:39
 */
public class MacCode
{

    /////////////////////////// HmacMD5///////////////////////////////
    /**
     * 初始化HmacMD5的密钥
     *
     * @return byte[] 密钥
     */
    public static byte[] initHmacMD5Key() throws Exception
    {
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获取密钥
        return secretKey.getEncoded();
    }

    /**
     * HmacMD5消息摘要
     *
     * @param data
     *            待做摘要处理的数据
     * @param key
     *            密钥
     * @return byte[] 消息摘要
     */
    public static String encodeHmacMD5(byte[] data, byte[] key) throws Exception
    {
        // 还原密钥，因为密钥是以byte形式为消息传递算法所拥有
        SecretKey secretKey = new SecretKeySpec(key, "HmacMD5");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要处理
        byte[] digest = mac.doFinal(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /////////////////////////////// HmacSHA1//////////////////////////////////
    /**
     * 初始化HmacSHA1的密钥
     *
     * @return byte[] 密钥
     */
    public static byte[] initHmacSHAKey() throws Exception
    {
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA1");
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获取密钥
        return secretKey.getEncoded();
    }

    /**
     * HmacSHA1消息摘要
     *
     * @param data
     *            待做摘要处理的数据
     * @param key
     *            密钥
     * @return byte[] 消息摘要
     */
    public static String encodeHmacSHA(byte[] data, byte[] key) throws Exception
    {
        // 还原密钥，因为密钥是以byte形式为消息传递算法所拥有
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA1");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要处理
        byte[] digest = mac.doFinal(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /////////////////////////////// HmacSHA256//////////////////////////////////
    /**
     * 初始化HmacSHA256的密钥
     *
     * @return byte[] 密钥
     */
    public static byte[] initHmacSHA256Key() throws Exception
    {
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获取密钥
        return secretKey.getEncoded();
    }

    /**
     * HmacSHA256消息摘要
     *
     * @param data
     *            待做摘要处理的数据
     * @param key
     *            密钥
     * @return byte[] 消息摘要
     */
    public static String encodeHmacSHA256(byte[] data, byte[] key) throws Exception
    {
        // 还原密钥，因为密钥是以byte形式为消息传递算法所拥有
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA256");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要处理
        byte[] digest = mac.doFinal(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /////////////////////////////// HmacSHA384//////////////////////////////////
    /**
     * 初始化HmacSHA384的密钥
     *
     * @return byte[] 密钥
     */
    public static byte[] initHmacSHA384Key() throws Exception
    {
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA384");
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获取密钥
        return secretKey.getEncoded();
    }

    /**
     * HmacSHA384消息摘要
     *
     * @param data
     *            待做摘要处理的数据
     * @param key
     *            密钥
     * @return byte[] 消息摘要
     */
    public static String encodeHmacSHA384(byte[] data, byte[] key) throws Exception
    {
        // 还原密钥，因为密钥是以byte形式为消息传递算法所拥有
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA384");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要处理
        byte[] digest = mac.doFinal(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /////////////////////////////// HmacSHA512//////////////////////////////////
    /**
     * 初始化HmacSHA512的密钥
     *
     * @return byte[] 密钥
     */
    public static byte[] initHmacSHA512Key() throws Exception
    {
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA512");
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获取密钥
        return secretKey.getEncoded();
    }

    /**
     * HmacSHA512消息摘要
     *
     * @param data
     *            待做摘要处理的数据
     * @param key
     *            密钥
     * @return byte[] 消息摘要
     */
    public static String encodeHmacSHA512(byte[] data, byte[] key) throws Exception
    {
        // 还原密钥，因为密钥是以byte形式为消息传递算法所拥有
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA512");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要处理
        byte[] digest = mac.doFinal(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /////////////////////////////// HmacMD2-BouncyCastle才支持的实现//////////////////////////////////
    /**
     * 初始化HmacMD2的密钥
     *
     * @return byte[] 密钥
     */
    public static byte[] initHmacMD2Key() throws Exception
    {

        // 加入BouncyCastleProvider的支持
        Security.addProvider(new BouncyCastleProvider());
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD2");
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获取密钥
        return secretKey.getEncoded();
    }

    /**
     * HmacMD2消息摘要
     *
     * @param data
     *            待做摘要处理的数据
     * @param key
     *            密钥
     * @return byte[] 消息摘要
     */
    public static String encodeHmacMD2(byte[] data, byte[] key) throws Exception
    {
        // 加入BouncyCastleProvider的支持
        Security.addProvider(new BouncyCastleProvider());
        // 还原密钥，因为密钥是以byte形式为消息传递算法所拥有
        SecretKey secretKey = new SecretKeySpec(key, "HmacMD2");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要处理
        byte[] digest = mac.doFinal(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * HmacMD2Hex消息摘要
     *
     * @param data
     *            待做消息摘要处理的数据
     * @param String
     *            密钥
     * @return byte[] 消息摘要
     */
    // public static String encodeHmacMD2Hex(byte[] data, byte[] key) throws Exception
    // {
    // // 执行消息摘要处理
    // byte[] b = encodeHmacMD2(data, key);
    // // 做十六进制转换
    // return new String(Hex.encode(b));
    // }

    /////////////////////////////// HmacMD4-BouncyCastle才支持的实现//////////////////////////////////
    /**
     * 初始化HmacMD2的密钥
     *
     * @return byte[] 密钥
     */
    public static byte[] initHmacMD4Key() throws Exception
    {

        // 加入BouncyCastleProvider的支持
        Security.addProvider(new BouncyCastleProvider());
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD4");
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获取密钥
        return secretKey.getEncoded();
    }

    /**
     * HmacMD4消息摘要
     *
     * @param data
     *            待做摘要处理的数据
     * @param key
     *            密钥
     * @return byte[] 消息摘要
     */
    public static String encodeHmacMD4(byte[] data, byte[] key) throws Exception
    {
        // 加入BouncyCastleProvider的支持
        Security.addProvider(new BouncyCastleProvider());
        // 还原密钥，因为密钥是以byte形式为消息传递算法所拥有
        SecretKey secretKey = new SecretKeySpec(key, "HmacMD4");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要处理
        byte[] digest = mac.doFinal(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * HmacMD4Hex消息摘要
     *
     * @param data
     *            待做消息摘要处理的数据
     * @param String
     *            密钥
     * @return byte[] 消息摘要
     */
    // public static String encodeHmacMD4Hex(byte[] data, byte[] key) throws Exception
    // {
    // // 执行消息摘要处理
    // byte[] b = encodeHmacMD4(data, key);
    // // 做十六进制转换
    // return new String(Hex.encode(b));
    // }

    /////////////////////////////// HmacSHA224-BouncyCastle才支持的实现//////////////////////////////////
    /**
     * 初始化HmacSHA224的密钥
     *
     * @return byte[] 密钥
     */
    public static byte[] initHmacSHA224Key() throws Exception
    {

        // 加入BouncyCastleProvider的支持
        Security.addProvider(new BouncyCastleProvider());
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA224");
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获取密钥
        return secretKey.getEncoded();
    }

    /**
     * HmacSHA224消息摘要
     *
     * @param data
     *            待做摘要处理的数据
     * @param key
     *            密钥
     * @return byte[] 消息摘要
     */
    public static String encodeHmacSHA224(byte[] data, byte[] key) throws Exception
    {
        // 加入BouncyCastleProvider的支持
        Security.addProvider(new BouncyCastleProvider());
        // 还原密钥，因为密钥是以byte形式为消息传递算法所拥有
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA224");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要处理
        byte[] digest = mac.doFinal(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * HmacSHA224Hex消息摘要
     *
     * @param data
     *            待做消息摘要处理的数据
     * @param String
     *            密钥
     * @return byte[] 消息摘要
     */
    // public static String encodeHmacSHA224Hex(byte[] data, byte[] key) throws Exception
    // {
    // // 执行消息摘要处理
    // byte[] b = encodeHmacSHA224(data, key);
    // // 做十六进制转换
    // return new String(Hex.encode(b));
    // }

    /**
     * 进行相关的摘要算法的处理展示
     *
     * @throws Exception
     **/
    public static void main(String[] args) throws Exception
    {
        String str = "HmacMD5消息摘要";
        // 初始化密钥
        byte[] key1 = MacCode.initHmacMD5Key();
        // 获取摘要信息
        String data1 = MacCode.encodeHmacMD5(str.getBytes(), key1);

        System.out.println("原文：" + str);
        System.out.println();
        System.out.println("HmacMD5的密钥:" + new String(Hex.encode(key1)));
        System.out.println("HmacMD5算法摘要：" + data1);
        System.out.println();

        // 初始化密钥
        byte[] key2 = MacCode.initHmacSHA256Key();
        // 获取摘要信息
        String data2 = MacCode.encodeHmacSHA256(str.getBytes(), key2);
        System.out.println("HmacSHA256的密钥:" + new String(Hex.encode(key2)));
        System.out.println("HmacSHA256算法摘要：" + data2);
        System.out.println();

        // 初始化密钥
        byte[] key3 = MacCode.initHmacSHAKey();
        // 获取摘要信息
        String data3 = MacCode.encodeHmacSHA(str.getBytes(), key3);
        System.out.println("HmacSHA1的密钥:" + new String(Hex.encode(key3)));
        System.out.println("HmacSHA1算法摘要：" + data3);
        System.out.println();

        // 初始化密钥
        byte[] key4 = MacCode.initHmacSHA384Key();
        // 获取摘要信息
        String data4 = MacCode.encodeHmacSHA384(str.getBytes(), key4);
        System.out.println("HmacSHA384的密钥:" + new String(Hex.encode(key4)));
        System.out.println("HmacSHA384算法摘要：" + data4);
        System.out.println();

        // 初始化密钥
        byte[] key5 = MacCode.initHmacSHA512Key();
        // 获取摘要信息
        String data5 = MacCode.encodeHmacSHA512(str.getBytes(), key5);
        System.out.println("HmacSHA512的密钥:" + new String(Hex.encode(key5)));
        System.out.println("HmacSHA512算法摘要：" + data5);
        System.out.println();

        System.out.println("================以下的算法支持是bouncycastle支持的算法，sun java6不支持=======================");
        // 初始化密钥
        byte[] key6 = MacCode.initHmacMD2Key();
        // 获取摘要信息
        String data6 = MacCode.encodeHmacMD2(str.getBytes(), key6);
        System.out.println("Bouncycastle HmacMD2的密钥:" + new String(Hex.encode(key6)));
        System.out.println("Bouncycastle HmacMD2算法摘要：" + data6);
        System.out.println();

        // 初始化密钥
        byte[] key7 = MacCode.initHmacMD4Key();
        // 获取摘要信息
        String data7 = MacCode.encodeHmacMD4(str.getBytes(), key7);
        System.out.println("Bouncycastle HmacMD4的密钥:" + new String(Hex.encode(key7)));
        System.out.println("Bouncycastle HmacMD4算法摘要：" + data7);
        System.out.println();

        // 初始化密钥
        byte[] key8 = MacCode.initHmacSHA224Key();
        // 获取摘要信息
        String data8 = MacCode.encodeHmacSHA224(str.getBytes(), key8);
        System.out.println("Bouncycastle HmacSHA224的密钥:" + new String(Hex.encode(key8)));
        System.out.println("Bouncycastle HmacSHA224算法摘要：" + data8);
        System.out.println();
    }
}
