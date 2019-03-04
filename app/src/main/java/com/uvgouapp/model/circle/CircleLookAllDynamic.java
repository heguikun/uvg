package com.uvgouapp.model.circle;


import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/2/13
 * - @Description:  查看全部动态
 */
public class CircleLookAllDynamic {

    /**
     * statusCode : 200
     * data : {"total":5,"size":99,"current":1,"records":[{"id":241,"createTime":1550085632000,"modifyTime":1550085632000,"createBy":"948","modifyBy":"948","deleted":null,"content":null,"userId":"948","imgUrl":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550056822387270.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550056821827788.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550056822130619.jpg","positionName":"广州市白云区石槎路","longitude":null,"latitude":null,"tableType":0,"tableId":124,"originalPrice":200,"retailPrice":1000,"discountPrice":null,"commodityName":"衣服","commodityCode":"1902131181834274","commodityType":null,"commodityTitle":null,"commodityDescribe":"%E5%A5%BD%E7%9C%8B%E7%9A%84%E8%A1%A3%E6%9C%8D","commodityUnit":null},{"id":236,"createTime":1549130287000,"modifyTime":1549130287000,"createBy":"948","modifyBy":"948","deleted":null,"content":"%E7%90%86%E4%B8%AA%E5%8F%91%E8%BF%87%E6%96%B0%E5%B9%B4","userId":"948","imgUrl":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1549101482102156.png","positionName":"南昌市青山湖区高新南大道","longitude":null,"latitude":null,"tableType":1,"tableId":124,"originalPrice":null,"retailPrice":null,"discountPrice":null,"commodityName":null,"commodityCode":null,"commodityType":null,"commodityTitle":null,"commodityDescribe":null,"commodityUnit":null}],"pages":1}
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
         * total : 5
         * size : 99
         * current : 1
         * records : [{"id":241,"createTime":1550085632000,"modifyTime":1550085632000,"createBy":"948","modifyBy":"948","deleted":null,"content":null,"userId":"948","imgUrl":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550056822387270.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550056821827788.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550056822130619.jpg","positionName":"广州市白云区石槎路","longitude":null,"latitude":null,"tableType":0,"tableId":124,"originalPrice":200,"retailPrice":1000,"discountPrice":null,"commodityName":"衣服","commodityCode":"1902131181834274","commodityType":null,"commodityTitle":null,"commodityDescribe":"%E5%A5%BD%E7%9C%8B%E7%9A%84%E8%A1%A3%E6%9C%8D","commodityUnit":null},{"id":236,"createTime":1549130287000,"modifyTime":1549130287000,"createBy":"948","modifyBy":"948","deleted":null,"content":"%E7%90%86%E4%B8%AA%E5%8F%91%E8%BF%87%E6%96%B0%E5%B9%B4","userId":"948","imgUrl":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1549101482102156.png","positionName":"南昌市青山湖区高新南大道","longitude":null,"latitude":null,"tableType":1,"tableId":124,"originalPrice":null,"retailPrice":null,"discountPrice":null,"commodityName":null,"commodityCode":null,"commodityType":null,"commodityTitle":null,"commodityDescribe":null,"commodityUnit":null}]
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
             * id : 241
             * createTime : 1550085632000
             * modifyTime : 1550085632000
             * createBy : 948
             * modifyBy : 948
             * deleted : null
             * content : null
             * userId : 948
             * imgUrl : https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550056822387270.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550056821827788.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1550056822130619.jpg
             * positionName : 广州市白云区石槎路
             * longitude : null
             * latitude : null
             * tableType : 0
             * tableId : 124
             * originalPrice : 200
             * retailPrice : 1000
             * discountPrice : null
             * commodityName : 衣服
             * commodityCode : 1902131181834274
             * commodityType : null
             * commodityTitle : null
             * commodityDescribe : %E5%A5%BD%E7%9C%8B%E7%9A%84%E8%A1%A3%E6%9C%8D
             * commodityUnit : null
             */

            private int id;
            private long createTime;
            private long modifyTime;
            private String createBy;
            private String modifyBy;
            private Object deleted;
            private String content;
            private String userId;
            private String imgUrl;
            private String positionName;
            private double longitude;
            private double latitude;
            private int tableType;
            private int tableId;
            private double originalPrice;
            private double retailPrice;
            private Object discountPrice;
            private String commodityName;
            private String commodityCode;
            private Object commodityType;
            private Object commodityTitle;
            private String commodityDescribe;
            private Object commodityUnit;

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

            public Object getDeleted() {
                return deleted;
            }

            public void setDeleted(Object deleted) {
                this.deleted = deleted;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
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

            public String getPositionName() {
                return positionName;
            }

            public void setPositionName(String positionName) {
                this.positionName = positionName;
            }

            public Object getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public int getTableType() {
                return tableType;
            }

            public void setTableType(int tableType) {
                this.tableType = tableType;
            }

            public int getTableId() {
                return tableId;
            }

            public void setTableId(int tableId) {
                this.tableId = tableId;
            }

            public double getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(double originalPrice) {
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

            public Object getCommodityType() {
                return commodityType;
            }

            public void setCommodityType(Object commodityType) {
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
        }
    }
}
