package com.anttu.dynamic.util;

import java.util.Map;
import java.util.UUID;

/**
 * 描述
 *
 * @ClassName：TokenUtils
 * @Description：token工具类
 * @author：Anttu
 * @Date：2/11/2021 20:27
 */
public class TokenUtils {
    private static Map<String, String> keyMap = RSAUtils.createKeys(2048);
    private static String publicKey = keyMap.get("publicKey");

    public static String createToken () {
        try {
            return RSAUtils.publicEncrypt(UUID.randomUUID().toString(), RSAUtils.getPublicKey(publicKey));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main (String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(createToken());
        }
    }
}
