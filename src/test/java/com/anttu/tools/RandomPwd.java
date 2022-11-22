package com.anttu.tools;

import java.util.Random;

/**
 * 描述
 *
 * @ClassName：RandomPwd
 * @Description：随机密码
 * @author：Anttu
 * @Date：22/11/2022 16:31
 */
public class RandomPwd {

    private static final String SPECIAL_CHARS = "!@#3%&*_=+";

    /**
     * 生成随机密码：6位数字
     *
     * @return 密码字符串
     */
    public static String randomPasswordV1() {
        char[] chars = new char[6];
        Random rnd = new Random();
        for (int i = 0; i < 6; i++) {
            chars[i] = (char) ('0' + rnd.nextInt(10));
        }
        return new String(chars);
    }

    private static char nextChar(Random rnd) {
        switch (rnd.nextInt(4)) {
            case 0:
                return (char) ('a' + rnd.nextInt(26));
            case 1:
                return (char) ('A' + rnd.nextInt(26));
            case 2:
                return (char) ('0' + rnd.nextInt(10));
            default:
                return SPECIAL_CHARS.charAt(rnd.nextInt(SPECIAL_CHARS.length()));
        }
    }

    /**
     * 生成随机密码：简单8位
     *
     * @return
     */
    public static String randomPasswordV2() {
        char[] chars = new char[8];
        Random rnd = new Random();
        for (int i = 0; i < 8; i++) {
            chars[i] = nextChar(rnd);
        }
        return new String(chars);
    }

    private static int nextIndex(char[] chars, Random rnd) {
        int index = rnd.nextInt(chars.length);
        while (chars[index] != 0) {
            index = rnd.nextInt(chars.length);
        }
        return index;
    }

    private static char nextSpecialChar(Random rnd) {
        return SPECIAL_CHARS.charAt(rnd.nextInt(SPECIAL_CHARS.length()));
    }

    private static char nextUpperLetter(Random rnd) {
        return (char) ('A' + rnd.nextInt(26));
    }

    private static char nextLowerLetter(Random rnd) {
        return (char) ('a' + rnd.nextInt(26));
    }

    private static char nextNumLetter(Random rnd) {
        return (char) ('0' + rnd.nextInt(10));
    }

    /**
     * 生成随机密码：复杂8位
     *
     * @return 密码字符串
     */
    public static String randomPasswordV3() {
        char[] chars = new char[8];
        Random rnd = new Random();
        chars[nextIndex(chars, rnd)] = nextSpecialChar(rnd);
        chars[nextIndex(chars, rnd)] = nextUpperLetter(rnd);
        chars[nextIndex(chars, rnd)] = nextLowerLetter(rnd);
        chars[nextIndex(chars, rnd)] = nextNumLetter(rnd);
        for (int i = 0; i < 8; i++) {
            if (chars[i] == 0) {
                chars[i] = nextChar(rnd);
            }
        }
        return new String(chars);
    }

    public static void main (String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(randomPasswordV3());
        }
    }
}
