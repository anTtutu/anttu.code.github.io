package com.anttu.dynamic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anttu.dynamic.request.KeyRequest;
import com.anttu.dynamic.response.KeyResponse;
import com.anttu.dynamic.response.RSAResponse;
import com.anttu.dynamic.service.EncryptOpenService;
import com.anttu.dynamic.util.SingleResult;

/**
 * 描述
 *
 * @ClassName：EncryptController
 * @Description：
 * @author：Anttu
 * @Date：27/10/2021 21:09
 */
@RestController
@RequestMapping("open/encrypt")
public class EncryptController {
    @Autowired
    private EncryptOpenService encryptOpenService;

    @RequestMapping(value = "getRSA",method = RequestMethod.POST)
    public SingleResult<RSAResponse> getRSA(){
        return encryptOpenService.getRSA();
    }

    @RequestMapping(value = "getKey",method = RequestMethod.POST)
    public SingleResult<KeyResponse> getKey(@Valid @RequestBody KeyRequest request)throws Exception{
        return encryptOpenService.getKey(request);
    }

//    @RequestMapping("/getAccessToken")
//    public String getAccessToken() {
//        return "";
//    }
}
