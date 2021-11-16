package com.anttu.qrcode.decode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * 描述
 *
 * @ClassName：QrCodeDecoder
 * @Description：二维码解析
 * @author：Anttu
 * @Date：15/11/2021 17:30
 */
public class QrCodeDecoderWrapper {
    /**
     * 读取二维码中的内容, 并返回
     * 有些图还是会报错，识别精度不够，后续有好的识别方法再补充
     *
     * @param qrCodePath 二维码图片的地址
     * @return 返回二维码的内容
     * @throws IOException       读取二维码失败
     * @throws FormatException   二维码解析失败
     * @throws ChecksumException
     * @throws NotFoundException
     */
    public static String decode(String qrCodePath) throws IOException, NotFoundException {
        MultiFormatReader formatReader = new MultiFormatReader();
        File file = new File(qrCodePath);
        //读取此文件识别成一个图片
        BufferedImage image = ImageIO.read(file);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

        //定义二维码参数
        HashMap<DecodeHintType, Object> hints = new HashMap();
        // 解码设置编码方式为：utf-8，
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
        // 优化精度 识别难度提升，速度下降
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);

        Result result = formatReader.decode(binaryBitmap, hints);

        System.out.println("解析结果：" + result.toString());
        System.out.println("二维码格式类型：" + result.getBarcodeFormat());
        System.out.println("二维码文本内容：" + result.getText());

        return result.getText();
    }

    public static void main (String[] args) throws NotFoundException, IOException {
        String qrcodePath = "/Images/b9e4f1d9-c01f-4d35-a547-b9c92b11d243.png";
        System.out.println("解析结果：" + decode(qrcodePath));
    }
}
