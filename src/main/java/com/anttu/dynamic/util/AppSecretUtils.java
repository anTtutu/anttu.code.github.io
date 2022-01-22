package com.anttu.dynamic.util;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

/**
 * 描述
 *
 * @ClassName：AppSecretUtils
 * @Description：AppSecret生成工具类
 * @author：Anttu
 * @Date：5/11/2021 17:51
 */
public class AppSecretUtils {


    public static String generateAppSecret(String appId) {
        try {
            int salt = new Random().nextInt(999999999);
            String key = String.format("%032d", System.currentTimeMillis() + salt);
            byte[] hmac256 = Auth.hmac256(key.getBytes(StandardCharsets.UTF_8), appId);
            return AESUtils.encrypt(DatatypeConverter.printHexBinary(hmac256));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main (String[] args) throws Exception {
        for (int i = 0; i < 10000; i++) {
            String appId = AppIDUtils.generateAppId();
            String appSecret = AppSecretUtils.generateAppSecret(appId);
            System.out.println("AppID:" + appId);
            System.out.println("AppSecret:" + appSecret);
        }
    }
}
