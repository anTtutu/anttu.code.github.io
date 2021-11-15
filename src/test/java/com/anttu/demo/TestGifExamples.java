package com.anttu.demo;

import com.anttu.asciimg.ascii_charater_cache.AsciiImgCache;
import com.anttu.asciimg.character_fit_strategy.BestCharacterFitStrategy;
import com.anttu.asciimg.character_fit_strategy.StructuralSimilarityFitStrategy;
import com.anttu.asciimg.converter.GifToAsciiConvert;
import org.junit.Test;

import java.awt.*;

/**
 * 描述
 *
 * @ClassName：TestGifExamples
 * @Description：测试 gif
 * @author：Anttu
 * @Date：15/11/2021 14:11
 */
public class TestGifExamples {

    @Test
    public void testGifExample () {
        // initialize caches
        AsciiImgCache smallFontCache = AsciiImgCache.create(new Font("Courier", Font.BOLD, 6));
        // initialize ssimStrategy
        BestCharacterFitStrategy ssimStrategy = new StructuralSimilarityFitStrategy();

        String srcFilePath = "examples/test.gif";
        String disFilePath = "examples/test-ascii.gif";
        int delay = 100;//ms

        GifToAsciiConvert asciiConvert = new GifToAsciiConvert(smallFontCache, ssimStrategy);

        asciiConvert.convertGitToAscii(srcFilePath, disFilePath, delay,0);
    }
}
