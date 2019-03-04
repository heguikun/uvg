package com.uvgouapp.model.other;


/**
 * - @Author:  ying
 * - @Time:  2019/1/20
 * - @Description:  信息
 */
public class InfoBean {

    /**
     * statusCode : 200
     * data : ""
     * msg :
     */

    private int statusCode;
    private String data;
    private String msg;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
