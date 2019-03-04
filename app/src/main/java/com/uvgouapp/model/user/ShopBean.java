package com.uvgouapp.model.user;


import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/1/29
 * - @Description:  商品
 */
public class ShopBean {

    /**
     * data : null
     * mapList : [{"originalPrice":200,"latitude":null,"discountPrice":null,"userId":"948","commodityType":null,"content":null,"commodityTitle":null,"commodityDescribe":"%E5%A5%BD%E7%9C%8B%E7%9A%84%E8%A1%A3%E6%9C%8D","imgUrl":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550056822387270.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550056821827788.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550056822130619.jpg","positionName":"广州市白云区石槎路","createTime":1550085632000,"commodityCode":"1902131181834274","commodityUnit":null,"id":124,"retailPrice":1000,"longitude":null,"commodityName":"衣服"}]
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
         * originalPrice : 200
         * latitude : null
         * discountPrice : null
         * userId : 948
         * commodityType : null
         * content : null
         * commodityTitle : null
         * commodityDescribe : %E5%A5%BD%E7%9C%8B%E7%9A%84%E8%A1%A3%E6%9C%8D
         * imgUrl : https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550056822387270.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550056821827788.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550056822130619.jpg
         * positionName : 广州市白云区石槎路
         * createTime : 1550085632000
         * commodityCode : 1902131181834274
         * commodityUnit : null
         * id : 124
         * retailPrice : 1000
         * longitude : null
         * commodityName : 衣服
         */

        private int originalPrice;
        private Object latitude;
        private Object discountPrice;
        private String userId;
        private Object commodityType;
        private Object content;
        private Object commodityTitle;
        private String commodityDescribe;
        private String imgUrl;
        private String positionName;
        private long createTime;
        private String commodityCode;
        private Object commodityUnit;
        private int id;
        private int retailPrice;
        private Object longitude;
        private String commodityName;

        public int getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(int originalPrice) {
            this.originalPrice = originalPrice;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(Object discountPrice) {
            this.discountPrice = discountPrice;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Object getCommodityType() {
            return commodityType;
        }

        public void setCommodityType(Object commodityType) {
            this.commodityType = commodityType;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public Object getCommodityTitle() {
            return commodityTitle;
        }

        public void setCommodityTitle(Object commodityTitle) {
            this.commodityTitle = commodityTitle;
        }

        public String getCommodityDescribe() {
            return commodityDescribe;
        }

        public void setCommodityDescribe(String commodityDescribe) {
            this.commodityDescribe = commodityDescribe;
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

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCommodityCode() {
            return commodityCode;
        }

        public void setCommodityCode(String commodityCode) {
            this.commodityCode = commodityCode;
        }

        public Object getCommodityUnit() {
            return commodityUnit;
        }

        public void setCommodityUnit(Object commodityUnit) {
            this.commodityUnit = commodityUnit;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRetailPrice() {
            return retailPrice;
        }

        public void setRetailPrice(int retailPrice) {
            this.retailPrice = retailPrice;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }
    }
}
