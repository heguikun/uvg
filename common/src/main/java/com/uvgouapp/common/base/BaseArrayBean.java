package com.uvgouapp.common.base;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2018/12/22
 * - @Description:  解析数组基类
 */
public class BaseArrayBean<T> {

    private int statusCode;
    private String msg;
    private List<T> result;

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

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
