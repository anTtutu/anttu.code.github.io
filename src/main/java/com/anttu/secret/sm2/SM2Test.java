package com.anttu.secret.sm2;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.SM2;

/**
 * 描述
 *
 * @ClassName：SM2Test
 * @Description：hutool工具包的sm2国密测试
 * @author：Anttu
 * @Date：25/8/2021 09:52
 */
public class SM2Test {
    public static void main(String[] args) {
        SM2 sm2 = SmUtil.sm2();
        String publicKey = sm2.getPublicKeyBase64();
        String privateKey = sm2.getPrivateKeyBase64();

        System.out.println("公钥：\n" + publicKey);
        System.out.println("私钥：\n" + privateKey);

        String pwd = "123456";
    }
}
