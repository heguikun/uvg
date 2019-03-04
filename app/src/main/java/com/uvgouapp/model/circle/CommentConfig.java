package com.uvgouapp.model.circle;


/**
 * - @Author:  ying
 * - @Time:  2019/1/28
 * - @Description:  增加评论
 */
public class CommentConfig {

    public int commentType;//评论的类型(0:普通评论 1：对人回复)

    public int tableId;//商品表ID/生活表id

    public int tableType;//生活,商品  1：生活，2：商品

    public String contentUser;// 当前评论的用户id

    public String replyUser;//被回复的用户id

    public String replyUserName;//被回复的用户名字

    public int circlePosition;//当前评论位置
}
