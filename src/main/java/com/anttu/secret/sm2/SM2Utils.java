package com.anttu.secret.sm2;

import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.crypto.signers.SM2Signer;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

/**
 * 描述
 *
 * @ClassName：SM2Utils
 * @Description：国密SM2算法工具demo
 * @author：Anttu
 * @Date：23/8/2021 20:52
 */
public class SM2Utils {
    /**
     * SM2加密算法
     * @param publicKey     公钥
     * @param data          明文数据
     * @return
     */
    public String encrypt(String publicKey, String data) {
        // 获取一条SM2曲线参数
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        //提取公钥点
        ECPoint pukPoint = sm2ECParameters.getCurve().decodePoint(Util.hexString2byte(publicKey));

        ECDomainParameters ecDomainParameters = new ECDomainParameters(sm2ECParameters.getCurve(),
                sm2ECParameters.getG(), sm2ECParameters.getN());

        ECPublicKeyParameters ecPublicKeyParameters = new ECPublicKeyParameters(pukPoint, ecDomainParameters);

        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(true, new ParametersWithRandom(ecPublicKeyParameters, new SecureRandom()));

        byte[] arrayOfBytes = null;
        try {
            byte[] in = data.getBytes("utf-8");
            arrayOfBytes = sm2Engine.processBlock(in,0, in.length);
        } catch (Exception e) {
            System.out.println("SM2加密时出现异常:");
        }
        return Hex.toHexString(arrayOfBytes);
    }

    /**
     * SM2解密算法
     * @param privateKey        私钥
     * @param cipherData        密文数据
     * @return
     */
    public String decrypt(String privateKey, String cipherData) {
        byte[] cipherDataByte = Hex.decode(cipherData);

        //获取一条SM2曲线参数
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        //构造domain参数
        ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(),
                sm2ECParameters.getG(), sm2ECParameters.getN());

        BigInteger privateKeyD = new BigInteger(privateKey, 16);
        ECPrivateKeyParameters privateKeyParameters = new ECPrivateKeyParameters(privateKeyD, domainParameters);

        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(false, privateKeyParameters);

        String result = null;
        try {
            byte[] arrayOfBytes = sm2Engine.processBlock(cipherDataByte, 0, cipherDataByte.length);
            return new String(arrayOfBytes, "utf-8");
        } catch (Exception e) {
            System.out.println("SM2解密时出现异常");
        }
        return result;
    }

    /**
     * SM2加密算法
     * @param publicKey     公钥
     * @param data          明文数据
     * @return
     */
    public String encrypt(PublicKey publicKey, String data) {

        ECPublicKeyParameters ecPublicKeyParameters = null;
        if (publicKey instanceof BCECPublicKey) {
            BCECPublicKey bcecPublicKey = (BCECPublicKey) publicKey;
            ECParameterSpec ecParameterSpec = bcecPublicKey.getParameters();
            ECDomainParameters ecDomainParameters = new ECDomainParameters(ecParameterSpec.getCurve(),
                    ecParameterSpec.getG(), ecParameterSpec.getN());
            ecPublicKeyParameters = new ECPublicKeyParameters(bcecPublicKey.getQ(), ecDomainParameters);
        }

        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(true, new ParametersWithRandom(ecPublicKeyParameters, new SecureRandom()));

        byte[] arrayOfBytes = null;
        try {
            byte[] in = data.getBytes("utf-8");
            arrayOfBytes = sm2Engine.processBlock(in,0, in.length);
        } catch (Exception e) {
            System.out.println("SM2加密时出现异常:");
        }
        return Hex.toHexString(arrayOfBytes);
    }

    /**
     * SM2解密算法
     * @param privateKey        私钥
     * @param cipherData        密文数据
     * @return
     */
    public String decrypt(PrivateKey privateKey, String cipherData) {
        byte[] cipherDataByte = Hex.decode(cipherData);

        BCECPrivateKey bcecPrivateKey = (BCECPrivateKey) privateKey;
        ECParameterSpec ecParameterSpec = bcecPrivateKey.getParameters();

        ECDomainParameters ecDomainParameters = new ECDomainParameters(ecParameterSpec.getCurve(),
                ecParameterSpec.getG(), ecParameterSpec.getN());

        ECPrivateKeyParameters ecPrivateKeyParameters = new ECPrivateKeyParameters(bcecPrivateKey.getD(),
                ecDomainParameters);

        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(false, ecPrivateKeyParameters);

        String result = null;
        try {
            byte[] arrayOfBytes = sm2Engine.processBlock(cipherDataByte, 0, cipherDataByte.length);
            return new String(arrayOfBytes, "utf-8");
        } catch (Exception e) {
            System.out.println("SM2解密时出现异常");
        }
        return result;
    }

    /**
     * 将未压缩公钥压缩成压缩公钥
     * @param pubKey    未压缩公钥(16进制,不要带头部04)
     * @return
     */
    public static String compressPubKey(String pubKey) {
        StringBuffer stringBuffer = new StringBuffer();
        pubKey = stringBuffer.append("04").append(pubKey).toString();    //将未压缩公钥加上未压缩标识.
        // 获取一条SM2曲线参数
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        // 构造domain参数
        ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(),
                sm2ECParameters.getG(),
                sm2ECParameters.getN());
        //提取公钥点
        ECPoint pukPoint = sm2ECParameters.getCurve().decodePoint(Util.hexString2byte(pubKey));
        // 公钥前面的02或者03表示是压缩公钥，04表示未压缩公钥, 04的时候，可以去掉前面的04
