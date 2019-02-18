/**
 *
 */
package com.anttu.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.anttu.tools.GifEncoder;

/**
 * 验证码工具类：
 * 随机字体、字体样式、字体大小（验证码图片宽度 - 8 ~ 验证码图片宽度 + 10）
 * 彩色字符 每个字符的颜色随机，一定会不相同
 * 随机字符 阿拉伯数字 + 小写字母 + 大写字母
 * 3D中空自定义字体，需要单独使用，只有阿拉伯数字和大写字母
 *
 * @author hk
 * @date 2017年5月9日 下午7:27:55
 */
public class RandomVerifyImgCodeUtils
{
    private static Logger logger = Logger.getLogger(RandomVerifyImgCodeUtils.class);
    /**
     * 随机类
     */
    private static Random random = new Random();

    // 放到session中的key
    public static final String RANDOMCODEKEY = "RANDOM_VALIDATE_IMAGE_KEY";

    // 验证码来源范围，去掉了0,1,I,O,l,o几个容易混淆的字符
    public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";

    private static Font baseFont;
    static
    {
        try
        {
            baseFont = Font.createFont(Font.TRUETYPE_FONT,
                    RandomVerifyImgCodeUtils.class.getClassLoader().getResourceAsStream("fonts/ActionJackson.TTF"));
        }
        catch (FontFormatException e)
        {
            logger.error("new img font font format failed. e: " + e.getMessage(), e);
        }
        catch (IOException e)
        {
            logger.error("new img font io failed. e: " + e.getMessage(), e);
        }
    }

    // 字体类型
    private static String[] fontName =
    {
            "Algerian", "Arial", "Arial Black", "Agency FB", "Calibri", "Cambria", "Gadugi", "Georgia", "Consolas",
            "Comic Sans MS", "Courier New", "Gill sans", "Time News Roman", "Tahoma", "Quantzite", "Verdana"
    };

    // 字体样式
    private static int[] fontStyle =
    {
            Font.BOLD, Font.ITALIC, Font.ROMAN_BASELINE, Font.PLAIN, Font.BOLD + Font.ITALIC
    };

    // 颜色
    private static Color[] colorRange =
    {
            Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK,
            Color.YELLOW, Color.GREEN, Color.BLUE, Color.DARK_GRAY, Color.BLACK, Color.RED
    };

    /**
     * 使用系统默认字符源生成验证码
     *
     * @param verifySize
     *            验证码长度
     * @param uuid
     *            App端传的参数，app没有session和cookie，必须用设备号在redis记录图形验证码的值
     * @param platFormName
     *            平台名称：pc\wap\app app比较特殊，需要单独处理
     * @param request
     *            请求
     * @return
     */
    public static String generateVerifyCode(int verifySize)
    {
        return generateVerifyCode(verifySize, VERIFY_CODES);
    }

    /**
     * 使用指定源生成验证码
     *
     * @param verifySize
     *            验证码长度
     * @param sources
     *            验证码字符源
     * @return
     */
    private static String generateVerifyCode(int verifySize, String sources)
    {
        if (sources == null || sources.length() == 0)
        {
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++)
        {
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }

        return verifyCode.toString();
    }

