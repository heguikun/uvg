package com.uvgouapp.model.user;


import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/1/26
 * - @Description:  关注好友
 */
public class FollowFriendsBean {

    /**
     * data : null
     * mapList : [{"modifyBy":"905","createBy":"905","modifyTime":1548457441000,"solicitudeId":"893","isDeleted":false,"createTime":1548030907000,"uName":"ChenFengBiao","uHeadImg":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/89320190123103918.png","id":36,"userId":"905","solicitudeType":true},{"modifyBy":"905","createBy":"905","modifyTime":1548457441000,"solicitudeId":"907","isDeleted":false,"createTime":1548112309000,"uName":"原旭晗","uHeadImg":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/90720190123104746.png","id":91,"userId":"905","solicitudeType":true},{"modifyBy":"905","createBy":"905","modifyTime":1548457441000,"solicitudeId":"891","isDeleted":false,"createTime":1548115446000,"uName":"cJy","uHeadImg":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/89120190123102652.png","id":96,"userId":"905","solicitudeType":true}]
     * total : null
     * maxPageSize : 1
     */

    private Object data;
    private Object total;
    private int maxPageSize;
    private List<MapListBean> mapList;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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
         * modifyBy : 905
         * createBy : 905
         * modifyTime : 1548457441000
         * solicitudeId : 893
         * isDeleted : false
         * createTime : 1548030907000
         * uName : ChenFengBiao
         * uHeadImg : https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/89320190123103918.png
         * id : 36
         * userId : 905
         * solicitudeType : true
         */

        private String modifyBy;
        private String createBy;
        private long modifyTime;
        private String solicitudeId;
        private boolean isDeleted;
        private long createTime;
        private String uName;
        private String uHeadImg;
        private int id;
        private String userId;
        private boolean solicitudeType;

        public String getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(String modifyBy) {
            this.modifyBy = modifyBy;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getSolicitudeId() {
            return solicitudeId;
        }

        public void setSolicitudeId(String solicitudeId) {
            this.solicitudeId = solicitudeId;
        }

        public boolean isIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getUName() {
            return uName;
        }

        public void setUName(String uName) {
            this.uName = uName;
        }

        public String getUHeadImg() {
            return uHeadImg;
        }

        public void setUHeadImg(String uHeadImg) {
            this.uHeadImg = uHeadImg;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public boolean isSolicitudeType() {
            return solicitudeType;
        }

        public void setSolicitudeType(boolean solicitudeType) {
            this.solicitudeType = solicitudeType;
        }
    }
}
