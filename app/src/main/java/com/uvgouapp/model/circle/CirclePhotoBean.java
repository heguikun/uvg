package com.uvgouapp.model.circle;


/**
 * - @Author:  ying
 * - @Time:  2019/1/17
 * - @Description:  图片
 */
public class CirclePhotoBean {

    /**
     * statusCode : 200
     * data : https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1547732188749879.jpg
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
