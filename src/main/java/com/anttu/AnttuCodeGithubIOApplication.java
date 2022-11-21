package com.anttu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 描述
 *
 * @ClassName：AnttuCodeGithubIOApplication
 * @Description：springboot启动类
 * @author：Anttu
 * @Date：22/1/2022 15:20
 */
@MapperScan({"com.anttu.*.mapper"})
@SpringBootApplication(scanBasePackages = "com.anttu.*")
@EnableCaching
public class AnttuCodeGithubIOApplication {
    public static void main (String[] args) {
        SpringApplication.run(AnttuCodeGithubIOApplication.class, args);
    }
}