//        ECPublicKeyParameters publicKeyParameters = new ECPublicKeyParameters(pukPoint, domainParameters);

        String compressPubKey = Hex.toHexString(pukPoint.getEncoded(Boolean.TRUE));

        return compressPubKey;
    }

    /**
     * 将压缩的公钥解压为非压缩公钥
     * @param compressKey   压缩公钥
     * @return
     */
    public static String unCompressPubKey(String compressKey) {
        // 获取一条SM2曲线参数
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        // 构造domain参数
        ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(),
                sm2ECParameters.getG(),
                sm2ECParameters.getN());
        //提取公钥点
        ECPoint pukPoint = sm2ECParameters.getCurve().decodePoint(Util.hexString2byte(compressKey));
        // 公钥前面的02或者03表示是压缩公钥，04表示未压缩公钥, 04的时候，可以去掉前面的04
//        ECPublicKeyParameters publicKeyParameters = new ECPublicKeyParameters(pukPoint, domainParameters);

        String pubKey = Hex.toHexString(pukPoint.getEncoded(Boolean.FALSE));
        pubKey = pubKey.substring(2);       //去掉前面的04   (04的时候，可以去掉前面的04)

        return pubKey;
    }

    /**
     * 验证签名
     * @param publicKey     公钥
     * @param content       待签名内容
     * @param sign          签名值
     * @return
     */
//    public static boolean verify(String publicKey, String content, String sign) {
//        //待签名内容
//        byte[] message = Hex.decode(content);
//        byte[] signData = Hex.decode(sign);
//
//        // 获取一条SM2曲线参数
//        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
//        // 构造domain参数
//        ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(),
//                sm2ECParameters.getG(),
//                sm2ECParameters.getN());
//        //提取公钥点
//        ECPoint pukPoint = sm2ECParameters.getCurve().decodePoint(Util.hexString2byte(compressPubKey(publicKey)));
//        // 公钥前面的02或者03表示是压缩公钥，04表示未压缩公钥, 04的时候，可以去掉前面的04
//        ECPublicKeyParameters publicKeyParameters = new ECPublicKeyParameters(pukPoint, domainParameters);
//
//        //获取签名
//        BigInteger R = null;
//        BigInteger S = null;
//        byte[] rBy = new byte[33];
//        System.arraycopy(signData, 0, rBy, 1, 32);
//        rBy[0] = 0x00;
//        byte[] sBy = new byte[33];
//        System.arraycopy(signData, 32, sBy, 1, 32);
//        sBy[0] = 0x00;
//        R = new BigInteger(rBy);
//        S = new BigInteger(sBy);
//
//        //创建签名实例
//        SM2Signer sm2Signer = new SM2Signer();
//        ParametersWithID parametersWithID = new ParametersWithID(publicKeyParameters, Strings.toByteArray("1234567812345678"));
//        sm2Signer.init(false, parametersWithID);
//
//        //验证签名结果
//        boolean verify = sm2Signer.verifySignature(message, R, S);
//        return verify;
//    }


    /**
     * 生成公钥私钥
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    public static void generateKey() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
        // 获取一个椭圆曲线类型的密钥对生成器
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
        // 使用SM2参数初始化生成器
        kpg.initialize(sm2Spec);
        // 获取密钥对
        KeyPair keyPair = kpg.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("publicKey:\n" + publicKey);
        System.out.println("privateKey:\n" + privateKey);
    }

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        // 生成公钥私钥密码对
        generateKey();

        String str = "123456";

        /**
         * 字符串的公钥私钥还存在算法拼接问题，暂时未调通
         */
//        String publicKey = "3059301306072a8648ce3d020106082a811ccf5501822d03420004f399bc89cec508821b2fdca59f89f3a6bd7590d433abcdf90a3d3445bd025f9930aead0695166c0669a0f52e3b81d627e5709fdfb064d3f134670dd98af099f6";
//        String privateKey = "308193020100301306072a8648ce3d020106082a811ccf5501822d047930770201010420217fe0a1622f10b1558f26bd8532163567376f7da0699885951d05da46a82a18a00a06082a811ccf5501822da14403420004f399bc89cec508821b2fdca59f89f3a6bd7590d433abcdf90a3d3445bd025f9930aead0695166c0669a0f52e3b81d627e5709fdfb064d3f134670dd98af099f6";

        ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
        // 获取一个椭圆曲线类型的密钥对生成器
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
        // 使用SM2参数初始化生成器
        kpg.initialize(sm2Spec);
        // 获取密钥对
        KeyPair keyPair = kpg.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();


        SM2Utils sm2 = new SM2Utils();
        String data = sm2.encrypt(publicKey, str);
        System.out.println("加密：\n" + data);
        String text=sm2.decrypt(privateKey, data);
        System.out.println("解密：\n" + text);
    }
}
