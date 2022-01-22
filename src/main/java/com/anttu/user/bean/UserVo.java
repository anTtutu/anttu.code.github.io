package com.anttu.user.bean;

import lombok.Data;

/**
 * 描述
 *
 * @ClassName：UserVo
 * @Description：用户Vo
 * @author：Anttu
 * @Date：20/8/2021 10:07
 */
@Data
public class UserVo {
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
}
