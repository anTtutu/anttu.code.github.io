package com.anttu.otp.Dto;

import lombok.Data;

/**
 * 描述
 *
 * @ClassName：BindDto
 * @Description：绑定 Dto
 * @author：Anttu
 * @Date：21/1/2022 14:39
 */
@Data
public class BindDto {
    private String account;
    private String secretKey;
}
