package com.anttu.dynamic.util;

import java.util.Random;

/**
 * 描述
 *
 * @ClassName：AppIDUtils
 * @Description：AppID 生成工具类
 * @author：Anttu
 * @Date：5/11/2021 17:40
 */
public class AppIDUtils {
    public static String generateAppId () {
        int seed = new Random().nextInt(64);
        Caesar caesar = new Caesar(seed);

        int key = new Random().nextInt(64);
        int salt = new Random().nextInt(999999999);
        String text = String.format("%036d", System.currentTimeMillis() + salt);
        return caesar.encode(key, text);
    }

    public static void main (String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(generateAppId());
        }
    }
}
