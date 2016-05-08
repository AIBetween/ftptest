package com.gyz.pattern.chainofresponsbility;

/**
 * 封装了对处理器的请求参数。
 * Created by CodeMonkey on 2016/5/3.
 */
public class Request {

    private String requestMessage;
    private int level;

    public Request(String requestMessage, int level) {
        this.requestMessage = requestMessage;
        this.level = level;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
