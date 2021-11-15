package com.anttu.demo;

import com.anttu.asciimg.ascii_charater_cache.AsciiImgCache;
import com.anttu.asciimg.character_fit_strategy.BestCharacterFitStrategy;
import com.anttu.asciimg.character_fit_strategy.ColorSquareErrorFitStrategy;
import com.anttu.asciimg.character_fit_strategy.StructuralSimilarityFitStrategy;
import com.anttu.asciimg.converter.AsciiToImageConverter;
import com.anttu.asciimg.converter.AsciiToStringConverter;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 描述
 *
 * @ClassName：TestExamples
 * @Description：测试 image
 * @author：Anttu
 * @Date：15/11/2021 14:11
 */
public class TestExamples {
    @Test
    public void testExample () throws IOException {
        // initialize caches
        AsciiImgCache smallFontCache = AsciiImgCache.create(new Font("Courier",
                Font.BOLD, 6));
        AsciiImgCache mediumBlackAndWhiteCache = AsciiImgCache.create(new Font(
                "Courier", Font.BOLD, 10), new char[] {'\\', ' ', '/'});
        AsciiImgCache largeFontCache = AsciiImgCache.create(new Font("Courier",
                Font.PLAIN, 16));

        // load image
        BufferedImage portraitImage = ImageIO.read(new File(
                "examples/portrait.png"));

        // initialize algorithms
        BestCharacterFitStrategy squareErrorStrategy = new ColorSquareErrorFitStrategy();
        BestCharacterFitStrategy ssimStrategy = new StructuralSimilarityFitStrategy();

        // initialize converters
        AsciiToImageConverter imageConverter = new AsciiToImageConverter(
                smallFontCache, squareErrorStrategy);
        AsciiToStringConverter stringConverter = new AsciiToStringConverter(
                largeFontCache, ssimStrategy);

        // small font images, square error
        imageConverter.setCharacterCache(smallFontCache);
        imageConverter.setCharacterFitStrategy(squareErrorStrategy);
        ImageIO.write(imageConverter.convertImage(portraitImage), "png",
                new File("examples/portrait_small_square_error.png"));

        // medium font images, square error
        imageConverter.setCharacterCache(mediumBlackAndWhiteCache);
        imageConverter.setCharacterFitStrategy(squareErrorStrategy);
        ImageIO.write(imageConverter.convertImage(portraitImage), "png",
                new File("examples/portrait_medium_square_error.png"));

        // large font images, square error
        imageConverter.setCharacterCache(largeFontCache);
        imageConverter.setCharacterFitStrategy(squareErrorStrategy);
        ImageIO.write(imageConverter.convertImage(portraitImage), "png",
                new File("examples/portrait_large_square_error.png"));

        // small font images, ssim
        imageConverter.setCharacterCache(smallFontCache);
        imageConverter.setCharacterFitStrategy(ssimStrategy);
        ImageIO.write(imageConverter.convertImage(portraitImage), "png",
                new File("examples/portrait_small_ssim.png"));

        // medium font images, ssim error
        imageConverter.setCharacterCache(mediumBlackAndWhiteCache);
        imageConverter.setCharacterFitStrategy(ssimStrategy);
        ImageIO.write(imageConverter.convertImage(portraitImage), "png",
                new File("examples/portrait_medium_ssim.png"));

        // large font images, ssim
        imageConverter.setCharacterCache(largeFontCache);
        imageConverter.setCharacterFitStrategy(ssimStrategy);
        ImageIO.write(imageConverter.convertImage(portraitImage), "png",
                new File("examples/portrait_large_ssim.png"));

        // string converter, output to console
        System.out.println(stringConverter.convertImage(portraitImage));
    }
}