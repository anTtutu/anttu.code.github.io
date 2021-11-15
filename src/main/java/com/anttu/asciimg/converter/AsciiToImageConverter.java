package com.anttu.asciimg.converter;

import com.anttu.asciimg.ascii_charater_cache.AsciiImgCache;
import com.anttu.asciimg.character_fit_strategy.BestCharacterFitStrategy;
import com.anttu.asciimg.matrix.GrayscaleMatrix;
import com.anttu.asciimg.utils.ArrayUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map.Entry;

/**
 * Converts ascii art to a BufferedImage.
 *
 * @ClassName：AsciiToImageConverter
 * @Description：
 * @author：Anttu
 * @Date：15/11/2021 10:47
 */
public class AsciiToImageConverter extends AsciiConverter<BufferedImage> {
    /**
     * Instantiates a new ascii to image converter.
     *
     * @param characterCacher
     *            the character cacher
     * @param characterFitStrategy
     *            the character fit strategy
     */
    public AsciiToImageConverter(final AsciiImgCache characterCacher, final BestCharacterFitStrategy characterFitStrategy) {
        super(characterCacher, characterFitStrategy);
    }

    /**
     * Copy image data over the source pixels image.
     *
     * @see com.anttu.asciimg.converter.AsciiConverter#addCharacterToOutput(java.util.Map.Entry,
     *      int[], int, int, int)
     */
    @Override
    public void addCharacterToOutput(
            final Entry<Character, GrayscaleMatrix> characterEntry,
            final int[] sourceImagePixels, final int tileX, final int tileY, final int imageWidth) {
        int startCoordinateX = tileX
                * this.characterCache.getCharacterImageSize().width;
        int startCoordinateY = tileY
                * this.characterCache.getCharacterImageSize().height;

        // copy winner character
        for (int i = 0; i < characterEntry.getValue().getData().length; i++) {
            int xOffset = i % this.characterCache.getCharacterImageSize().width;
            int yOffset = i / this.characterCache.getCharacterImageSize().width;

            int component = (int) characterEntry.getValue().getData()[i];
            sourceImagePixels[ArrayUtils.convert2DTo1D(startCoordinateX
                    + xOffset, startCoordinateY + yOffset, imageWidth)] = new Color(
                    component, component, component).getRGB();
        }

    }

    /**
     * Write pixels to output image.
     *
     * @see com.anttu.asciimg.converter.AsciiConverter#finalizeOutput(int[],
     *      int, int)
     */
    @Override
    protected void finalizeOutput(final int[] sourceImagePixels, final int imageWidth,
                                  final int imageHeight) {
        this.output.setRGB(0, 0, imageWidth, imageHeight, sourceImagePixels, 0,
                imageWidth);

    }

    /**
     * Create an empty buffered image.
     *
     * @see com.anttu.asciimg.converter.AsciiConverter#initializeOutput(int,
     *      int)
     */
    @Override
    protected BufferedImage initializeOutput(final int imageWidth, final int imageHeight) {
        return new BufferedImage(imageWidth, imageHeight,
                BufferedImage.TYPE_INT_ARGB);
    }
}
