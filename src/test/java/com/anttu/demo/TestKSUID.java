package com.anttu.demo;

import com.github.f4b6a3.ksuid.KsuidCreator;
import org.junit.Test;

/**
 * 描述
 *
 * @ClassName：TestKSUID
 * @Description：测试 KSUID
 * @author：Anttu
 * @Date：18/10/2022 16:28
 */
public class TestKSUID {

    @Test
    public void generateKSUID () {
        for (int i = 0; i < 10; i++) {
            String ksuid = KsuidCreator.getKsuid().toString();
            System.out.println("KSUID:" + ksuid);
        }
    }

    @Test
    public void generateSubSecondKSUID () {
        for (int i = 0; i < 10; i++) {
            String ksuid = KsuidCreator.getSubsecondKsuid().toString();
            System.out.println("SubSecond KSUID:" + ksuid);
        }
    }

    @Test
    public void generateMonotonicKSUID () {
        for (int i = 0; i < 10; i++) {
            String ksuid = KsuidCreator.getMonotonicKsuid().toString();
            System.out.println("Monotonic KSUID:" + ksuid);
        }
    }
}
