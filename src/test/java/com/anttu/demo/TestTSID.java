package com.anttu.demo;

import com.github.f4b6a3.tsid.TsidCreator;
import org.junit.Test;

/**
 * 描述
 *
 * @ClassName：TestTSID
 * @Description：测试 TSID
 * @author：Anttu
 * @Date：18/10/2022 16:28
 */
public class TestTSID {

    @Test
    public void generateLongTSID () {
        for (int i = 0; i < 10; i++) {
           Long tsid = TsidCreator.getTsid256().toLong();
           System.out.println("Long TSID:" + tsid);
        }
    }

    @Test
    public void generateStringTSID () {
        for (int i = 0; i < 10; i++) {
            String tsid = TsidCreator.getTsid256().toString();
            System.out.println("String TSID:" + tsid);
        }
    }
}
