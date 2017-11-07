/**
 * @author cgtu
 * @date 2017年10月27日 下午3:25:37
 */
package free6om.research.qart4j;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.google.zxing.common.BitMatrix;

/**
 * @author cgtu
 * @date 2017年10月27日 下午3:25:37
 */
public class TestQArt
{
    private static final Logger LOGGER = Logger.getLogger(TestQArt.class);

    @SuppressWarnings("unused")
    public static void main(String[] args)
    {
        // input
        String filename = "D:\\Code\\anttu.code.github.io\\target\\classes\\free6om\\research\\qart4j\\mian.png";
        String url = "https://m.purcotton.com";

        // QR code
        int version = 6; // 1 - 40
        int mask = 2; // 0 - 7
        int quietZone = 2; // QR quiet zone 1 - 3
        int rotation = 0; // 0 - 3
        int size = 360; // 0 - 99999
        int colorBlack = (int) Long.parseLong("FF000000", 16); // 0x00000000 - 0xFFFFFFFF
        int colorWhite = (int) Long.parseLong("FFFFFFFF", 16); // 0x00000000 - 0xFFFFFFFF

        int width = 360;
        int height = 360;

        Integer marginTop = 0;
        Integer marginBottom = 0;
        Integer marginLeft = 0;
        Integer marginRight = 0;

        String outputFormat = "PNG";
        String output = "d:\\output.png";

        boolean randControl = false;
        long seed = -1;
        if (seed == -1)
        {
            seed = System.currentTimeMillis();
        }
        boolean dither = false;
        boolean onlyDataBits = false;
        boolean saveControl = false;

        // how to generate QR code
        // boolean randControl = (Boolean) options.valueOf("randControl");
        // long seed = (Long) options.valueOf("seed");
        // if (seed == -1)
        // {
        // seed = System.currentTimeMillis();
        // }
        // boolean dither = (Boolean) options.valueOf("d");
        // boolean onlyDataBits = (Boolean) options.valueOf("onlyData");
        // boolean saveControl = (Boolean) options.valueOf("saveControl");
        //
        // // output image
        // int width = (Integer) options.valueOf("w");
        // int height = (Integer) options.valueOf("h");
        //
        // Integer marginTop = options.has("mt") ? (Integer) options.valueOf("mt") : null;
        // Integer marginBottom = options.has("mb") ? (Integer) options.valueOf("mb") : null;
        // Integer marginLeft = options.has("ml") ? (Integer) options.valueOf("ml") : null;
        // Integer marginRight = options.has("mr") ? (Integer) options.valueOf("mr") : null;
        //
        // String outputFormat = (String) options.valueOf("f");
        // String output = (String) options.valueOf("o");
        try
        {
            BufferedImage input = ImageUtil.loadImage(filename, width, height);

            int qrSizeWithoutQuiet = 17 + 4 * version;
            int qrSize = qrSizeWithoutQuiet + quietZone * 2;
            if (size < qrSize)
            { // don't scale
                size = qrSize;
            }
            int scale = size / qrSize;
            int targetQrSizeWithoutQuiet = qrSizeWithoutQuiet * scale;

            Rectangle inputImageRect = new Rectangle(new Point(0, 0), width, height);
            int startX = 0, startY = 0;
            if (marginLeft != null)
            {
                startX = marginLeft;
            }
            else if (marginRight != null)
            {
                startX = width - marginRight - size;
            }
            if (marginTop != null)
            {
                startY = marginTop;
            }
            else if (marginBottom != null)
            {
                startY = height - marginBottom - size;
            }

            Rectangle qrRect = new Rectangle(new Point(startX, startY), size, size);
            Rectangle qrWithoutQuietRect = new Rectangle(
                    new Point(startX + (size - targetQrSizeWithoutQuiet) / 2,
                            startY + (size - targetQrSizeWithoutQuiet) / 2),
                    targetQrSizeWithoutQuiet, targetQrSizeWithoutQuiet);

            int[][] target = null;
            int dx = 0, dy = 0;
            BufferedImage targetImage = null;
            Rectangle targetRect = inputImageRect.intersect(qrWithoutQuietRect);
            if (targetRect == null)
            {
                LOGGER.warn("no intersect zone");
                target = new int[0][0];
            }
            else
            {
                targetImage = input.getSubimage(targetRect.start.x, targetRect.start.y, targetRect.width,
                        targetRect.height);
                int scaledWidth = targetRect.width / scale;
                int scaledHeight = targetRect.height / scale;
                BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics graphics = scaledImage.createGraphics();
                graphics.drawImage(targetImage, 0, 0, scaledWidth, scaledHeight, null);
                graphics.dispose();

                target = ImageUtil.makeTarget(scaledImage, 0, 0, scaledWidth, scaledHeight);
                dx = (qrWithoutQuietRect.start.x - targetRect.start.x) / scale;
                dy = (qrWithoutQuietRect.start.y - targetRect.start.y) / scale;
            }

            Image image = new Image(target, dx, dy, url, version, mask, rotation, randControl, seed, dither,
                    onlyDataBits, saveControl);

            QRCode qrCode = image.encode();
            BitMatrix bitMatrix = ImageUtil.makeBitMatrix(qrCode, quietZone, size);

            MatrixToImageConfig config = new MatrixToImageConfig(colorBlack, colorWhite);
            BufferedImage finalQrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, config);

            Rectangle finalRect = qrRect.union(inputImageRect);
            BufferedImage finalImage = new BufferedImage(finalRect.width, finalRect.height, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = finalImage.createGraphics();

            graphics.drawImage(input, inputImageRect.start.x - finalRect.start.x,
                    inputImageRect.start.y - finalRect.start.y, inputImageRect.width, inputImageRect.height, null);
            graphics.drawImage(finalQrImage, qrRect.start.x - finalRect.start.x, qrRect.start.y - finalRect.start.y,
                    qrRect.width, qrRect.height, null);
            graphics.dispose();

            if (outputFormat.toUpperCase().contentEquals("JPEG"))
            {
                // Creating a non Alpha channel bufferedImage so that alpha channel does not corrupt
                // jpeg.
                BufferedImage nonAlpha = new BufferedImage(finalImage.getWidth(), finalImage.getHeight(),
                        BufferedImage.TYPE_INT_RGB);
                Graphics nonAlphaGraphics = nonAlpha.createGraphics();

                nonAlphaGraphics.setColor(Color.white);
                nonAlphaGraphics.fillRect(0, 0, finalImage.getWidth(), finalImage.getHeight());
                nonAlphaGraphics.drawImage(finalImage, 0, 0, null);
                nonAlphaGraphics.dispose();

                finalImage = nonAlpha;
            }

            ImageIO.write(finalImage, outputFormat, new File(output));

        }
        catch (Exception e)
        {
            LOGGER.error("encode error", e);
        }
    }
}
