package com.anttu.secret.sm2;

/**
 * 描述
 *
 * @ClassName：Util
 * @Description：国密SM2工具类
 * @author：Anttu
 * @Date：23/8/2021 20:56
 */
public class Util {
    /**
     * @param hexString 16进制字符串    如:"33d20046" 转换为 0x33 0xD2 0x00 0x46
     * @return
     * @Title: hexString2byte
     * @Description: 16进制字符串转字节数组
     * @since: 0.0.1
     */
    public static byte[] hexString2byte(String hexString) {
        if (null == hexString || hexString.length() % 2 != 0 || hexString.contains("null")) {
            return null;
        }
        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            bytes[i / 2] = (byte) (Integer.parseInt(hexString.substring(i, i + 2), 16) & 0xff);
        }
        return bytes;
    }

    /**
     * @param bytes 字节数组
     * @return
     * @Title: byte2hexString
     * @Description: 字节数组转换为16进制字符串    //0x33 0xD2 0x00 0x46 转换为 "33d20046" 转换和打印报文用
     * @since: 0.0.1
     */
    public static String byte2hexString(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buf.toString().toUpperCase();
    }

    /**
     * 字节数组转换为十六进制字符串
     *
     * @param b
     *            byte[] 需要转换的字节数组
     * @return String 十六进制字符串
     */
    public static String byteToHex(byte b[]) {
        if (b == null) {
            throw new IllegalArgumentException(
                    "Argument b ( byte array ) is null! ");
        }
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xff);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }
}