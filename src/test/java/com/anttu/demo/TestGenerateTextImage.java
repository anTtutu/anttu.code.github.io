package com.anttu.demo;

import com.anttu.asciimg.GenerateTextImage;
import org.junit.Test;

import java.awt.image.BufferedImage;

/**
 * 描述
 *
 * @ClassName：TestGenerateTextImage
 * @Description：
 * @author：Anttu
 * @Date：11/12/2021 11:19
 */
public class TestGenerateTextImage {
    @Test
    public void test () throws Exception {
        String filePath = "examples/star.png";
        BufferedImage bufferedImage = GenerateTextImage.makeSmallImage(filePath);
        GenerateTextImage.printImage(bufferedImage);
    }
}
