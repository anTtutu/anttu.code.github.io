package com.anttu.tools;

/**
 * 简易加密工具类，当前主要用于唯一KEY生成工具类使用
 *
 * @author hk
 */
public class Caesar
{
    private String table;
    private int seedA = 1103515245;
    private int seedB = 12345;

    public Caesar(String table, int seed)
    {
        this.table = chaos(table, seed, table.length());
    }

    public Caesar(String table)
    {
        this(table, 11);
    }

    public Caesar()
    {
        this(11);
    }

    public Caesar(int seed)
    {
        this("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", seed);
    }

    public char dict(int i, boolean reverse)
    {
        int s = table.length(), index = reverse ? s - i : i;
        return table.charAt(index);
    }

    public int dict(char c, boolean reverse)
    {
        int s = table.length(), index = table.indexOf(c);
        return reverse ? s - index : index;
    }

    public int seed(int seed)
    {
        long temp = seed;
        return (int) ((temp * seedA + seedB) & 0x7fffffffL);
    }

    public String chaos(String data, int seed, int cnt)
    {
        StringBuffer buf = new StringBuffer(data);
        char tmp;
        int a, b, r = data.length();
        for (int i = 0; i < cnt; i += 1)
        {
            seed = seed(seed);
            a = seed % r;
            seed = seed(seed);
            b = seed % r;
            tmp = buf.charAt(a);
            buf.setCharAt(a, buf.charAt(b));
            buf.setCharAt(b, tmp);
        }
        return buf.toString();
    }

    public String crypto(boolean reverse, int key, String text)
    {
        String ret = null;
        StringBuilder buf = new StringBuilder();
        int m, s = table.length(), e = text.length();

        for (int i = 0; i < e; i += 1)
        {
            m = dict(text.charAt(i), reverse);
            if (m < 0)
            {
                break;
            }
            m = m + key + i;
            buf.append(dict(m % s, reverse));
        }
        if (buf.length() == e)
        {
            ret = buf.toString();
        }
        return ret;
    }

    public String encode(int key, String text)
    {
        return crypto(false, key, text);

    }

    public String decode(int key, String text)
    {
        return crypto(true, key, text);
    }

    public static void main(String[] args)
    {
        Caesar caesar = new Caesar("0123456789qwertyuiopasdfghjklzxcvbnm");
        String data = caesar.encode(32, "23423");
        System.out.println(data);
        String pass = caesar.decode(32, data);
        System.out.println(pass);

    }

}
