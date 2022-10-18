package com.anttu.demo;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;
import org.junit.Test;

/**
 * 描述
 *
 * @ClassName：TestULID
 * @Description：测试ULID
 * @author：Anttu
 * @Date：18/10/2022 16:27
 */
public class TestULID {

    @Test
    public void generateULID () {
        for (int i = 0; i < 10; i++) {
            Ulid ulid = UlidCreator.getUlid();
            System.out.println("ULID : " + ulid);
        }
    }

    @Test
    public void generateMonotonicULID () {
        for (int i = 0; i < 10; i++) {
            Ulid ulid = UlidCreator.getMonotonicUlid();
            System.out.println("Monotonic ULID : " + ulid);
        }
    }
}
