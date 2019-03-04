package com.uvgouapp.model.show;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/1/11
 * - @Description:  在线
 */
public class ShowOnlineBean {

    /**
     * data : null
     * objectData : null
     * mapList : [{"latitude":"22.519032","dist":"103.6","autograph":null,"content":null,"settingImg":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/90320190125140433.png","positionName":"深圳市南山区海德三道3001号深圳湾木棉花酒店","modifyTime":1550566039000,"onLineTime":1550537239000,"qrCode":null,"isDeleted":false,"integral":null,"rank":1550506567000,"id":903,"longitude":"113.946753","opendId":"oyXKv0d5LgM4xdLCrw_RoILr1_N8","modifyBy":"903","isCancel":null,"headImg":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/90320190128175925.png","level":0,"groupid":null,"sex":true,"mobile":null,"isDistribution":null,"realName":null,"createBy":null,"byname":"朱礼裕","createTime":1547672966000,"district":"深圳市南山区海德三道3001号深圳湾木棉花酒店","name":"朱礼裕","pwd":null,"status":null},{"latitude":"23.18442","dist":"0.2","autograph":null,"content":null,"settingImg":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/mmexport1549341222913.jpg","positionName":null,"modifyTime":1550573955000,"onLineTime":1550545155000,"qrCode":null,"isDeleted":false,"integral":null,"rank":1550505031000,"id":933,"longitude":"113.237382","opendId":"oyXKv0UM8PH_YZlLf5PhIjczcI5c","modifyBy":"933","isCancel":null,"headImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/QCmef1sLh4HnFibWnvJHsSTnkibiabf8m89mAqNAXN6lfajNS3mrmBmqmZ23mKhUndFZ6tmOPDExicqBFO2cLkeI7A/132","level":13,"groupid":null,"sex":true,"mobile":null,"isDistribution":null,"realName":null,"createBy":null,"byname":null,"createTime":1548228166000,"district":"中国,广东,广州","name":"sky诺言","pwd":null,"status":null},{"latitude":"23.183704","dist":"0.1","autograph":null,"content":null,"settingImg":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/90220190218133816.png","positionName":"广州市白云区石槎路33号宾利国际大厦","modifyTime":1550573070000,"onLineTime":1550544270000,"qrCode":null,"isDeleted":false,"integral":null,"rank":1550480234000,"id":902,"longitude":"113.238046","opendId":"oyXKv0X3t3C0UlKT3areIzEcUN3U","modifyBy":"902","isCancel":null,"headImg":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/90220190218132701.png","level":0,"groupid":null,"sex":false,"mobile":null,"isDistribution":null,"realName":null,"createBy":null,"byname":"UWE购商学院讲师","createTime":1547661174000,"district":"广州市白云区石槎路33号宾利国际大厦","name":"UWE购商学院讲师","pwd":null,"status":null}]
     * total : null
     * maxPageSize : 9
     */

    private Object data;
    private Object objectData;
    private Object total;
    private int maxPageSize;
    private List<MapListBean> mapList;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getObjectData() {
        return objectData;
    }

    public void setObjectData(Object objectData) {
        this.objectData = objectData;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public int getMaxPageSize() {
        return maxPageSize;
    }

    public void setMaxPageSize(int maxPageSize) {
        this.maxPageSize = maxPageSize;
    }

    public List<MapListBean> getMapList() {
        return mapList;
    }

    public void setMapList(List<MapListBean> mapList) {
        this.mapList = mapList;
    }

    public static class MapListBean {
        /**
         * latitude : 22.519032
         * dist : 103.6
         * autograph : null
         * content : null
         * settingImg : https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/90320190125140433.png
         * positionName : 深圳市南山区海德三道3001号深圳湾木棉花酒店
         * modifyTime : 1550566039000
         * onLineTime : 1550537239000
         * qrCode : null
         * isDeleted : false
         * integral : null
         * rank : 1550506567000
         * id : 903
         * longitude : 113.946753
         * opendId : oyXKv0d5LgM4xdLCrw_RoILr1_N8
         * modifyBy : 903
         * isCancel : null
         * headImg : https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/90320190128175925.png
         * level : 0
         * groupid : null
         * sex : true
         * mobile : null
         * isDistribution : null
         * realName : null
         * createBy : null
         * byname : 朱礼裕
         * createTime : 1547672966000
         * district : 深圳市南山区海德三道3001号深圳湾木棉花酒店
         * name : 朱礼裕
         * pwd : null
         * status : null
         */

        private String latitude;
        private String dist;
        private Object autograph;
        private Object content;
        private String settingImg;
        private String positionName;
        private long modifyTime;
        private long onLineTime;
        private Object qrCode;
        private boolean isDeleted;
        private Object integral;
        private long rank;
        private int id;
        private String longitude;
        private String opendId;
        private String modifyBy;
        private Object isCancel;
        private String headImg;
        private int level;
        private Object groupid;
        private boolean sex;
        private Object mobile;
        private Object isDistribution;
        private Object realName;
        private Object createBy;
        private String byname;
        private long createTime;
        private String district;
        private String name;
        private Object pwd;
        private Object status;

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getDist() {
            return dist;
        }

        public void setDist(String dist) {
            this.dist = dist;
        }

        public Object getAutograph() {
            return autograph;
        }

        public void setAutograph(Object autograph) {
            this.autograph = autograph;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public String getSettingImg() {
            return settingImg;
        }

        public void setSettingImg(String settingImg) {
            this.settingImg = settingImg;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public long getOnLineTime() {
            return onLineTime;
        }

        public void setOnLineTime(long onLineTime) {
            this.onLineTime = onLineTime;
        }

        public Object getQrCode() {
            return qrCode;
        }

        public void setQrCode(Object qrCode) {
            this.qrCode = qrCode;
        }

        public boolean isIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
        }

        public Object getIntegral() {
            return integral;
        }

        public void setIntegral(Object integral) {
            this.integral = integral;
        }

        public long getRank() {
            return rank;
        }

        public void setRank(long rank) {
            this.rank = rank;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getOpendId() {
            return opendId;
        }

        public void setOpendId(String opendId) {
            this.opendId = opendId;
        }

        public String getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(String modifyBy) {
            this.modifyBy = modifyBy;
        }

        public Object getIsCancel() {
            return isCancel;
        }

        public void setIsCancel(Object isCancel) {
            this.isCancel = isCancel;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public Object getGroupid() {
            return groupid;
        }

        public void setGroupid(Object groupid) {
            this.groupid = groupid;
        }

        public boolean isSex() {
            return sex;
        }

        public void setSex(boolean sex) {
            this.sex = sex;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getIsDistribution() {
            return isDistribution;
        }

        public void setIsDistribution(Object isDistribution) {
            this.isDistribution = isDistribution;
        }

        public Object getRealName() {
            return realName;
        }

        public void setRealName(Object realName) {
            this.realName = realName;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public String getByname() {
            return byname;
        }

        public void setByname(String byname) {
            this.byname = byname;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getPwd() {
            return pwd;
        }

        public void setPwd(Object pwd) {
            this.pwd = pwd;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }
    }
}
