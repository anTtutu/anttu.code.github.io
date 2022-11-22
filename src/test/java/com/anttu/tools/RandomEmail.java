package com.anttu.tools;

/**
 * 描述
 *
 * @ClassName：RandomEmail
 * @Description：随机邮箱生成工具
 * @author：Anttu
 * @Date：22/11/2022 16:43
 */
public class RandomEmail {
    public static String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static final String[] email_suffix = "@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    /**
     * 随机邮箱
     *
     * @param min
     * @param max
     * @return
     */
    public static String getEmail(int min, int max) {
        int length = getNum(min, max);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * base.length());
            sb.append(base.charAt(number));
        }
        sb.append(email_suffix[(int) (Math.random() * email_suffix.length)]);
        return sb.toString();
    }

    public static void main (String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getEmail(4, 8));
        }
    }
}
