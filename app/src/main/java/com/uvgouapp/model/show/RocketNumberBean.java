package com.uvgouapp.model.show;


/**
 * - @Author:  ying
 * - @Time:  2019/1/27
 * - @Description:  火箭数量
 */
public class RocketNumberBean {

    /**
     * statusCode : 200
     * data : {"create_by":null,"is_deleted":false,"create_time":1548467656000,"user_id":"904","rocket_number":0,"modify_time":1548579429000,"rocket_type":1,"sumNumber":0,"id":75683,"modify_by":null}
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
         * create_by : null
         * is_deleted : false
         * create_time : 1548467656000
         * user_id : 904
         * rocket_number : 0
         * modify_time : 1548579429000
         * rocket_type : 1
         * sumNumber : 0
         * id : 75683
         * modify_by : null
         */

        private String create_by;
        private boolean is_deleted;
        private long create_time;
        private String user_id;
        private int rocket_number;
        private long modify_time;
        private int rocket_type;
        private int sumNumber;
        private int id;
        private String modify_by;

        public String getCreate_by() {
            return create_by;
        }

        public void setCreate_by(String create_by) {
            this.create_by = create_by;
        }

        public boolean isIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(boolean is_deleted) {
            this.is_deleted = is_deleted;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public int getRocket_number() {
            return rocket_number;
        }

        public void setRocket_number(int rocket_number) {
            this.rocket_number = rocket_number;
        }

        public long getModify_time() {
            return modify_time;
        }

        public void setModify_time(long modify_time) {
            this.modify_time = modify_time;
        }

        public int getRocket_type() {
            return rocket_type;
        }

        public void setRocket_type(int rocket_type) {
            this.rocket_type = rocket_type;
        }

        public int getSumNumber() {
            return sumNumber;
        }

        public void setSumNumber(int sumNumber) {
            this.sumNumber = sumNumber;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getModify_by() {
            return modify_by;
        }

        public void setModify_by(String modify_by) {
            this.modify_by = modify_by;
        }
    }
}
