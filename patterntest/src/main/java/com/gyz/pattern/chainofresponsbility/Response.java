package com.gyz.pattern.chainofresponsbility;

/**
 * 封装了 处理器 对请求的处理结果。
 * Created by CodeMonkey on 2016/5/3.
 */
public class Response {

    private String responseMessage;

    public Response(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
