package com.uvgouapp.model.user;

import com.google.gson.annotations.SerializedName;

public class WxPayBean {

    /**
     * statusCode : 200
     * data : {"appid":"wx69af5446bd66d789","noncestr":"3ef815416f775098fe977004015c6193","package":"Sign=WXPay","partnerid":"1511744041","prepayid":null,"sign":"A125DC010747CCC7C29FCA283861B848","timestamp":"1550482328"}
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
         * appid : wx69af5446bd66d789
         * noncestr : 3ef815416f775098fe977004015c6193
         * outTradeNo :1550654023630816754
         * package : Sign=WXPay
         * partnerid : 1511744041
         * prepayid : null
         * sign : A125DC010747CCC7C29FCA283861B848
         * timestamp : 1550482328
         */

        private String appid;
        private String noncestr;
        private String outTradeNo;
        @SerializedName("package")
        private String packageValue;
        private String partnerid;
        private String prepayid;
        private String sign;
        private String timestamp;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getpackageValue() {
            return packageValue;
        }

        public void setpackageValue(String packageValue) {
            this.packageValue = packageValue;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }
    }
}
