package com.anttu.tools;

import org.bouncycastle.asn1.x509.Targets;

/**
 * 描述
 *
 * @ClassName：RandomMobile
 * @Description：随机手机号
 * @author：Anttu
 * @Date：22/11/2022 16:46
 */
public class RandomMobile {
    public static String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static String[] telFirst = "133,149,153,173,177,180,181,189,190,191,193,199,130,131,132,145,155,156,166,167,171,175,176,185,186,196,134,135,136,137,138,139,144,147,148,150,151,152,157,158,159,172,178,182,183,184,187,188,195,197,198,192,174,162,165,170,".split(",");

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    /**
     * 生成11位手机号码
     *
     * @return
     */
    public static String getMobile() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }

    /**
     * 生成8位电话号码
     *
     * @return
     */
    public static String getTelephone() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return second + third;
    }

    public static void main (String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getTelephone());
        }
    }
}
