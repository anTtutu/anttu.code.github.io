package com.anttu.otp.controller;

import com.anttu.otp.util.OTPUtil;

/**
 * 描述
 *
 * @ClassName：BindOTPController
 * @Description：绑定
 * @author：Anttu
 * @Date：21/1/2022 10:59
 */
public class TestMain {
    public static void main (String[] args) {
        String userName = "test";
        String email = "609061217@qq.com";
        String host = "otptest@wjs.com";

        // 创建OTP信息
        String secretBase32 = OTPUtil.getRandomSecretBase32(64);
        System.out.println("OTP密钥=" + secretBase32);

        String OTPUrl = OTPUtil.generateTotpString(userName, host, secretBase32);
        System.out.println("OTP URL=" + OTPUrl);

        String OTPPwd = OTPUtil.generate(secretBase32);
        System.out.println("OTP PWD=" + OTPPwd);
    }
}
