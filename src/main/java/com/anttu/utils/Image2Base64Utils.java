package com.anttu.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * 描述
 *
 * @ClassName：Image2Base64Utils
 * @Description：图片转 base64
 * @author：Anttu
 * @Date：15/11/2021 09:11
 */
public class Image2Base64Utils
{
    /**
     * 图片转化成base64字符串
     * 
     * @param imgPath
     * @return
     */
    public static String GetImageStr(String imgPath)
    {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        // 待处理的图片
        String imgFile = imgPath;
        InputStream in = null;
        byte[] data;
        // 返回Base64编码过的字节数组字符串
        String encode = null;
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        try
        {
            // 读取图片字节数组
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            encode = encoder.encode(data);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return encode;
    }

    /**
     * base64字符串转化成图片
     *
     * @param imgData
     *            图片编码
     * @param imgFilePath
     *            存放到本地路径
     * @return
     * @throws IOException
     */
    public static boolean GenerateImage(String imgData, String imgFilePath) throws IOException {
        // 对字节数组字符串进行Base64解码并生成图片
        if (imgData == null)
        {
            // 图像数据为空
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try
        {
            out = new FileOutputStream(imgFilePath);
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgData);
            for (int i = 0; i < b.length; ++i)
            {
                if (b[i] < 0)
                {
                    // 调整异常数据
                    b[i] += 256;
                }
            }
            out.write(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            out.flush();
            out.close();
            return true;
        }
    }
}
