package com.uvgouapp.model.user;

/**
 * - @Author:  ying
 * - @Time:  2019/1/10
 * - @Description:  用户
 */
public class User {

    /**
     * statusCode : 200
     * data : {"id":966,"name":"秦王～ying","groupid":null,"level":null,"opendId":"oyXKv0SMIiisaz1jtqc4p0vN3ohQ","realName":null,"headImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJOBEgLpicaeLTrB9wWicLxvyg9ias6GyiauqPDjqJocpBpXwD3JCAaSzXostDTHXJFuqS6XOF6yXnE1w/132","settingImg":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550604807268207.jpg","sex":true,"district":"中国,北京,朝阳","autograph":null,"qrCode":null,"mobile":"15070808785","pwd":null,"content":null,"status":true,"isDistribution":null,"byname":null,"integral":null,"isCancel":null,"createTime":1550589540000,"modifyTime":1550831477000,"createBy":null,"modifyBy":"966","isDeleted":false,"longitude":null,"latitude":null,"positionName":null,"rank":1550653079000,"onLineTime":1550802677000,"totalCommission":0,"o2":2}
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
         * id : 966
         * name : 秦王～ying
         * groupid : null
         * level : null
         * opendId : oyXKv0SMIiisaz1jtqc4p0vN3ohQ
         * realName : null
         * headImg : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJOBEgLpicaeLTrB9wWicLxvyg9ias6GyiauqPDjqJocpBpXwD3JCAaSzXostDTHXJFuqS6XOF6yXnE1w/132
         * settingImg : https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550604807268207.jpg
         * sex : true
         * district : 中国,北京,朝阳
         * autograph : null
         * qrCode : null
         * mobile : 15070808785
         * pwd : null
         * content : null
         * status : true
         * isDistribution : null
         * byname : null
         * integral : null
         * isCancel : null
         * createTime : 1550589540000
         * modifyTime : 1550831477000
         * createBy : null
         * modifyBy : 966
         * isDeleted : false
         * longitude : null
         * latitude : null
         * positionName : null
         * rank : 1550653079000
         * onLineTime : 1550802677000
         * totalCommission : 0
         * o2 : 2
         */

        private int id;
        private String name;
        private Object groupid;
        private Object level;
        private String opendId;
        private Object realName;
        private String headImg;
        private String settingImg;
        private boolean sex;
        private String district;
        private Object autograph;
        private Object qrCode;
        private String mobile;
        private Object pwd;
        private Object content;
        private boolean status;
        private Object isDistribution;
        private Object byname;
        private Object integral;
        private Object isCancel;
        private long createTime;
        private long modifyTime;
        private Object createBy;
        private String modifyBy;
        private boolean isDeleted;
        private Object longitude;
        private Object latitude;
        private Object positionName;
        private long rank;
        private long onLineTime;
        private int totalCommission;
        private double o2;
        private String obode;
        private String constellation;
        private String occupation;
        private String stature;
        private String weight;
        private String emotionalState;
        private String dateOfBirth;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getGroupid() {
            return groupid;
        }

        public void setGroupid(Object groupid) {
            this.groupid = groupid;
        }

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
            this.level = level;
        }

        public String getOpendId() {
            return opendId;
        }

        public void setOpendId(String opendId) {
            this.opendId = opendId;
        }

        public Object getRealName() {
            return realName;
        }

        public void setRealName(Object realName) {
            this.realName = realName;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getSettingImg() {
            return settingImg;
        }

        public void setSettingImg(String settingImg) {
            this.settingImg = settingImg;
        }

        public boolean isSex() {
            return sex;
        }

        public void setSex(boolean sex) {
            this.sex = sex;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public Object getAutograph() {
            return autograph;
        }

        public void setAutograph(Object autograph) {
            this.autograph = autograph;
        }

        public Object getQrCode() {
            return qrCode;
        }

        public void setQrCode(Object qrCode) {
            this.qrCode = qrCode;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getPwd() {
            return pwd;
        }

        public void setPwd(Object pwd) {
            this.pwd = pwd;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public Object getIsDistribution() {
            return isDistribution;
        }

        public void setIsDistribution(Object isDistribution) {
            this.isDistribution = isDistribution;
        }

        public Object getByname() {
            return byname;
        }

        public void setByname(Object byname) {
            this.byname = byname;
        }

        public Object getIntegral() {
            return integral;
        }

        public void setIntegral(Object integral) {
            this.integral = integral;
        }

        public Object getIsCancel() {
            return isCancel;
        }

        public void setIsCancel(Object isCancel) {
            this.isCancel = isCancel;
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

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getPositionName() {
            return positionName;
        }

        public void setPositionName(Object positionName) {
            this.positionName = positionName;
        }

        public long getRank() {
            return rank;
        }

        public void setRank(long rank) {
            this.rank = rank;
        }

        public long getOnLineTime() {
            return onLineTime;
        }

        public void setOnLineTime(long onLineTime) {
            this.onLineTime = onLineTime;
        }

        public int getTotalCommission() {
            return totalCommission;
        }

        public void setTotalCommission(int totalCommission) {
            this.totalCommission = totalCommission;
        }

        public double getO2() {
            return o2;
        }

        public void setO2(double o2) {
            this.o2 = o2;
        }

        public String getObode() {
            return obode;
        }

        public void setObode(String obode) {
            this.obode = obode;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public String getStature() {
            return stature;
        }

        public void setStature(String stature) {
            this.stature = stature;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getEmotionalState() {
            return emotionalState;
        }

        public void setEmotionalState(String emotionalState) {
            this.emotionalState = emotionalState;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }
    }
}
