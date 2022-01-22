package com.anttu.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述
 *
 * @ClassName：User
 * @Description：用户测试
 * @author：Anttu
 * @Date：20/8/2021 10:04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * id
     */
    private int id;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 名称
     */
    private String name;
    /**
     * 性别 0 女 1 男
     */
    private int sex;
    /**
     * 公司
     */
    private String company;

    private Date createTime;
    private Date updateTime;
}
