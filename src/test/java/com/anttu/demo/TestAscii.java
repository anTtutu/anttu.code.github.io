package com.anttu.demo;

import com.anttu.asciimg.ascii_charater_cache.AsciiImgCache;
import com.anttu.asciimg.character_fit_strategy.BestCharacterFitStrategy;
import com.anttu.asciimg.character_fit_strategy.ColorSquareErrorFitStrategy;
import com.anttu.asciimg.character_fit_strategy.StructuralSimilarityFitStrategy;
import com.anttu.asciimg.converter.AsciiToImageConverter;
import com.anttu.asciimg.converter.AsciiToStringConverter;
import com.anttu.asciimg.converter.GifToAsciiConvert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 描述
 *
 * @ClassName：TestAscii
 * @Description：测试
 * @author：Anttu
 * @Date：15/11/2021 14:18
 */
public class TestAscii {

    @Test
    public void testClass () throws IOException {
        // initialize caches
        AsciiImgCache smallFontCache = AsciiImgCache.create(new Font("Courier",
                Font.BOLD, 6));
        AsciiImgCache mediumBlackAndWhiteCache = AsciiImgCache.create(new Font(
                "Courier", Font.BOLD, 10), new char[] {'\\', ' ', '/'});
        AsciiImgCache largeFontCache = AsciiImgCache.create(new Font("Courier",
                Font.PLAIN, 16));

        // load image
        BufferedImage portraitImage = ImageIO.read(new File(
                "examples/class.png"));

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
                new File("examples/class_small_square_error.png"));

        // string converter, output to console
        System.out.println(stringConverter.convertImage(portraitImage));
    }

    @Test
    public void testStar () throws IOException {
        // initialize caches
        AsciiImgCache smallFontCache = AsciiImgCache.create(new Font("Courier",
                Font.BOLD, 6));
        AsciiImgCache mediumBlackAndWhiteCache = AsciiImgCache.create(new Font(
                "Courier", Font.BOLD, 10), new char[] {'\\', ' ', '/'});
        AsciiImgCache largeFontCache = AsciiImgCache.create(new Font("Courier",
                Font.PLAIN, 16));

        // load image
        BufferedImage portraitImage = ImageIO.read(new File(
                "examples/star.png"));

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
                new File("examples/star_small_square_error.png"));

        // string converter, output to console
        System.out.println(stringConverter.convertImage(portraitImage));
    }

    @Test
    public void tesTower () throws IOException {
        // initialize caches
        AsciiImgCache smallFontCache = AsciiImgCache.create(new Font("Courier",
                Font.BOLD, 6));
        AsciiImgCache mediumBlackAndWhiteCache = AsciiImgCache.create(new Font(
                "Courier", Font.BOLD, 10), new char[] {'\\', ' ', '/'});
        AsciiImgCache largeFontCache = AsciiImgCache.create(new Font("Courier",
                Font.PLAIN, 16));

        // load image
        BufferedImage portraitImage = ImageIO.read(new File(
                "examples/tower.jpeg"));

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
                new File("examples/tower_small_square_error.png"));

        // string converter, output to console
        System.out.println(stringConverter.convertImage(portraitImage));
    }

    @Test
    public void tesTowerColor () throws IOException {
        // initialize caches
        AsciiImgCache smallFontCache = AsciiImgCache.create(new Font("Courier",
                Font.BOLD, 6));
        AsciiImgCache mediumBlackAndWhiteCache = AsciiImgCache.create(new Font(
                "Courier", Font.BOLD, 10), new char[] {'\\', ' ', '/'});
        AsciiImgCache largeFontCache = AsciiImgCache.create(new Font("Courier",
                Font.PLAIN, 16));

        // load image
        BufferedImage portraitImage = ImageIO.read(new File(
                "examples/tower-color.jpeg"));

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
                new File("examples/tower-color_small_square_error.png"));

        // string converter, output to console
        System.out.println(stringConverter.convertImage(portraitImage));
    }

    @Test
    public void tesFlower () throws IOException {
        // initialize caches
        AsciiImgCache smallFontCache = AsciiImgCache.create(new Font("Courier",
                Font.BOLD, 6));
        AsciiImgCache mediumBlackAndWhiteCache = AsciiImgCache.create(new Font(
                "Courier", Font.BOLD, 10), new char[] {'\\', ' ', '/'});
        AsciiImgCache largeFontCache = AsciiImgCache.create(new Font("Courier",
                Font.PLAIN, 16));

        // load image
        BufferedImage portraitImage = ImageIO.read(new File(
                "examples/flower.jpg"));

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
                new File("examples/flower_small_square_error.png"));

        // string converter, output to console
        System.out.println(stringConverter.convertImage(portraitImage));
    }

    @Test
    public void testGifDuck () {
        // initialize caches
        AsciiImgCache smallFontCache = AsciiImgCache.create(new Font("Courier", Font.BOLD, 6));
        // initialize ssimStrategy
        BestCharacterFitStrategy ssimStrategy = new StructuralSimilarityFitStrategy();

        String srcFilePath = "examples/duck.gif";
        String disFilePath = "examples/duck-ascii.gif";
        int delay = 100;//ms

        GifToAsciiConvert asciiConvert = new GifToAsciiConvert(smallFontCache, ssimStrategy);

        asciiConvert.convertGitToAscii(srcFilePath, disFilePath, delay,0);
    }

    @Test
    public void testGifDuck3 () {
        // initialize caches
        AsciiImgCache smallFontCache = AsciiImgCache.create(new Font("Courier", Font.BOLD, 6));
        // initialize ssimStrategy
        BestCharacterFitStrategy ssimStrategy = new StructuralSimilarityFitStrategy();

        String srcFilePath = "examples/duck-3.gif";
        String disFilePath = "examples/duck-3-ascii.gif";
        int delay = 100;//ms

        GifToAsciiConvert asciiConvert = new GifToAsciiConvert(smallFontCache, ssimStrategy);

        asciiConvert.convertGitToAscii(srcFilePath, disFilePath, delay,0);
    }

    @Test
    public void testGifDuck9 () {
        // initialize caches
        AsciiImgCache smallFontCache = AsciiImgCache.create(new Font("Courier", Font.BOLD, 6));
        // initialize ssimStrategy
        BestCharacterFitStrategy ssimStrategy = new StructuralSimilarityFitStrategy();

        String srcFilePath = "examples/duck-9.gif";
        String disFilePath = "examples/duck-9-ascii.gif";
        int delay = 100;//ms

        GifToAsciiConvert asciiConvert = new GifToAsciiConvert(smallFontCache, ssimStrategy);

        asciiConvert.convertGitToAscii(srcFilePath, disFilePath, delay,0);
    }

    @Test
    public void testGifDuck16 () {
        // initialize caches
        AsciiImgCache smallFontCache = AsciiImgCache.create(new Font("Courier", Font.BOLD, 6));
        // initialize ssimStrategy
        BestCharacterFitStrategy ssimStrategy = new StructuralSimilarityFitStrategy();

        String srcFilePath = "examples/duck-16.gif";
        String disFilePath = "examples/duck-16-ascii.gif";
        int delay = 100;//ms

        GifToAsciiConvert asciiConvert = new GifToAsciiConvert(smallFontCache, ssimStrategy);

        asciiConvert.convertGitToAscii(srcFilePath, disFilePath, delay,0);
    }
}
