package com.anttu.otp.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.anttu.user.bean.UserVo;
import com.anttu.user.service.TestQuery;
import com.anttu.dynamic.response.AppResponse;
import com.anttu.otp.Dto.BindDto;
import com.anttu.otp.Dto.LoginDto;
import com.anttu.otp.entity.UserOTPBindEntity;
import com.anttu.otp.service.UserOTPBindService;
import com.anttu.otp.util.OTPUtil;

import cn.hutool.core.util.ObjectUtil;

/**
 * 描述
 *
 * @ClassName：LoginController
 * @Description：登录
 * @author：Anttu
 * @Date：21/1/2022 11:08
 */
@RestController
@RequestMapping("/otp/test")
public class LoginController {

    @Resource
    private TestQuery testQuery;

    @Resource
    private UserOTPBindService userOTPBindService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AppResponse login (@RequestBody LoginDto loginDto) {
        UserOTPBindEntity userOTPBindEntity = new UserOTPBindEntity();
        userOTPBindEntity.setAccount(loginDto.getAccount());
        UserOTPBindEntity userInfo = userOTPBindService.queryByUser(userOTPBindEntity);
        if (ObjectUtil.isNotNull(userInfo)) {
            if (OTPUtil.verify(userInfo.getOptSecret(), loginDto.getOtpPassword())) {
                return AppResponse.success("");
            } else {
                return AppResponse.failed(401, "otp check is not right.", "");
            }
        } else {
            return AppResponse.failed(404, "not bind OTP.", loginDto.getAccount());
        }
    }

    @RequestMapping(value = "/bind", method = RequestMethod.POST)
    public AppResponse bindOTP (@RequestBody BindDto bindDto) {

        UserVo userVo = testQuery.selectByAccount(bindDto.getAccount());
        if (ObjectUtil.isNotNull(userVo)) {
            UserOTPBindEntity userOTPBindEntity = new UserOTPBindEntity();
            userOTPBindEntity.setAccount(bindDto.getAccount());
            String otpSecret = OTPUtil.getRandomSecretBase32(64);
            userOTPBindEntity.setOptSecret(otpSecret);
            int result = userOTPBindService.save(userOTPBindEntity);
            if (result > 0) {
                return AppResponse.success("");
            } else {
                return AppResponse.failed(901, "bind failed.", "");
            }
        } else {
            return AppResponse.failed(404, "account is not found.", bindDto.getAccount());
        }

    }

    @RequestMapping(value = "/queryOTP/{account}", method = RequestMethod.GET)
    public AppResponse queryOTPPwd (@PathVariable("account") String account) {
        UserOTPBindEntity userOTPBindEntity = new UserOTPBindEntity();
        userOTPBindEntity.setAccount(account);
        UserOTPBindEntity userInfo = userOTPBindService.queryByUser(userOTPBindEntity);

        if (ObjectUtil.isNotNull(userInfo)) {
            String otpPwd = OTPUtil.generate(userInfo.getOptSecret());
            return AppResponse.success(otpPwd);
        } else {
            return AppResponse.failed(404, "account is not exist.", account);
        }
    }
}
