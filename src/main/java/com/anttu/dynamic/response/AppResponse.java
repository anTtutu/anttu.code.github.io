package com.anttu.dynamic.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 描述
 *
 * @ClassName：AppResponse
 * @Description：App 应用返回值
 * @author：Anttu
 * @Date：2/11/2021 10:23
 */
@Data
public class AppResponse extends BaseResponse {
    /**
     * 返回码
     */
    private int Code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据，可能为空
     */
    private Object data;

    /**
     * 返回成功
     * @param object
     * @return
     */
    public static AppResponse success (Object object) {
        AppResponse appResponse = new AppResponse();
        appResponse.setCode(200);
        appResponse.setMsg("Success");
        appResponse.setData(object);
        return appResponse;
    }

    /**
     * 返回失败
     * @param code
     * @param msg
     * @param object
     * @return
     */
    public static AppResponse failed (int code, String msg, Object object) {
        AppResponse appResponse = new AppResponse();
        appResponse.setCode(code);
        appResponse.setMsg(msg);
        appResponse.setData(object);
        return appResponse;
    }
}
