package com.uvgouapp.model.shop;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2018/12/24
 * - @Description:
 */
public class ShopCategoryBean {

    /**
     * statusCode : 200
     * data : {"total":1,"size":5,"current":1,"records":[{"id":174,"createTime":1550487547000,"deleted":null,"content":null,"userId":"953","imgUrl":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058190.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058191.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058192.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058193.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058204.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058205.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058206.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058207.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058208.png","positionName":null,"longitude":null,"latitude":null,"originalPrice":null,"retailPrice":690,"discountPrice":null,"commodityName":"BOBBI BROWN芭比波朗虫草精华粉底液 保湿持久遮瑕干皮亲妈不脱妆","commodityCode":"1902181347238755","commodityType":"1","commodityTitle":null,"commodityDescribe":"%E7%B2%89%E5%BA%95%E6%B6%B2%20%E5%B9%B2%E7%9A%AE%E4%BA%B2%E5%A6%88","commodityUnit":null,"collectTotal":null}],"pages":1}
     * msg : success
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
         * total : 1
         * size : 5
         * current : 1
         * records : [{"id":174,"createTime":1550487547000,"deleted":null,"content":null,"userId":"953","imgUrl":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058190.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058191.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058192.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058193.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058204.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058205.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058206.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058207.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058208.png","positionName":null,"longitude":null,"latitude":null,"originalPrice":null,"retailPrice":690,"discountPrice":null,"commodityName":"BOBBI BROWN芭比波朗虫草精华粉底液 保湿持久遮瑕干皮亲妈不脱妆","commodityCode":"1902181347238755","commodityType":"1","commodityTitle":null,"commodityDescribe":"%E7%B2%89%E5%BA%95%E6%B6%B2%20%E5%B9%B2%E7%9A%AE%E4%BA%B2%E5%A6%88","commodityUnit":null,"collectTotal":null}]
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
             * id : 174
             * createTime : 1550487547000
             * deleted : null
             * content : null
             * userId : 953
             * imgUrl : https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058190.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058191.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058192.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058193.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058204.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058205.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058206.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058207.png,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/953201902181058208.png
             * positionName : null
             * longitude : null
             * latitude : null
             * originalPrice : null
             * retailPrice : 690
             * discountPrice : null
             * commodityName : BOBBI BROWN芭比波朗虫草精华粉底液 保湿持久遮瑕干皮亲妈不脱妆
             * commodityCode : 1902181347238755
             * commodityType : 1
             * commodityTitle : null
             * commodityDescribe : %E7%B2%89%E5%BA%95%E6%B6%B2%20%E5%B9%B2%E7%9A%AE%E4%BA%B2%E5%A6%88
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
            private int retailPrice;
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

            public int getRetailPrice() {
                return retailPrice;
            }

            public void setRetailPrice(int retailPrice) {
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
