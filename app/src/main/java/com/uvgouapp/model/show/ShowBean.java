package com.uvgouapp.model.show;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/1/2
 * - @Description:  秀场
 */
public class ShowBean {

    /**
     * data : null
     * mapList : [{"modifyBy":null,"messageCount":1,"orderId":null,"latitude":null,"uHeadImg":"http://cdn.duitang.com/uploads/item/201406/17/20140617131104_hcJft.thumb.700_0.jpeg","thumbsType":0,"commodityId":null,"userId":"6","content":"社会我江哥凭一条红内裤走遍天下","tId":null,"uweShareCount":1,"thumbsCount":1,"imgUrl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546946121310&di=298063e103a5ea74e474ae542c92439f&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Ftj%2F2018-10-15%2F5bc459c049b4c.jpg,https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546946121309&di=26c59342dfe32219a14bd92207d0e066&imgtype=0&src=http%3A%2F%2Fpic11.nipic.com%2F20101130%2F5653289_070501635000_2.jpg,https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546946121309&di=eaa8cfd0c1975856aad64b9f5caeedb3&imgtype=0&src=http%3A%2F%2Fscimg.jb51.net%2Fallimg%2F151004%2F14-1510041543455R.jpg","positionName":"广州市白云区石槎路33号杰丰电商大厦A座","uId":6,"createBy":null,"modifyTime":1547712391000,"positionId":null,"isDeleted":false,"createTime":1547540254000,"uName":"社会我江哥","rank":null,"id":10,"longitude":null},{"modifyBy":"string","messageCount":0,"orderId":"string","latitude":"string","uHeadImg":null,"thumbsType":0,"commodityId":"string","userId":"string","content":"string","tId":null,"uweShareCount":0,"thumbsCount":0,"imgUrl":"string","positionName":"string","uId":null,"createBy":"string","modifyTime":1547516548000,"positionId":"string","isDeleted":false,"createTime":1547516548000,"uName":null,"rank":0,"id":39,"longitude":"string"}]
     * total : null
     * maxPageSize : 20
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
         * modifyBy : null
         * messageCount : 1
         * orderId : null
         * latitude : null
         * dist  : 0.3
         * uHeadImg : http://cdn.duitang.com/uploads/item/201406/17/20140617131104_hcJft.thumb.700_0.jpeg
         * thumbsType : 0
         * commodityId : null
         * userId : 6
         * content : 社会我江哥凭一条红内裤走遍天下
         * tId : null
         * sId :null
         * uweShareCount : 1
         * thumbsCount : 1
         * imgUrl : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546946121310&di=298063e103a5ea74e474ae542c92439f&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Ftj%2F2018-10-15%2F5bc459c049b4c.jpg,https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546946121309&di=26c59342dfe32219a14bd92207d0e066&imgtype=0&src=http%3A%2F%2Fpic11.nipic.com%2F20101130%2F5653289_070501635000_2.jpg,https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546946121309&di=eaa8cfd0c1975856aad64b9f5caeedb3&imgtype=0&src=http%3A%2F%2Fscimg.jb51.net%2Fallimg%2F151004%2F14-1510041543455R.jpg
         * positionName : 广州市白云区石槎路33号杰丰电商大厦A座
         * uId : 6
         * createBy : null
         * modifyTime : 1547712391000
         * positionId : null
         * isDeleted : false
         * createTime : 1547540254000
         * uName : 社会我江哥
         * rank : null
         * id : 10
         * longitude : null
         */

        private Object modifyBy;
        private int messageCount;
        private Object orderId;
        private String latitude;
        private String longitude;
        private String dist;
        private String uHeadImg;
        private int thumbsType;
        private Object commodityId;
        private String userId;
        private String content;
        private Object tId;
        private Object sId;
        private int uweShareCount;
        private int thumbsCount;
        private String imgUrl;
        private String positionName;
        private int uId;
        private Object createBy;
        private long modifyTime;
        private Object positionId;
        private boolean isDeleted;
        private long createTime;
        private String uName;
        private Object rank;
        private int id;

        public Object getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(Object modifyBy) {
            this.modifyBy = modifyBy;
        }

        public int getMessageCount() {
            return messageCount;
        }

        public void setMessageCount(int messageCount) {
            this.messageCount = messageCount;
        }

        public Object getOrderId() {
            return orderId;
        }

        public void setOrderId(Object orderId) {
            this.orderId = orderId;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getUHeadImg() {
            return uHeadImg;
        }

        public void setUHeadImg(String uHeadImg) {
            this.uHeadImg = uHeadImg;
        }

        public int getThumbsType() {
            return thumbsType;
        }

        public void setThumbsType(int thumbsType) {
            this.thumbsType = thumbsType;
        }

        public Object getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(Object commodityId) {
            this.commodityId = commodityId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getTId() {
            return tId;
        }

        public void setTId(Object tId) {
            this.tId = tId;
        }

        public Object getsId() {
            return sId;
        }

        public void setsId(Object sId) {
            this.sId = sId;
        }

        public int getUweShareCount() {
            return uweShareCount;
        }

        public void setUweShareCount(int uweShareCount) {
            this.uweShareCount = uweShareCount;
        }

        public int getThumbsCount() {
            return thumbsCount;
        }

        public void setThumbsCount(int thumbsCount) {
            this.thumbsCount = thumbsCount;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public int getUId() {
            return uId;
        }

        public void setUId(int uId) {
            this.uId = uId;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public Object getPositionId() {
            return positionId;
        }

        public void setPositionId(Object positionId) {
            this.positionId = positionId;
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

        public Object getRank() {
            return rank;
        }

        public void setRank(Object rank) {
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

        public String getDist() {
            return dist;
        }

        public void setDist(String dist) {
            this.dist = dist;
        }
    }
}
