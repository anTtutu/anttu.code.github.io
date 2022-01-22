package com.anttu.otp.service;

import com.anttu.otp.entity.UserOTPBindEntity;

/**
 * 描述
 *
 * @ClassName：UserOTPBindService
 * @Description：
 * @author：Anttu
 * @Date：21/1/2022 17:08
 */
public interface UserOTPBindService {
    int save (UserOTPBindEntity userOTPBindEntity);
    UserOTPBindEntity queryByUser (UserOTPBindEntity userOTPBindEntity);
    int updateStatus (UserOTPBindEntity userOTPBindEntity);
    int deleteByPrimaryKey (Long id);
}
