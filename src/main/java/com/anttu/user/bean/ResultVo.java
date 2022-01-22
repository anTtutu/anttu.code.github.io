package com.anttu.user.bean;

import lombok.Data;

/**
 * 描述
 *
 * @ClassName：ResultVo
 * @Description：返回值
 * @author：Anttu
 * @Date：20/8/2021 09:58
 */
@Data
public class ResultVo {
    /**
     * 返回值编码
     */
    private String code;
    /**
     * true 成功，false  失败
     */
    private Boolean success;
    /**
     * 返回内容
     */
    private Result result;
    /**
     * 返回描述
     */
    private String message;

    @Data
    class Result {
        String token;
    }
}
