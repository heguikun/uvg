package com.uvgouapp.model.circle;


import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/1/20
 * - @Description:  商品
 */
public class CircleShopBean {

    /**
     * statusCode : 200
     * data : {"total":43,"size":10,"current":1,"records":[{"id":64,"createTime":1547934261000,"deleted":null,"content":null,"userId":"904","imgUrl":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1547963029911693.png","positionName":"广州市白云区杰丰电商大厦A座","longitude":null,"latitude":null,"tableType":null,"tableId":null,"originalPrice":300,"retailPrice":400,"discountPrice":null,"commodityName":"商品名称","commodityCode":"1901201676507307","commodityType":null,"commodityTitle":null,"commodityDescribe":"商品名称","commodityUnit":null,"userName":"秦王～ying","headImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJOBEgLpicaeLTrB9wWicLxvyg9ias6GyiauqPDjqJocpBpXwD3JCAaSzXostDTHXJFuqS6XOF6yXnE1w/132","thumbs":null,"collect":null,"commentReplyResultList":[],"thumbsResultList":[]},{"id":62,"createTime":1547933855000,"deleted":null,"content":"string","userId":"904","imgUrl":"string","positionName":"string","longitude":"string","latitude":"string","tableType":null,"tableId":null,"originalPrice":0,"retailPrice":0,"discountPrice":0,"commodityName":"string","commodityCode":"1901201492411123","commodityType":"string","commodityTitle":"string","commodityDescribe":"string","commodityUnit":"string","userName":"秦王～ying","headImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJOBEgLpicaeLTrB9wWicLxvyg9ias6GyiauqPDjqJocpBpXwD3JCAaSzXostDTHXJFuqS6XOF6yXnE1w/132","thumbs":null,"collect":null,"commentReplyResultList":[],"thumbsResultList":[]},{"id":63,"createTime":1547933855000,"deleted":null,"content":"string","userId":"904","imgUrl":"string","positionName":"string","longitude":"string","latitude":"string","tableType":null,"tableId":null,"originalPrice":0,"retailPrice":0,"discountPrice":0,"commodityName":"string","commodityCode":"190120310005359","commodityType":"string","commodityTitle":"string","commodityDescribe":"string","commodityUnit":"string","userName":"秦王～ying","headImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJOBEgLpicaeLTrB9wWicLxvyg9ias6GyiauqPDjqJocpBpXwD3JCAaSzXostDTHXJFuqS6XOF6yXnE1w/132","thumbs":true,"collect":null,"commentReplyResultList":[],"thumbsResultList":[{"thumbsHeadImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEJ94hdgGCUK87rAll8aYXGmGH36T6W5eRiaw5IaMsLKWsx1vOlkrDSqVyKAibsmyexratiap45ZEKQ2w/132","thumbsName":"朱礼裕","thumbsUserId":"903"},{"thumbsHeadImg":null,"thumbsName":null,"thumbsUserId":"905"},{"thumbsHeadImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/5EgbE7BFRtVgNPibV603BTuy8Ied2zriaxrwZcsFdBLAr1hXJLa2t1Pdr9gvQemzWM7r1N7XbrbDPusSBV41Y9Ow/132","thumbsName":"UWE购商学院讲师","thumbsUserId":"902"},{"thumbsHeadImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJOBEgLpicaeLTrB9wWicLxvyg9ias6GyiauqPDjqJocpBpXwD3JCAaSzXostDTHXJFuqS6XOF6yXnE1w/132","thumbsName":"秦王～ying","thumbsUserId":"904"}]}],"pages":5}
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
         * total : 43
         * size : 10
         * current : 1
         * records : [{"id":63,"createTime":1547933855000,"deleted":null,"content":"string","userId":"904","imgUrl":"string","positionName":"string","longitude":"string","latitude":"string","tableType":null,"tableId":null,"originalPrice":0,"retailPrice":0,"discountPrice":0,"commodityName":"string","commodityCode":"190120310005359","commodityType":"string","commodityTitle":"string","commodityDescribe":"string","commodityUnit":"string","userName":"秦王～ying","headImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJOBEgLpicaeLTrB9wWicLxvyg9ias6GyiauqPDjqJocpBpXwD3JCAaSzXostDTHXJFuqS6XOF6yXnE1w/132","thumbs":true,"collect":null,"commentReplyResultList":[],"thumbsResultList":[{"thumbsHeadImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEJ94hdgGCUK87rAll8aYXGmGH36T6W5eRiaw5IaMsLKWsx1vOlkrDSqVyKAibsmyexratiap45ZEKQ2w/132","thumbsName":"朱礼裕","thumbsUserId":"903"},{"thumbsHeadImg":null,"thumbsName":null,"thumbsUserId":"905"},{"thumbsHeadImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/5EgbE7BFRtVgNPibV603BTuy8Ied2zriaxrwZcsFdBLAr1hXJLa2t1Pdr9gvQemzWM7r1N7XbrbDPusSBV41Y9Ow/132","thumbsName":"UWE购商学院讲师","thumbsUserId":"902"},{"thumbsHeadImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJOBEgLpicaeLTrB9wWicLxvyg9ias6GyiauqPDjqJocpBpXwD3JCAaSzXostDTHXJFuqS6XOF6yXnE1w/132","thumbsName":"秦王～ying","thumbsUserId":"904"}]}]
         * pages : 5
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
             * id : 65
             * createTime : 1547950966000
             * deleted : null
             * content : null
             * userId : 904
             * imgUrl : https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/-35462f3864dfbab4.jpg
             * positionName : 广州市白云区永华轩红木家具
             * longitude : null
             * latitude : null
             * tableType : null
             * tableId : null
             * originalPrice : 500
             * retailPrice : 1000
             * discountPrice : null
             * commodityName : 面膜
             * commodityCode : 190120390347818
             * commodityType : null
             * commodityTitle : null
             * commodityDescribe : 好面膜
             * commodityUnit : null
             * userName : 秦王～ying
             * headImg : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJOBEgLpicaeLTrB9wWicLxvyg9ias6GyiauqPDjqJocpBpXwD3JCAaSzXostDTHXJFuqS6XOF6yXnE1w/132
             * thumbs : null
             * collect : null
             * commentReplyResultList : []
             * thumbsResultList : []
             */

