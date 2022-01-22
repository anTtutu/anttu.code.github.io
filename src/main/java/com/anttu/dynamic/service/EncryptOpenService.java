package com.anttu.dynamic.service;

/**
 * 描述
 *
 * @ClassName：EncryptOpenService
 * @Description：
 * @author：Anttu
 * @Date：27/10/2021 21:11
 */

import com.anttu.dynamic.request.KeyRequest;
import com.anttu.dynamic.response.KeyResponse;
import com.anttu.dynamic.response.RSAResponse;
import com.anttu.dynamic.util.SingleResult;

/**
 * API传输加解密相关接口
 * @author Anttu
 */
public interface EncryptOpenService {
    /**
     * 生成RSA公私钥
     * @return
     */
    SingleResult<RSAResponse> getRSA();

    /**
     * 获得加解密用的密钥
     * @param request
     * @return
     * @throws Exception
     */
    SingleResult<KeyResponse> getKey(KeyRequest request) throws Exception;
}
