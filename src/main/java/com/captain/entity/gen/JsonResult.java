package com.captain.entity.gen;


import com.google.gson.Gson;

/**
 * Created by cp on 2017/7/31.
 */
public class JsonResult<T> {
    private int code; //所有返回码都归结到此字段，data部分不再设置业务返回码result ，
    // 此字段定义：100以内的错误码为内部使用业务码  1 成功  -1 模块位找到 -2 接口未找到  -3 未知错误 其他为标准的http返回码
    private String msg;
    private T data;


    public JsonResult() {

    }

    public JsonResult(int code, String msg) {
        this(code, msg, null);
    }

    public JsonResult(T data) {
        this(1, "ok", data);
    }

    public JsonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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

    public String toString() {
        return new Gson().toJson(this);
    }

}
