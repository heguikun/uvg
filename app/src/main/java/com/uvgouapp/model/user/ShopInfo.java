package com.uvgouapp.model.user;


/**
 * - @Author:  ying
 * - @Time:  2019/1/29
 * - @Description:  店铺信息
 */
public class ShopInfo {

    /**
     * statusCode : 200
     * data : {"id":3,"shopName":"来啦老弟","address":null,"regionsId":null,"userId":"905","userName":"","businessLicence":null,"shopType":null,"businessHours":null,"shopCode":null,"companyName":null,"mainOperation":"卖身不卖艺","synopsis":null,"mobilePhone":"18054223782","idCard":"445222199401250331","createTime":1548038308000,"modifyTime":1548038308000,"createBy":null,"modifyBy":null,"isDeleted":false,"smsCode":null}
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
         * id : 3
         * shopName : 来啦老弟
         * address : null
         * regionsId : null
         * userId : 905
         * userName :
         * businessLicence : null
         * shopType : null
         * businessHours : null
         * shopCode : null
         * companyName : null
         * mainOperation : 卖身不卖艺
         * synopsis : null
         * mobilePhone : 18054223782
         * idCard : 445222199401250331
         * createTime : 1548038308000
         * modifyTime : 1548038308000
         * createBy : null
         * modifyBy : null
         * isDeleted : false
         * smsCode : null
         */

        private int id;
        private String shopName;
        private Object address;
        private Object regionsId;
        private String userId;
        private String userName;
        private Object businessLicence;
        private Object shopType;
        private Object businessHours;
        private Object shopCode;
        private Object companyName;
        private String mainOperation;
        private Object synopsis;
        private String mobilePhone;
        private String idCard;
        private long createTime;
        private long modifyTime;
        private Object createBy;
        private Object modifyBy;
        private boolean isDeleted;
        private Object smsCode;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getRegionsId() {
            return regionsId;
        }

        public void setRegionsId(Object regionsId) {
            this.regionsId = regionsId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getBusinessLicence() {
            return businessLicence;
        }

        public void setBusinessLicence(Object businessLicence) {
            this.businessLicence = businessLicence;
        }

        public Object getShopType() {
            return shopType;
        }

        public void setShopType(Object shopType) {
            this.shopType = shopType;
        }

        public Object getBusinessHours() {
            return businessHours;
        }

        public void setBusinessHours(Object businessHours) {
            this.businessHours = businessHours;
        }

        public Object getShopCode() {
            return shopCode;
        }

        public void setShopCode(Object shopCode) {
            this.shopCode = shopCode;
        }

        public Object getCompanyName() {
            return companyName;
        }

        public void setCompanyName(Object companyName) {
            this.companyName = companyName;
        }

        public String getMainOperation() {
            return mainOperation;
        }

        public void setMainOperation(String mainOperation) {
            this.mainOperation = mainOperation;
        }

        public Object getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(Object synopsis) {
            this.synopsis = synopsis;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(Object modifyBy) {
            this.modifyBy = modifyBy;
        }

        public boolean isIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
        }

        public Object getSmsCode() {
            return smsCode;
        }

        public void setSmsCode(Object smsCode) {
            this.smsCode = smsCode;
        }
    }
}
