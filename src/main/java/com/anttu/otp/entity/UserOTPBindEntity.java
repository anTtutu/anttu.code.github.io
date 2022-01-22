package com.anttu.otp.entity;

import java.util.Date;

import lombok.Data;

/**
 * 描述
 *
 * @ClassName：UserOTPBindEntity
 * @Description：用户 OTP 绑定实体
 * @author：Anttu
 * @Date：21/1/2022 16:20
 */
@Data
public class UserOTPBindEntity {
    private Long id;
    private Long userId;
    private String account;
    private String optSecret;
    private int isFlag;
    private int delFlag;
    private Date createTime;
    private Date updateTime;
}
