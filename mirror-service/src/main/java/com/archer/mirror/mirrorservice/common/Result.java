package com.archer.mirror.mirrorservice.common;

public class Result<T> {

    private static final Result<Void> OK = success();

    private int code = 0;

    private String msg;

    private T data;

    public static <T> Result<T> success() {
        return new Result<>();
    }
    public static <T> Result<T> success(T data) {
        Result<T> result = success();
        result.setData(data);
        return result;
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
