package com.uvgouapp.model.other;


import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/2/17
 * - @Description:  选择类目
 */
public class ChooseCategoryBean {

    /**
     * statusCode : 200
     * data : [{"id":1,"name":"美妆护肤","level":"0","parentId":0,"createTime":1550288296000,"modifyTime":1550288447000,"createBy":"admin","modifyBy":"admin","isDeleted":false},{"id":2,"name":"服装","level":"0","parentId":0,"createTime":1550288296000,"modifyTime":1550288447000,"createBy":"admin","modifyBy":"admin","isDeleted":false},{"id":3,"name":"鞋靴","level":"0","parentId":0,"createTime":1550288334000,"modifyTime":1550288447000,"createBy":"admin","modifyBy":"admin","isDeleted":false},{"id":4,"name":"母婴","level":"0","parentId":0,"createTime":1550288334000,"modifyTime":1550288447000,"createBy":"admin","modifyBy":"admin","isDeleted":false},{"id":5,"name":"家电","level":"0","parentId":0,"createTime":1550288334000,"modifyTime":1550288447000,"createBy":"admin","modifyBy":"admin","isDeleted":false},{"id":6,"name":"居家百货","level":"0","parentId":0,"createTime":1550288335000,"modifyTime":1550288447000,"createBy":"admin","modifyBy":"admin","isDeleted":false},{"id":7,"name":"食品","level":"0","parentId":0,"createTime":1550288335000,"modifyTime":1550288447000,"createBy":"admin","modifyBy":"admin","isDeleted":false},{"id":8,"name":"配饰","level":"0","parentId":0,"createTime":1550288335000,"modifyTime":1550288447000,"createBy":"admin","modifyBy":"admin","isDeleted":false},{"id":9,"name":"手机数码","level":"0","parentId":0,"createTime":1550288336000,"modifyTime":1550288448000,"createBy":"admin","modifyBy":"admin","isDeleted":false},{"id":10,"name":"整车车品","level":"0","parentId":0,"createTime":1550288337000,"modifyTime":1550288448000,"createBy":"admin","modifyBy":"admin","isDeleted":false}]
     * msg : success
     */

    private int statusCode;
    private String msg;
    private List<DataBean> data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 美妆护肤
         * level : 0
         * parentId : 0
         * createTime : 1550288296000
         * modifyTime : 1550288447000
         * createBy : admin
         * modifyBy : admin
         * isDeleted : false
         */

        private int id;
        private String name;
        private String level;
        private int parentId;
        private long createTime;
        private long modifyTime;
        private String createBy;
        private String modifyBy;
        private boolean isDeleted;

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

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
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

        public boolean isIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
        }
    }
}
