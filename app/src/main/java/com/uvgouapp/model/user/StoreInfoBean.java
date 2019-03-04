package com.uvgouapp.model.user;

/**
 * - @Author:  ying
 * - @Time:  2019/1/19
 * - @Description:  店铺
 */
public class StoreInfoBean {

    /**
     * statusCode : 200
     * data : {"id":5,"shopName":"ying店铺","address":null,"regionsId":null,"userId":"904","userName":"ying","businessLicence":null,"shopType":null,"businessHours":null,"shopCode":null,"companyName":null,"mainOperation":"衣服","synopsis":null,"mobilePhone":"15070808785","idCard":"360121199407221957","createTime":1548024534000,"modifyTime":1548024534000,"createBy":"904","modifyBy":"904","isDeleted":false,"smsCode":null}
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
         * id : 5
         * shopName : ying店铺
         * address : null
         * regionsId : null
         * userId : 904
         * userName : ying
         * businessLicence : null
         * shopType : null
         * businessHours : null
         * shopCode : null
         * companyName : null
         * mainOperation : 衣服
         * synopsis : null
         * mobilePhone : 15070808785
         * idCard : 360121199407221957
         * createTime : 1548024534000
         * modifyTime : 1548024534000
         * createBy : 904
         * modifyBy : 904
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
        private String createBy;
        private String modifyBy;
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

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(String modifyBy) {
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
