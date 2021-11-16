package com.anttu.qrcode.art;

import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeGenWrapper;
import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeOptions;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.IOException;

/**
 * 描述
 *
 * @ClassName：ArtQrCode
 * @Description：艺术二维码
 * @author：Anttu
 * @Date：15/11/2021 17:28
 */
public class ArtQrCode {
    public static void main (String[] args) throws IOException, WriterException {
        String msg = "http://weixin.qq.com/r/FS9waAPEg178rUcL93oH";
        int size = 500;

        // 文字
        boolean ans = QrCodeGenWrapper.of(msg)
                .setW(size)
                .setH(size)
                // 不指定text时，默认文本为千字文，宋体加粗
                .setQrText("Hello World")
                .setDetectSpecial()
                .setErrorCorrection(ErrorCorrectionLevel.H)
                .setDrawStyle(QrCodeOptions.DrawStyle.TXT)
                .setPicType("png")
                .asFile("./qrcode/fontQr1.png");

        // 爱心素材生成的二维码图片，如下图中的图1，请注意我们的二维码背景渲染色指定为透明，最终输出的二维码也是带透明度的png图片，
        /*ans = QrCodeGenWrapper.of(msg)
                .setW(size)
                .setH(size)
                .setErrorCorrection(ErrorCorrectionLevel.M)
                .setDrawBgColor(ColorUtil.OPACITY)
                .setDetectImg(ArtQrCode.class.getClassLoader().getResourceAsStream("love/01.png"))
                .setDrawStyle(QrCodeOptions.DrawStyle.IMAGE)
                .addImg(1, 1, ArtQrCode.class.getClassLoader().getResourceAsStream("love/003_01.png"))
                .addImg(4, 1, ArtQrCode.class.getClassLoader().getResourceAsStream("love/004.png"))
                .addImg(1, 4, ArtQrCode.class.getClassLoader().getResourceAsStream("love/004_02.png"))
                .asFile("src/qrcode/imgQr1.png");

        // 几何素材生成的二维码图片
        ans = QrCodeGenWrapper.of(msg)
                .setW(size)
                .setH(size)
                .setErrorCorrection(ErrorCorrectionLevel.H)
                // 因为素材为png透明图，我们这里设置二维码的背景为透明，输出更加优雅
                .setDrawBgColor(ColorUtil.OPACITY)
                .setDetectImg(ArtQrCode.class.getClassLoader().getResourceAsStream("jihe/PDP.png"))
                .setDrawStyle(QrCodeOptions.DrawStyle.IMAGE)
                .addImg(1, 1, ArtQrCode.class.getClassLoader().getResourceAsStream("jihe/a.png"))
                .addImg(3, 1, ArtQrCode.class.getClassLoader().getResourceAsStream("jihe/b.png"))
                .addImg(1, 3, ArtQrCode.class.getClassLoader().getResourceAsStream("jihe/c.png"))
                .addImg(3, 2, ArtQrCode.class.getClassLoader().getResourceAsStream("jihe/e.png"))
                .addImg(2, 3, ArtQrCode.class.getClassLoader().getResourceAsStream("jihe/f.png"))
                .addImg(2, 2, ArtQrCode.class.getClassLoader().getResourceAsStream("jihe/g.png"))
                .addImg(3, 4, ArtQrCode.class.getClassLoader().getResourceAsStream("jihe/h.png"))
                .setPicType("png")
                .asFile("src/qrcode/imgQr2.png");

        // 除了指定二维码的”前置图片“之外，还可以指定”后置图片“
        // 注意下图中的图3，其中黑色小点为素材图片，对应二维码中黑色方块;
        // 白色小点也是素材图片，对应二维码中的白色方块
        ans = QrCodeGenWrapper.of(msg)
                .setW(size)
                .setH(size)
                .setErrorCorrection(ErrorCorrectionLevel.M)
                .setDrawBgColor(ColorUtil.OPACITY)
                .setDrawBgImg(ArtQrCode.class.getClassLoader().getResourceAsStream("overbg/b.png"))
                .setDrawStyle(QrCodeOptions.DrawStyle.IMAGE)
                .setDrawImg(ArtQrCode.class.getClassLoader().getResourceAsStream("overbg/a.png"))
                .asFile("src/qrcode/imgQr3.png");*/
    }
}