            private int id;
            private long createTime;
            private Object deleted;
            private Object content;
            private String userId;
            private String imgUrl;
            private String positionName;
            private Object longitude;
            private Object latitude;
            private Object tableType;
            private int tableId;
            private double originalPrice;
            private double retailPrice;
            private double discountPrice;
            private String commodityName;
            private String commodityCode;
            private Object commodityType;
            private Object commodityTitle;
            private String commodityDescribe;
            private Object commodityUnit;
            private String userName;
            private String headImg;
            private boolean thumbs;
            private Object collect;
            private List<CommentReplyResultListBean> commentReplyResultList;
            private List<ThumbsResultListBean> thumbsResultList;

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

            public String getPositionName() {
                return positionName;
            }

            public void setPositionName(String positionName) {
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

            public Object getTableType() {
                return tableType;
            }

            public void setTableType(Object tableType) {
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

            public double getDiscountPrice() {
                return discountPrice;
            }

            public void setDiscountPrice(double discountPrice) {
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

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public boolean getThumbs() {
                return thumbs;
            }

            public void setThumbs(boolean thumbs) {
                this.thumbs = thumbs;
            }

            public Object getCollect() {
                return collect;
            }

            public void setCollect(Object collect) {
                this.collect = collect;
            }

            public List<CommentReplyResultListBean> getCommentReplyResultList() {
                return commentReplyResultList;
            }

            public void setCommentReplyResultList(List<CommentReplyResultListBean> commentReplyResultList) {
                this.commentReplyResultList = commentReplyResultList;
            }

            public List<ThumbsResultListBean> getThumbsResultList() {
                return thumbsResultList;
            }

            public void setThumbsResultList(List<ThumbsResultListBean> thumbsResultList) {
                this.thumbsResultList = thumbsResultList;
            }

            public static class CommentReplyResultListBean {
                /**
                 * tableName : message_comment
                 * thisId : 23
                 * thisContent : 不错不错啊
                 * thisName : 原旭晗
                 * entityId : 24
                 * createTime : 1547703141000
                 * beforeId : null
                 * beforeContent : null
                 * beforeName : null
                 */

                private String tableName;
                private String thisId;
                private String thisContent;
                private String thisName;
                private String entityId;
                private long createTime;
                private Object beforeId;
                private Object beforeContent;
                private Object beforeName;

                public String getTableName() {
                    return tableName;
                }

                public void setTableName(String tableName) {
                    this.tableName = tableName;
                }

                public String getThisId() {
                    return thisId;
                }

                public void setThisId(String thisId) {
                    this.thisId = thisId;
                }

                public String getThisContent() {
                    return thisContent;
                }

                public void setThisContent(String thisContent) {
                    this.thisContent = thisContent;
                }

                public String getThisName() {
                    return thisName;
                }

                public void setThisName(String thisName) {
                    this.thisName = thisName;
                }

                public String getEntityId() {
                    return entityId;
                }

                public void setEntityId(String entityId) {
                    this.entityId = entityId;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public Object getBeforeId() {
                    return beforeId;
                }

                public void setBeforeId(Object beforeId) {
                    this.beforeId = beforeId;
                }

                public Object getBeforeContent() {
                    return beforeContent;
                }

                public void setBeforeContent(Object beforeContent) {
                    this.beforeContent = beforeContent;
                }

                public Object getBeforeName() {
                    return beforeName;
                }

                public void setBeforeName(Object beforeName) {
                    this.beforeName = beforeName;
                }
            }

            public static class ThumbsResultListBean {
                /**
                 * thumbsHeadImg : http://thirdwx.qlogo.cn/mmopen/vi_32/CoHtDnx1Iw8KQMjN5sVNvw8FhzG8tT2erhauHC5JzbrsYYDibwtYTQwPhBnMnpv4Rv0F7OT2CVHnLIbbMuTpRWQ/132
                 * thumbsName : 原旭晗
                 * thumbsUserId :900
                 */

                private String thumbsHeadImg;
                private String thumbsName;
                private String thumbsUserId;

                public String getThumbsHeadImg() {
                    return thumbsHeadImg;
                }

                public void setThumbsHeadImg(String thumbsHeadImg) {
                    this.thumbsHeadImg = thumbsHeadImg;
                }

                public String getThumbsName() {
                    return thumbsName;
                }

                public void setThumbsName(String thumbsName) {
                    this.thumbsName = thumbsName;
                }

                public String getThumbsUserId() {
                    return thumbsUserId;
                }

                public void setThumbsUserId(String thumbsUserId) {
                    this.thumbsUserId = thumbsUserId;
                }
            }
        }
    }
}
