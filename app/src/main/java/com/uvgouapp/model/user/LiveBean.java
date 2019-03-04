package com.uvgouapp.model.user;


import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/1/30
 * - @Description:  生活
 */
public class LiveBean {

    /**
     * statusCode : 200
     * data : {"total":2,"size":10,"current":1,"records":[{"id":105,"createTime":1548751710000,"content":"生活不易","userId":"948","imgUrl":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1548751704823662.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1548751705561977.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1548751704572795.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1548751705072834.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1548751705316336.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/154875170580784.jpg","positionName":"广州市白云区石槎路","longitude":null,"latitude":null},{"id":104,"createTime":1548751444000,"content":"生活不易","userId":"948","imgUrl":null,"positionName":"广州市白云区石槎路","longitude":null,"latitude":null}],"pages":1}
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
         * size : 10
         * current : 1
         * records : [{"id":105,"createTime":1548751710000,"content":"生活不易","userId":"948","imgUrl":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1548751704823662.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1548751705561977.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1548751704572795.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1548751705072834.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1548751705316336.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/154875170580784.jpg","positionName":"广州市白云区石槎路","longitude":null,"latitude":null},{"id":104,"createTime":1548751444000,"content":"生活不易","userId":"948","imgUrl":null,"positionName":"广州市白云区石槎路","longitude":null,"latitude":null}]
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
             * id : 105
             * createTime : 1548751710000
             * content : 生活不易
             * userId : 948
             * imgUrl : https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1548751704823662.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1548751705561977.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1548751704572795.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1548751705072834.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/1548751705316336.jpg,https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/154875170580784.jpg
             * positionName : 广州市白云区石槎路
             * longitude : null
             * latitude : null
             */

            private int id;
            private long createTime;
            private String content;
            private String userId;
            private String imgUrl;
            private String positionName;
            private String longitude;
            private String latitude;

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

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }
        }
    }
}
