package com.uvgouapp.model.other;


/**
 - @Author:  ying
 - @Time:  2019/2/21
 - @Description:  版本号
 */
public class VersionBean {

    /**
     * statusCode : 200
     * data : {"id":2,"versionCode":11,"url":"https://a.app.qq.com/o/simple.jsp?pkgname=com.uvgouapp","type":2,"createTime":1550740935000}
     * msg :
     */

    private int statusCode;
    private DataBean data;
    private String msg;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * id : 2
         * versionCode : 11
         * url : https://a.app.qq.com/o/simple.jsp?pkgname=com.uvgouapp
         * type : 2
         * createTime : 1550740935000
         */

        private int id;
        private int versionCode;
        private String url;
        private int type;
        private long createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
