package com.uvgouapp.model.circle;


/**
 * - @Author:  ying
 * - @Time:  2019/2/12
 * - @Description:  增加点赞
 */
public class FavortConfig {

    public int itemType;//0.商品  1.生活   用户区分是商品 还是生活点赞

    public String isThumbsId;//被点赞id

    public int thumbsType;//类型 1：生活 2：商品 3：秀场 4.评论   调用接口所传递的参数类型

    public int circlePosition;//当前点赞位置

}
