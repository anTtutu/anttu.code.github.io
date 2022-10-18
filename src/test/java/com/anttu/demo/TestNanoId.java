package com.anttu.demo;

import com.anttu.utils.NanoIdUtils;
import org.junit.Test;

/**
 * 描述
 *
 * @ClassName：TestNanoId
 * @Description：测试 nanoid
 * @author：Anttu
 * @Date：17/10/2022 10:49
 */
public class TestNanoId {

    @Test
    public void generateNanoId () {
        for (int i = 0; i < 10; i++) {
            System.out.println("NanoId::" + NanoIdUtils.randomNanoId());
        }
    }
}
