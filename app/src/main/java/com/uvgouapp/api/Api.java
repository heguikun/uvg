package com.uvgouapp.api;

/**
 * - @Author:  ying
 * - @Time:  2018/12/22
 * - @Description: 接口
 */
public class Api {

    //private static final String BASE_URL = "http://192.168.1.48:8083/";//彪哥测试

    //private static final String BASE_URL = "http://192.168.1.47:8083/";//伟洲测试

    //private static final String BASE_URL = "http://192.168.1.43:8083/";//陈琳测试

    //private static final String BASE_URL = "http://192.168.1.47:8088/";//伟洲测试

    private static final String BASE_URL = "http://40.73.74.249:8050/";//线上

    //------------------------------商城-----------------------------------

    public static final String SHOP_QUERY_CATEGORY = BASE_URL + "commodity/listCommodityByCategory";//查询商品类目

    //---------------------------------秀场----------------------------------------
    public static final String SHOW_RELEASE = BASE_URL + "showField/addOrUpdate";//发布秀场

    public static final String SHOW_QUERY_ALL = BASE_URL + "showField/getByPage";//查询全部

    public static final String SHOW_GIVE_THUMBS_UP = BASE_URL + "thumbs/addOrUpdate";//点赞

    public static final String SHOW_SHARE = BASE_URL + "uweShare/addOrUpdate";//分享

    public static final String SHOW_COMMENT = BASE_URL + "comment/getCommentReply";//评论回复  (老)

    public static final String SHOW_COMMENT_REPLY = BASE_URL + "comment/listCommentReply";//评论回复  (新)

    public static final String ADD_COMMENT_INFO = BASE_URL + "comment/addOrUpdateMessageComment";//添加评论信息

    public static final String CIRCLE_ADD_COMMENT_REPLY = BASE_URL + "momentsComment/addOrUpdateBusinessCircleComment";//增加淘友圈评论和回复

    //----------------------------------火箭-------------------------------

    public static final String ROCKET_QUERY_NUMBER = BASE_URL + "rocket/getByUserId";//查询火箭数量

    public static final String ROCKET_PUT_TOP = BASE_URL + "rocket/toClick";//火箭置顶

    //---------------------------------淘友圈---------------------------------
    public static final String CIRCLE_ALL_DYNAMIC = BASE_URL + "moments/listBusinessCircle";//全部动态

    public static final String CIRCLE_SHOP_DYNAMIC = BASE_URL + "moments/listCommodityMessage";//商品动态

    public static final String CIRCLE_LIVE_DYNAMIC = BASE_URL + "moments/listLifeMessage";//生活动态

    public static final String CIRCLE_SHARE_LIFE = BASE_URL + "life/addOrUpdateLifeMessage";//分享生活

    public static final String CIRCLE_ADD_COLLECT = BASE_URL + "collect/insertOrUpdateCollect";//添加收藏

    public static final String CIRCLE_DELETE_COLLECT = BASE_URL + "collect/addOrDeleteCollect";//删除收藏

    public static final String CIRCLE_UPLOAD_PICTURES = "http://40.73.74.249:8081/azure/upload";//上传图片

    public static final String CIRCLE_QUERY_USER_ALL_DYNAMIC = BASE_URL + "moments/listAllMoments";//查询用户全部动态

    public static final String CIRCLE_QUERY_USER_SHOP_DYNAMIC = BASE_URL + "commodity/listCommodityMoments";//查询用户商品动态

    public static final String CIRCLE_QUERY_USER_LIVE_DYNAMIC = BASE_URL + "life/listLiveMoments";//查询用户生活动态


    //-------------------------------用户--------------------------------

    public static final String USER_WX_LOGIN = "http://40.73.74.249:8084/user/getAccessToken";//微信登录

    public static final String USER_SHOP_LOGIN = "http://139.217.224.124:8080/app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile&r=account.apploginregister";//商城登录

    public static final String USER_QUERY = BASE_URL + "user/getById";//查询用户

