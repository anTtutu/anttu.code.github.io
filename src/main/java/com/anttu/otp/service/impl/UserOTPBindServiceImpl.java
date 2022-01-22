package com.anttu.otp.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.anttu.otp.entity.UserOTPBindEntity;
import com.anttu.otp.mapper.UserOTPBindMapper;
import com.anttu.otp.service.UserOTPBindService;

/**
 * 描述
 *
 * @ClassName：UserOTPBindServiceImpl
 * @Description：
 * @author：Anttu
 * @Date：21/1/2022 17:12
 */
@Service
public class UserOTPBindServiceImpl implements UserOTPBindService {

    @Resource
    private UserOTPBindMapper userOTPBindMapper;

    @Override
    public int save(UserOTPBindEntity userOTPBindEntity) {
        return userOTPBindMapper.save(userOTPBindEntity);
    }

    @Override
    public UserOTPBindEntity queryByUser(UserOTPBindEntity userOTPBindEntity) {
        return userOTPBindMapper.queryByUser(userOTPBindEntity);
    }

    @Override
    public int updateStatus(UserOTPBindEntity userOTPBindEntity) {
        return userOTPBindMapper.updateStatus(userOTPBindEntity);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userOTPBindMapper.deleteByPrimaryKey(id);
    }
}
