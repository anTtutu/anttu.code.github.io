package com.anttu.qrcode.decode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述
 *
 * @ClassName：Demo
 * @Description：demo
 * @author：Anttu
 * @Date：16/11/2021 17:03
 */
public class Demo {
    /**
     * 流图片解码
     * @param 	input
     * @return 	QRResult
     */
    public static QRResult decoder(InputStream input) {
        BufferedImage image;
        try {
            if (null == input) {
                return new QRResult("得到的文件不存在！",300);
            }
            image = ImageIO.read(input);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Map<DecodeHintType,Object> hints = new LinkedHashMap<DecodeHintType,Object>();
            // 解码设置编码方式为：utf-8，
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
            // 优化精度
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
            // 复杂模式，开启PURE_BARCODE模式
            hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
            Result result = new MultiFormatReader().decode(bitmap, hints);
            String txt = result.getText();
            return new QRResult("成功解码！",200,txt);
        } catch (Exception e) {
//           LoggerUtils.error(MatrixUtil.class,"解码失败。", e);
            return new QRResult("解码失败，请确认的你二维码是否正确，或者图片有多个二维码！",500);
        }
    }

    /**
     * 返回值处理
     */
    public static class QRResult{
        public QRResult(String message,int status) {
            this.message = message;
            this.status = status;
            this.txt = "";
        }
        public QRResult(String message,int status,String txt) {
            this.message = message;
            this.status = status;
            this.txt = txt;
        }
        //解码内容
        private String txt;
        //返回的消息内容
        private String message;
        //返回的状态码，200：成功，500：错误
        private int status ;
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public int getStatus() {
            return status;
        }
        public void setStatus(int status) {
            this.status = status;
        }
        public String getTxt() {
            return txt;
        }
        public void setTxt(String txt) {
            this.txt = txt;
        }
    }
}
