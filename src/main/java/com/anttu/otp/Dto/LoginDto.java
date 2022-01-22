package com.anttu.otp.Dto;

import lombok.Data;

/**
 * 描述
 *
 * @ClassName：LoginDto
 * @Description：登录 Dto
 * @author：Anttu
 * @Date：21/1/2022 11:12
 */
@Data
public class LoginDto {
    private String account;
    private String password;
    private String otpPassword;
    private String otpSecKey;
}
