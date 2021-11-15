package com.anttu.asciimg.character_fit_strategy;

import com.anttu.asciimg.matrix.GrayscaleMatrix;

/**
 * Calculates squared mean error between each pixel.
 *
 * @ClassName：ColorSquareErrorFitStrategy
 * @Description：
 * @author：Anttu
 * @Date：15/11/2021 10:17
 */
public class ColorSquareErrorFitStrategy implements BestCharacterFitStrategy {
    /**
     * @see com.anttu.asciimg.character_fit_strategy.BestCharacterFitStrategy#calculateError(com.anttu.asciimg.matrix.GrayscaleMatrix, com.anttu.asciimg.matrix.GrayscaleMatrix)
     */
    @Override
    public float calculateError(GrayscaleMatrix character, GrayscaleMatrix tile) {
        float error = 0;
        for (int i = 0; i < character.getData().length; i++) {
            error += (character.getData()[i] - tile.getData()[i])
                    * (character.getData()[i] - tile.getData()[i]);
        }

        return error / character.getData().length;

    }
}
