package com.archer.mirror.mirrorservice.common;

public class Response<T> {

    private static final Response<Void> OK = success();

    private int code = 0;

    private String msg;

    private T data;

    public static <T> Response<T> success() {
        return new Response<>();
    }
    public static <T> Response<T> success(T data) {
        Response<T> response = success();
        response.setData(data);
        return response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
