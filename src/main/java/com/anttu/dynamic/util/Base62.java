package com.anttu.dynamic.util;

/**
 * 描述
 *
 * @ClassName：Base62
 * @Description：62进制
 * @author：Anttu
 * @Date：1/11/2021 16:44
 */
public class Base62 {
    private static final String BASE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String toBase62(long num) {
        StringBuilder sb = new StringBuilder();
        do {
            int i = (int) (num % 62);
            sb.append(BASE.charAt(i));
            num /= 62;
        } while (num > 0);

        return sb.reverse().toString();
    }
}
