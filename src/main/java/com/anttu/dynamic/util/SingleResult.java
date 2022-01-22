package com.anttu.dynamic.util;

import java.io.Serializable;

import com.anttu.dynamic.response.BaseResponse;

/**
 * 描述
 *
 * @ClassName：SingleResult
 * @Description：
 * @author：Anttu
 * @Date：27/10/2021 21:19
 */
public class SingleResult<T> implements Serializable {
    /**
     * 返回处理code，包括模块代码以及功能代码等信息，默认为0。
     */
    private int code = 0;

    /**
     * 返回处理状态说明，描述。
     */
    private String message;

    /**
     * 返回的业务数据。
     */
    private T data;

    /**
     *
     * @return 返回代码。
     */
    public int getCode() {
        return code;
    }

    /**
     *
     * @param code 返回代码。
     */
    public SingleResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    /**
     *
     * @return 返回信息。
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message 返回信息。
     */
    public SingleResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     *
     * @return 返回数据。
     */
    public T getData() {
        return data;
    }

    /**
     *
     * @param data 返回数据。
     */
    public SingleResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 成功
     * @param baseResponse
     * @param <T>
     * @return
     */
    public static <T> SingleResult<T> buildSuccess (BaseResponse baseResponse) {
        SingleResult singleResult = new SingleResult();
        singleResult.setCode(200);
        singleResult.setMessage("Success");
        singleResult.setData(baseResponse);
        return singleResult;
    }

    /**
     * 失败
     * @param baseResponse
     * @param <T>
     * @return
     */
    public static <T> SingleResult<T> buildFailed (BaseResponse baseResponse) {
        SingleResult singleResult = new SingleResult();
        singleResult.setCode(500);
        singleResult.setMessage("Error");
        singleResult.setData(baseResponse);
        return singleResult;
    }
}
