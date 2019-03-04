package com.uvgouapp.model.circle;


import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/3/2
 * - @Description:  查看商品动态
 */
public class CircleLookShopDynamic {

    /**
     * statusCode : 200
     * data : {"total":2,"size":99,"current":1,"records":[{"id":322,"createTime":1551365151000,"deleted":null,"content":null,"userId":"903","imgUrl":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444400.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444471.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444482.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444493.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444494.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444505.png","positionName":null,"longitude":null,"latitude":null,"originalPrice":null,"retailPrice":1000,"discountPrice":null,"commodityName":"线雕","commodityCode":"1902281258649604","commodityType":"66","commodityTitle":null,"commodityDescribe":"%E7%BE%8E%E5%AE%B9","commodityUnit":null,"collectTotal":null},{"id":321,"createTime":1551109556000,"deleted":null,"content":null,"userId":"903","imgUrl":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902251543540.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902251544151.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902251544152.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902251544163.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902251544164.png","positionName":"广州市白云区石槎路","longitude":"113.237425","latitude":"23.183716","originalPrice":null,"retailPrice":1000,"discountPrice":null,"commodityName":"线雕","commodityCode":"1902251421683928","commodityType":"66","commodityTitle":null,"commodityDescribe":"%E7%BE%8E%E5%AE%B9%E4%BA%A7%E5%93%81","commodityUnit":null,"collectTotal":null}],"pages":1}
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
         * total : 2
         * size : 99
         * current : 1
         * records : [{"id":322,"createTime":1551365151000,"deleted":null,"content":null,"userId":"903","imgUrl":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444400.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444471.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444482.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444493.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444494.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444505.png","positionName":null,"longitude":null,"latitude":null,"originalPrice":null,"retailPrice":1000,"discountPrice":null,"commodityName":"线雕","commodityCode":"1902281258649604","commodityType":"66","commodityTitle":null,"commodityDescribe":"%E7%BE%8E%E5%AE%B9","commodityUnit":null,"collectTotal":null},{"id":321,"createTime":1551109556000,"deleted":null,"content":null,"userId":"903","imgUrl":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902251543540.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902251544151.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902251544152.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902251544163.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902251544164.png","positionName":"广州市白云区石槎路","longitude":"113.237425","latitude":"23.183716","originalPrice":null,"retailPrice":1000,"discountPrice":null,"commodityName":"线雕","commodityCode":"1902251421683928","commodityType":"66","commodityTitle":null,"commodityDescribe":"%E7%BE%8E%E5%AE%B9%E4%BA%A7%E5%93%81","commodityUnit":null,"collectTotal":null}]
         * pages : 1
         */

        private int total;
        private int size;
        private int current;
        private int pages;
        private List<RecordsBean> records;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            /**
             * id : 322
             * createTime : 1551365151000
             * deleted : null
             * content : null
             * userId : 903
             * imgUrl : https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444400.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444471.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444482.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444493.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444494.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/903201902281444505.png
             * positionName : null
             * longitude : null
             * latitude : null
             * originalPrice : null
             * retailPrice : 1000
             * discountPrice : null
             * commodityName : 线雕
             * commodityCode : 1902281258649604
             * commodityType : 66
             * commodityTitle : null
             * commodityDescribe : %E7%BE%8E%E5%AE%B9
             * commodityUnit : null
             * collectTotal : null
             */

            private int id;
            private long createTime;
            private Object deleted;
            private Object content;
            private String userId;
            private String imgUrl;
            private Object positionName;
            private Object longitude;
            private Object latitude;
            private Object originalPrice;
            private double retailPrice;
            private Object discountPrice;
            private String commodityName;
            private String commodityCode;
            private String commodityType;
            private Object commodityTitle;
            private String commodityDescribe;
            private Object commodityUnit;
            private Object collectTotal;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public Object getDeleted() {
                return deleted;
            }

            public void setDeleted(Object deleted) {
                this.deleted = deleted;
            }

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
                this.content = content;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public Object getPositionName() {
                return positionName;
            }

            public void setPositionName(Object positionName) {
                this.positionName = positionName;
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

            public Object getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(Object originalPrice) {
                this.originalPrice = originalPrice;
            }

            public double getRetailPrice() {
                return retailPrice;
            }

            public void setRetailPrice(double retailPrice) {
                this.retailPrice = retailPrice;
            }

            public Object getDiscountPrice() {
                return discountPrice;
            }

            public void setDiscountPrice(Object discountPrice) {
                this.discountPrice = discountPrice;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public String getCommodityCode() {
                return commodityCode;
            }

            public void setCommodityCode(String commodityCode) {
                this.commodityCode = commodityCode;
            }

            public String getCommodityType() {
                return commodityType;
            }

            public void setCommodityType(String commodityType) {
                this.commodityType = commodityType;
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

            public Object getCommodityUnit() {
                return commodityUnit;
            }

            public void setCommodityUnit(Object commodityUnit) {
                this.commodityUnit = commodityUnit;
            }

            public Object getCollectTotal() {
                return collectTotal;
            }

            public void setCollectTotal(Object collectTotal) {
                this.collectTotal = collectTotal;
            }
        }
    }
}
