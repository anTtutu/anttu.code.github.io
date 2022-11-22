package com.anttu.tools;

import java.util.Random;

/**
 * 描述
 *
 * @ClassName：RandomMacAddress
 * @Description：随机 mac 地址
 * @author：Anttu
 * @Date：22/11/2022 17:14
 */
public class RandomMacAddress {

    private static String SEPARATOR_OF_MAC = ":";

    /**
     * 随机 mac 地址
     * @return
     */
    public static String randomMac4Qemu() {
        Random random = new Random();
        String[] mac = {String.format("%02x", 0x52), String.format("%02x", 0x54), String.format("%02x", 0x00), String.format("%02x", random.nextInt(0xff)), String.format("%02x", random.nextInt(0xff)), String.format("%02x", random.nextInt(0xff))};
        return String.join(SEPARATOR_OF_MAC, mac);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(randomMac4Qemu());
        }
    }
}
