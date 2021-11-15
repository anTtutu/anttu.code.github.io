package com.anttu.asciimg.converter;

import cn.hutool.core.img.gif.AnimatedGifEncoder;
import com.anttu.asciimg.ascii_charater_cache.AsciiImgCache;
import com.anttu.asciimg.character_fit_strategy.BestCharacterFitStrategy;
import com.anttu.asciimg.utils.GifDecoder;

/**
 * 描述
 *
 * @ClassName：GifToAsciiConvert
 * @Description：
 * @author：Anttu
 * @Date：15/11/2021 10:52
 */
public class GifToAsciiConvert extends AsciiToImageConverter {

    public GifToAsciiConvert(AsciiImgCache characterCacher,
                             BestCharacterFitStrategy characterFitStrategy) {
        super(characterCacher, characterFitStrategy);
    }

    /**
     *
     * @param srcFilePath
     * @param disFilePath
     * @param delay－－the delay time(ms) between each frame
     * @param repeat－－he number of times the set of GIF frames should be played.0 means play indefinitely.
     * @return
     */
    public int convertGitToAscii(String srcFilePath,String disFilePath,int delay,int repeat){
        GifDecoder decoder = new GifDecoder();
        int status = decoder.read(srcFilePath);
        if(status!=0){
            return -1;//srcfile not exist or open failed!
        }
        AnimatedGifEncoder e = new AnimatedGifEncoder();
        boolean openStatus = e.start(disFilePath);
        if(openStatus){
            e.setDelay(delay);   // 1 frame per delay(ms)
            e.setRepeat(repeat);
            // initialize converters
            int frameCount = decoder.getFrameCount();
            for(int i=0;i<frameCount;i++){
                //convert per frame
                e.addFrame(this.convertImage(decoder.getFrame(i)));
            }
            e.finish();
            return 1;//done!
        }
        return 0;//open disfile failed!
    };
}
