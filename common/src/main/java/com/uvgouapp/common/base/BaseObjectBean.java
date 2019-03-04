package com.uvgouapp.common.base;

/**
 * - @Author:  ying
 * - @Time:  2018/12/22
 * - @Description:  解析对象基类
 */
public class BaseObjectBean<T> {

    private int statusCode;
    private String msg;
    private T result;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
