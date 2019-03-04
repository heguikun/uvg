package com.uvgouapp.model.show;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/1/15
 * - @Description:  秀场评论
 */
public class ShowCommentReplyBean {

    /**
     * statusCode : 200
     * data : {"total":2,"size":10,"current":1,"records":[{"commentId":251,"tableId":"180","tableType":3,"createTime":1548756589000,"content":"我还以为在吃沙发呢","userId":"912","userName":"比目鱼","headImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK90wXNIgluBzB5yM1sFseDC2D8rgW2kqjY8vrTiaUIwW3sFTsIjzCMUlPiaSyp64qxgrFAZNnjDZCA/132","thumbs":null,"thumbsCount":0,"replyResultList":{"total":1,"size":10,"current":1,"records":[{"replyId":51,"createTime":1548836333000,"content":"%E6%BA%9C%E5%88%B0%E4%B8%8D%E8%A1%8C%F0%9F%98%8F","userId":"905","userName":"浅缘","headImg":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/90520190123113821.png"}],"pages":1}}],"pages":1}
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
         * total : 2
         * size : 10
         * current : 1
         * records : [{"commentId":251,"tableId":"180","tableType":3,"createTime":1548756589000,"content":"我还以为在吃沙发呢","userId":"912","userName":"比目鱼","headImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK90wXNIgluBzB5yM1sFseDC2D8rgW2kqjY8vrTiaUIwW3sFTsIjzCMUlPiaSyp64qxgrFAZNnjDZCA/132","thumbs":null,"thumbsCount":0,"replyResultList":{"total":1,"size":10,"current":1,"records":[{"replyId":51,"createTime":1548836333000,"content":"%E6%BA%9C%E5%88%B0%E4%B8%8D%E8%A1%8C%F0%9F%98%8F","userId":"905","userName":"浅缘","headImg":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/90520190123113821.png"}],"pages":1}}]
         * pages : 1
         */

        private int total;
        private int size;
        private int current;
        private int pages;
        private List<RecordsBeanComment> records;

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

        public List<RecordsBeanComment> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBeanComment> records) {
            this.records = records;
        }

        public static class RecordsBeanComment {
            /**
             * commentId : 251
             * tableId : 180
             * tableType : 3
             * createTime : 1548756589000
             * content : 我还以为在吃沙发呢
             * userId : 912
             * userName : 比目鱼
             * headImg : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK90wXNIgluBzB5yM1sFseDC2D8rgW2kqjY8vrTiaUIwW3sFTsIjzCMUlPiaSyp64qxgrFAZNnjDZCA/132
             * thumbs : null
             * thumbsCount : 0
             * replyResultList : {"total":1,"size":10,"current":1,"records":[{"replyId":51,"createTime":1548836333000,"content":"%E6%BA%9C%E5%88%B0%E4%B8%8D%E8%A1%8C%F0%9F%98%8F","userId":"905","userName":"浅缘","headImg":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/90520190123113821.png"}],"pages":1}
             */

            private int commentId;
            private String tableId;
            private int tableType;
            private long createTime;
            private String content;
            private String userId;
            private String userName;
            private String headImg;
            private Object thumbs;
            private int thumbsCount;
            private ReplyResultListBean replyResultList;

            public int getCommentId() {
                return commentId;
            }

            public void setCommentId(int commentId) {
                this.commentId = commentId;
            }

            public String getTableId() {
                return tableId;
            }

            public void setTableId(String tableId) {
                this.tableId = tableId;
            }

            public int getTableType() {
                return tableType;
            }

            public void setTableType(int tableType) {
                this.tableType = tableType;
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

            public Object getThumbs() {
                return thumbs;
            }

            public void setThumbs(Object thumbs) {
                this.thumbs = thumbs;
            }

            public int getThumbsCount() {
                return thumbsCount;
            }

            public void setThumbsCount(int thumbsCount) {
                this.thumbsCount = thumbsCount;
            }

            public ReplyResultListBean getReplyResultList() {
                return replyResultList;
            }

            public void setReplyResultList(ReplyResultListBean replyResultList) {
                this.replyResultList = replyResultList;
            }

            public static class ReplyResultListBean {
                /**
                 * total : 1
                 * size : 10
                 * current : 1
                 * records : [{"replyId":51,"createTime":1548836333000,"content":"%E6%BA%9C%E5%88%B0%E4%B8%8D%E8%A1%8C%F0%9F%98%8F","userId":"905","userName":"浅缘","headImg":"https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/90520190123113821.png"}]
                 * pages : 1
                 */

                private int total;
                private int size;
                private int current;
                private int pages;
                private List<RecordsBeanReply> records;

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

                public List<RecordsBeanReply> getRecords() {
                    return records;
                }

                public void setRecords(List<RecordsBeanReply> records) {
                    this.records = records;
                }

                public static class RecordsBeanReply {
                    /**
                     * replyId : 51
                     * createTime : 1548836333000
                     * content : %E6%BA%9C%E5%88%B0%E4%B8%8D%E8%A1%8C%F0%9F%98%8F
                     * userId : 905
                     * userName : 浅缘
                     * headImg : https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/90520190123113821.png
                     */

                    private int replyId;
                    private long createTime;
                    private String content;
                    private String userId;
                    private String userName;
                    private String headImg;

                    public int getReplyId() {
                        return replyId;
                    }

                    public void setReplyId(int replyId) {
                        this.replyId = replyId;
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
                }
            }
        }
    }
}
