package com.anttu.dynamic.response;

/**
 * 私钥输出参数
 *
 * @ClassName：KeyResponse
 * @Description：
 * @author：Anttu
 * @Date：27/10/2021 21:11
 */
public class KeyResponse extends BaseResponse {
    /**
     * 整个系统所有加密算法共用的密钥
     */
    private String key;

    public static class Builder{
        private String key;

        public Builder setKey(String key){
            this.key = key;
            return this;
        }

        public KeyResponse build(){
            return new KeyResponse(this);
        }
    }

    public static Builder options(){
        return new Builder();
    }

    private KeyResponse(Builder builder){
        this.key = builder.key;
    }

    public String getKey() {
        return key;
    }
}