    public static final String QUERY_MY = BASE_URL + "user/getMeById";//查询自己

    public static final String USER_UPDATE = BASE_URL + "user/addOrUpdate";//修改用户信息

    public static final String USER_ONLINE = BASE_URL + "user/getOnLineUser";//秀场在线用户

    public static final String USER_FOLLOW = BASE_URL + "userSolicitudeController/addOrUpdate";//用户关注

    public static final String USER_CANCEL_FOLLOW = BASE_URL + "userSolicitudeController/cancelSolicitude";//取消关注

    public static final String USER_QUERY_FOLLOW = BASE_URL + "userSolicitudeController/getIsSolicitude";//查询用户关注

    public static final String USER_QUERY_FOLLOW_FRIENDS = BASE_URL + "userSolicitudeController/getByUserId";//查询用户关注好友

    public static final String USER_QUERY_SHOP_INFO = BASE_URL + "shopManage/getComByUserId";//查询店铺商品信息

    public static final String USER_BINDING_PHONE = BASE_URL + "user/bindingPhone";//用户绑定手机号码

    public static final String USER_LOGOUT = "http://40.73.74.249:8084/user/logout";//退出登录

    public static final String USER_WECHAT_PAYMENT = "http://40.73.74.249:8084/WechatPay/unifiedorder";//微信支付

    public static final String QUERY_ORDER = "http://40.73.74.249:8084/WechatPay/orderquery";//订单查询

    public static final String APP_VERSION = BASE_URL + "version/getAppVersion";//版本更新

    //--------------------------------店铺------------------------------------

    public static final String RELEASE_SHOP = BASE_URL + "moments/addOrUpdateCommodityMessage";//发布商品

    public static final String APPLY_STORE = BASE_URL + "shopManage/addOrUpdate";//申请店铺

    public static final String QUERY_STORE = BASE_URL + "shopManage/getByUserId";//查询店铺

    public static final String ONE_KEY_SHOP = BASE_URL + "commodityBeShop/copyCommodity";//一键开店

    public static final String QUERY_YES_COPY_SHOP = BASE_URL + "shopManage/getNotCopyCommdityId";//查询可以复制的商品

    public static final String QUERY_SHOP_CATEGORY = BASE_URL + "commodity/getShopGenre";//查询商铺类目

    public static final String SEND_PHONE_SMS = BASE_URL + "sms/sendSms";//发送手机短信

    //---------------------------------H5链接地址---------------------------------

    private static final String WEB_VIEW_BASE = "http://40.73.74.249:8080/uwe/";

    public static final String STORE_URL = WEB_VIEW_BASE + "shops/store.html";//店铺链接

    public static final String WALLET_MANAGE_URL = WEB_VIEW_BASE + "wallet_manage/DailyCash.html?userId=%s";//钱包管理

    public static final String RECEIVING_ADDRESS_URL = WEB_VIEW_BASE + "shops/site_manage.html?userId=%s";//收获地址

    public static final String SHOP_CATEGORY = WEB_VIEW_BASE + "store/tag_product.html?categoryId=%s&userId=%s";//商品分类

    public static final String SHOP_CATEGORY_DETAILS = WEB_VIEW_BASE + "store/product_details.html?userId=%s&id=%s";//商品分类详情页

    public static final String SHOPPING_CART = WEB_VIEW_BASE + "store/shopping.html?userId=%s";//购物车

    public static final String ENTER_AGREEMENT = WEB_VIEW_BASE + "agreement/agreement.html";//入驻协议

    public static final String COLLECTION = WEB_VIEW_BASE + "collect/my_collect.html?userId=%s";//收藏

    public static final String INTEGRAL_MANAGE = WEB_VIEW_BASE + "integral_manage/integ_manage.html?userId=%s";//积分管理

    public static final String ORDER_MANAGE = WEB_VIEW_BASE + "My_order_manage/my_order_manage.html?userId=%s";//订单管理

}