    /**
     * 输出指定验证码图片流
     *
     * @param w
     *            验证码图片的宽
     * @param h
     *            验证码图片的高
     * @param os
     *            流
     * @param code
     *            验证码
     * @param type
     *            场景类型，login：登录，
     *            coupons：领券 登录清晰化，领券模糊化
     *            3D: 3D中空自定义字体
     *            GIF：普通动态GIF
     *            GIF3D：3D动态GIF
     *            mix2: 普通字体和3D字体混合
     *            mixGIF: 混合动态GIF
     * @throws IOException
     */
    public static void outputImage(int w, int h, OutputStream os, String code, String type) throws IOException
    {
        int verifySize = code.length();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = new Color[5];
        Color[] colorSpaces = colorRange;
        float[] fractions = new float[colors.length];
        for (int i = 0; i < colors.length; i++)
        {
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
            fractions[i] = rand.nextFloat();
        }
        Arrays.sort(fractions);

        g2.setColor(Color.GRAY);// 设置边框色
        g2.fillRect(0, 0, w, h);

        Color c = getRandColor(200, 250);
        g2.setColor(c);// 设置背景色
        g2.fillRect(0, 2, w, h - 4);

        char[] charts = code.toCharArray();
        for (int i = 0; i < charts.length; i++)
        {
            g2.setColor(c);// 设置背景色
            g2.setFont(getRandomFont(h, type));
            g2.fillRect(0, 2, w, h - 4);
        }

        // 1.绘制干扰线
        Random random = new Random();
        g2.setColor(getRandColor(160, 200));// 设置线条的颜色
        int lineNumbers = 20;
        if (type.equals("login") || type.contains("mix") || type.contains("3D"))
        {
            lineNumbers = 20;
        }
        else if (type.equals("coupons"))
        {
            lineNumbers = getRandomDrawLine();
        }
        else
        {
            lineNumbers = getRandomDrawLine();
        }
        for (int i = 0; i < lineNumbers; i++)
        {
            int x = random.nextInt(w - 1);
            int y = random.nextInt(h - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g2.drawLine(x, y, x + xl + 40, y + yl + 20);
        }

        // 2.添加噪点
        float yawpRate = 0.05f;
        if (type.equals("login") || type.contains("mix") || type.contains("3D"))
        {
            yawpRate = 0.05f; // 噪声率
        }
        else if (type.equals("coupons"))
        {
            yawpRate = getRandomDrawPoint(); // 噪声率
        }
        else
        {
            yawpRate = getRandomDrawPoint(); // 噪声率
        }
        int area = (int) (yawpRate * w * h);
        for (int i = 0; i < area; i++)
        {
            int x = random.nextInt(w);
            int y = random.nextInt(h);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }

        // 3.使图片扭曲
        shear(g2, w, h, c);

        char[] chars = code.toCharArray();
        Double rd = rand.nextDouble();
        Boolean rb = rand.nextBoolean();

        if (type.equals("login"))
        {
            for (int i = 0; i < verifySize; i++)
            {
                g2.setColor(getRandColor(100, 160));
                g2.setFont(getRandomFont(h, type));

                AffineTransform affine = new AffineTransform();
                affine.setToRotation(Math.PI / 4 * rd * (rb ? 1 : -1), (w / verifySize) * i + (h - 4) / 2, h / 2);
                g2.setTransform(affine);
                g2.drawOval(random.nextInt(w), random.nextInt(h), 5 + random.nextInt(10), 5 + random.nextInt(10));
                g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + (h - 4) / 2 - 10);
            }

            g2.dispose();
            ImageIO.write(image, "jpg", os);
        }
        else if (type.contains("GIF") || type.contains("mixGIF"))
        {
            GifEncoder gifEncoder = new GifEncoder(); // gif编码类，这个利用了洋人写的编码类
            // 生成字符
            gifEncoder.start(os);
            gifEncoder.setQuality(180);
            gifEncoder.setDelay(150);
            gifEncoder.setRepeat(0);

            AlphaComposite ac3;
            for (int i = 0; i < verifySize; i++)
            {
                g2.setColor(getRandColor(100, 160));
                g2.setFont(getRandomFont(h, type));
                for (int j = 0; j < verifySize; j++)
                {
                    AffineTransform affine = new AffineTransform();
                    affine.setToRotation(Math.PI / 4 * rd * (rb ? 1 : -1), (w / verifySize) * i + (h - 4) / 2, h / 2);
                    g2.setTransform(affine);
                    g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + (h - 4) / 2 - 10);

                    ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getAlpha(j, i, verifySize));
                    g2.setComposite(ac3);
                    g2.drawOval(random.nextInt(w), random.nextInt(h), 5 + random.nextInt(10), 5 + random.nextInt(10));
                    gifEncoder.addFrame(image);
                    image.flush();
                }
            }
            gifEncoder.finish();
            g2.dispose();
        }
        else
        {
            for (int i = 0; i < verifySize; i++)
            {
                g2.setColor(getRandColor(100, 160));
                g2.setFont(getRandomFont(h, type));

                AffineTransform affine = new AffineTransform();
                affine.setToRotation(Math.PI / 4 * rd * (rb ? 1 : -1), (w / verifySize) * i + (h - 4) / 2, h / 2);
                g2.setTransform(affine);
                g2.drawOval(random.nextInt(w), random.nextInt(h), 5 + random.nextInt(10), 5 + random.nextInt(10));
                g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + (h - 4) / 2 - 10);
            }

            g2.dispose();
            ImageIO.write(image, "jpg", os);
        }
    }

    /**
     * 获取随机颜色
     *
     * @param fc
     * @param bc
     * @return
     */
    private static Color getRandColor(int fc, int bc)
    {
        if (fc > 255)
        {
            fc = 255;
        }
        if (bc > 255)
        {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private static int getRandomIntColor()
    {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb)
        {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private static int[] getRandomRgb()
    {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++)
        {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    /**
     * 随机字体、随机风格、随机大小
     *
     * @param h
     *            验证码图片高
     * @return
     */
    private static Font getRandomFont(int h, String type)
    {
        // 字体
        String name = fontName[random.nextInt(fontName.length)];
        // 字体样式
        int style = fontStyle[random.nextInt(fontStyle.length)];
        // 字体大小
        int size = getRandomFontSize(h);

        if (type.equals("login"))
        {
            return new Font(name, style, size);
        }
        else if (type.equals("coupons"))
        {
            return new Font(name, style, size);
        }
        else if (type.contains("3D"))
        {
            return new ImgFontByte().getFont(size, style);
        }
        else if (type.contains("mix"))
        {
            int flag = random.nextInt(10);
            if (flag > 4)
            {
                return new Font(name, style, size);
            }
            else
            {
                return new ImgFontByte().getFont(size, style);
            }
        }
        else
        {
            return new Font(name, style, size);
        }
    }

    /**
     * 干扰线按范围获取随机数
     *
     * @return
     */
    private static int getRandomDrawLine()
    {
        int min = 20;
        int max = 155;
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 噪点数率按范围获取随机数
     *
     * @return
     */
    private static float getRandomDrawPoint()
    {
        float min = 0.05f;
        float max = 0.1f;
        return min + ((max - min) * random.nextFloat());
    }

    /**
     * 获取字体大小按范围随机
     *
     * @param h
     *            验证码图片高
     * @return
     */
    private static int getRandomFontSize(int h)
    {
        int min = h - 8;
        // int max = 46;
        Random random = new Random();
        return random.nextInt(11) + min;
    }

    /**
     * 3D中空字体自定义属性类
     *
     * @author hk
     * @date 2017年5月15日 下午3:27:52
     */
    static class ImgFontByte
    {
        public Font getFont(int fontSize, int fontStype)
        {
            try
            {
                Font font = baseFont;
                if (baseFont == null)
                {
                    font = Font.createFont(Font.TRUETYPE_FONT, RandomVerifyImgCodeUtils.class.getClassLoader()
                            .getResourceAsStream("fonts/ActionJackson.TTF"));
                }
                return font.deriveFont(fontStype, fontSize);
            }
            catch (Exception e)
            {
                return new Font("Arial", fontStype, fontSize);
            }
        }
    }

    /**
     * 字符和干扰线扭曲
     *
     * @param g
     *            绘制图形的java工具类
     * @param w1
     *            验证码图片宽
     * @param h1
     *            验证码图片高
     * @param color
     *            颜色
     */
    private static void shear(Graphics g, int w1, int h1, Color color)
    {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    /**
     * x轴扭曲
     *
     * @param g
     *            绘制图形的java工具类
     * @param w1
     *            验证码图片宽
     * @param h1
     *            验证码图片高
     * @param color
     *            颜色
     */
    private static void shearX(Graphics g, int w1, int h1, Color color)
    {
        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++)
        {
            double d = (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * phase) / frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap)
            {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }
    }

    /**
     * y轴扭曲
     *
     * @param g
     *            绘制图形的java工具类
     * @param w1
     *            验证码图片宽
     * @param h1
     *            验证码图片高
     * @param color
     *            颜色
     */
    private static void shearY(Graphics g, int w1, int h1, Color color)
    {
        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++)
        {
            double d = (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * phase) / frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap)
            {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }
        }
    }

    /**
     * 获取透明度,从0到1,自动计算步长
     *
     * @param i
     * @param j
     * @return float 透明度
     */
    private static float getAlpha(int i, int j, int verifySize)
    {
        int num = i + j;
        float r = (float) 1 / verifySize, s = (verifySize + 1) * r;
        return num > verifySize ? (num * r - s) : num * r;
    }

    /**
     * 生成指定验证码图像文件 - 本地测试生成图片查看效果
     *
     * @param w
     *            验证码图片宽
     * @param h
     *            验证码图片高
     * @param outputFile
     *            文件流
     * @param code
     *            随机验证码
     * @throws IOException
     */
    public static void outputImage(int w, int h, File outputFile, String code) throws IOException
    {
        if (outputFile == null)
        {
            return;
        }
        File dir = outputFile.getParentFile();
        if (!dir.exists())
        {
            dir.mkdirs();
        }
        try
        {
            outputFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(outputFile);
            // outputImage(w, h, fos, code, "login"); //测试登录，噪点和干扰线为0.05f和20
            // outputImage(w, h, fos, code, "coupons"); //测试领券，噪点和干扰线为范围随机值0.05f ~ 0.1f和20 ~ 155
            // outputImage(w, h, fos, code, "3D"); //测试领券，噪点和干扰线为范围随机值0.05f ~ 0.1f和20 ~ 155
            // outputImage(w, h, fos, code, "GIF"); //测试领券，噪点和干扰线为范围随机值0.05f ~ 0.1f和20 ~ 155
            // outputImage(w, h, fos, code, "GIF3D"); //测试领券，噪点和干扰线为范围随机值0.05f ~ 0.1f和20 ~ 155
            // outputImage(w, h, fos, code, "mix2"); //测试领券，噪点和干扰线为范围随机值0.05f ~ 0.1f和20 ~ 155
            outputImage(w, h, fos, code, "mixGIF"); // 测试领券，噪点和干扰线为范围随机值0.05f ~ 0.1f和20 ~ 155
            fos.close();
        }
        catch (IOException e)
        {
            throw e;
        }
    }

    /**
     * 本地测试类，可以生成样例验证码图片供观看效果
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        File dir = new File("E:/test/verifies8");
        int w = 120, h = 48;
        for (int i = 0; i < 150; i++)
        {
            String verifyCode = generateVerifyCode(4);
            File file = new File(dir, verifyCode + ".gif");
            outputImage(w, h, file, verifyCode);
        }
    }
}
