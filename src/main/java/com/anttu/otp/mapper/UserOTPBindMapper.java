package com.anttu.otp.mapper;

import com.anttu.otp.entity.UserOTPBindEntity;

/**
 * 描述
 *
 * @ClassName：UserOPTBindMapper
 * @Description：用户 OTP 绑定 mapper
 * @author：Anttu
 * @Date：21/1/2022 16:23
 */
public interface UserOTPBindMapper {
    int save (UserOTPBindEntity userOTPBindEntity);
    UserOTPBindEntity queryByUser (UserOTPBindEntity userOTPBindEntity);
    int updateStatus (UserOTPBindEntity userOTPBindEntity);
    int deleteByPrimaryKey (Long id);
}
