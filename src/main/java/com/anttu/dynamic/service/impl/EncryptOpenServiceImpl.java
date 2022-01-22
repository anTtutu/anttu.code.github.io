package com.anttu.dynamic.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.anttu.dynamic.request.KeyRequest;
import com.anttu.dynamic.response.KeyResponse;
import com.anttu.dynamic.response.RSAResponse;
import com.anttu.dynamic.service.EncryptOpenService;
import com.anttu.dynamic.util.RSAUtils;
import com.anttu.dynamic.util.SingleResult;

/**
 * 描述
 *
 * @ClassName：EncryptOpenServiceImpl
 * @Description：
 * @author：Anttu
 * @Date：27/10/2021 21:17
 */
@Service
public class EncryptOpenServiceImpl implements EncryptOpenService {

    @Value("${rsa.publicKey}")
    private String publicKey;
    @Value("${rsa.privateKey}")
    private String privateKey;
    @Value("${api.encrypt.key}")
    private String key;

    @Override
    public SingleResult<RSAResponse> getRSA() {
        RSAResponse response = RSAResponse.options()
                .setServerPublicKey(publicKey)
                .build();
        return SingleResult.buildSuccess(response);
    }

    @Override
    public SingleResult<KeyResponse> getKey(KeyRequest request)throws Exception {
        String clientPublicKey = RSAUtils.privateDecrypt(request.getClientEncryptPublicKey(), RSAUtils.getPrivateKey(privateKey));
        String encryptKey = RSAUtils.publicEncrypt(key, RSAUtils.getPublicKey(clientPublicKey));
        KeyResponse response = KeyResponse.options()
                .setKey(encryptKey)
                .build();
        return SingleResult.buildSuccess(response);
    }
}
