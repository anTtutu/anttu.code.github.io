package com.anttu.dynamic.request;

import lombok.Data;

/**
 * 描述
 *
 * @ClassName：AppRequest
 * @Description：App 的请求参数
 * @author：Anttu
 * @Date：1/11/2021 20:21
 */
@Data
public class AppRequest {
    /**
     * 应用 id
     */
    private String appId;
    /**
     * 应用密钥
     */
    private String appSecret;
    /**
     * 应用名称
     */
    private String appName;
}
