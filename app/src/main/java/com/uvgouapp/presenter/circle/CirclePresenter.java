package com.uvgouapp.presenter.circle;


import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.base.BaseFragment;
import com.uvgouapp.common.base.BasePresenter;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.contract.circle.CircleContract;
import com.uvgouapp.model.circle.CircleAllBean;
import com.uvgouapp.model.circle.CircleAllDynamic;
import com.uvgouapp.model.circle.CirclePhotoBean;
import com.uvgouapp.model.circle.CommentConfig;
import com.uvgouapp.model.circle.CommentItem;
import com.uvgouapp.model.circle.FavortConfig;
import com.uvgouapp.model.circle.FavortItem;
import com.uvgouapp.model.other.InfoBean;
import com.uvgouapp.model.user.User;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * - @Author:  ying
 * - @Time:  2019/1/20
 * - @Description:  淘友圈
 */
public class CirclePresenter extends BasePresenter<CircleContract.View> implements CircleContract.Presenter {

    private BaseFragment mBaseFragment;

    public CirclePresenter(BaseFragment baseFragment) {
        this.mBaseFragment = baseFragment;
    }

    @Override
    public void requestCircleAllData(String userId, String current, String size) {
        if (!mBaseFragment.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.CIRCLE_ALL_DYNAMIC)
                .params("userId", userId)
                .params("current", current)
                .params("size", size)
                .execute(new StringCallback() {
                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        mView.showLoading();
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        mView.hideLoading();
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                CircleAllDynamic circleAllDynamic = GsonUtil.GsonToBean(body, CircleAllDynamic.class);
                                if (circleAllDynamic != null) {
                                    int statusCode = circleAllDynamic.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        CircleAllDynamic.DataBean data = circleAllDynamic.getData();
                                        if (data != null) {
                                            List<CircleAllBean> list = new ArrayList<>();
                                            List<CircleAllDynamic.DataBean.RecordsBean> records = data.getRecords();
                                            int size = records.size();
                                            if (size > 0) {
                                                for (int i = 0; i < size; i++) {
                                                    CircleAllBean circleAllBean = new CircleAllBean();
                                                    CircleAllDynamic.DataBean.RecordsBean recordsBean = records.get(i);
                                                    circleAllBean.id = recordsBean.getId();//淘友圈id
                                                    circleAllBean.tableId = recordsBean.getTableId();
                                                    //--------------用户信息--------------

                                                    circleAllBean.userId = recordsBean.getUserId();
                                                    circleAllBean.userName = recordsBean.getUserName();
                                                    circleAllBean.headImg = recordsBean.getHeadImg();

                                                    //-----------------商品---------生活-------------------
                                                    int tableType = recordsBean.getTableType();
                                                    switch (tableType) {
                                                        //------------------------------------商品-------------------------------
                                                        case 0:
                                                            circleAllBean.itemType = 0;
                                                            circleAllBean.commodityShopId = recordsBean.getCommodityShopId();
                                                            circleAllBean.commodityName = recordsBean.getCommodityName();
                                                            circleAllBean.commodityDescribe = recordsBean.getCommodityDescribe();
                                                            circleAllBean.originalPrice = recordsBean.getOriginalPrice();
                                                            circleAllBean.retailPrice = recordsBean.getRetailPrice();
                                                            circleAllBean.collect = recordsBean.getCollect();
                                                            circleAllBean.thumbs = recordsBean.getThumbs();
                                                            circleAllBean.imgUrl = recordsBean.getImgUrl();
                                                            circleAllBean.createTime = recordsBean.getCreateTime();
                                                            //--------------------------评论---------------------------
                                                            List<CircleAllDynamic.DataBean.RecordsBean.CommentReplyResultListBean> commentReplyResultListShop = recordsBean.getCommentReplyResultList();
                                                            if (commentReplyResultListShop != null) {
                                                                int sizeShop = commentReplyResultListShop.size();
                                                                if (sizeShop > 0) {
                                                                    for (int j = 0; j < sizeShop; j++) {
                                                                        CircleAllDynamic.DataBean.RecordsBean.CommentReplyResultListBean commentReplyResultListBean = commentReplyResultListShop.get(j);
                                                                        CommentItem commentItem = new CommentItem();

                                                                        commentItem.contentUser = commentReplyResultListBean.getContentUser();
                                                                        commentItem.contentUserName = commentReplyResultListBean.getContentUserName();
                                                                        commentItem.replyUser = commentReplyResultListBean.getReplyUser();
                                                                        commentItem.replyUserName = commentReplyResultListBean.getReplyUserName();
                                                                        commentItem.content = commentReplyResultListBean.getContent();
                                                                        circleAllBean.comments.add(commentItem);
                                                                    }
                                                                }
                                                            }

                                                            //---------------------------点赞---------------------------
                                                            List<CircleAllDynamic.DataBean.RecordsBean.ThumbsResultListBean> thumbsResultListShop = recordsBean.getThumbsResultList();
                                                            if (thumbsResultListShop != null) {
                                                                int countShop = thumbsResultListShop.size();
                                                                if (countShop > 0) {
                                                                    for (int z = 0; z < countShop; z++) {
                                                                        CircleAllDynamic.DataBean.RecordsBean.ThumbsResultListBean thumbsResultListBean = thumbsResultListShop.get(z);
                                                                        FavortItem favortItem = new FavortItem();
                                                                        favortItem.thumbsUserId = thumbsResultListBean.getThumbsUserId();
                                                                        favortItem.thumbsName = thumbsResultListBean.getThumbsName();
                                                                        favortItem.thumbsHeadImg = thumbsResultListBean.getThumbsHeadImg();
                                                                        circleAllBean.favorters.add(favortItem);
                                                                    }
                                                                }
                                                            }
                                                            break;
                                                        //------------------------------------生活------------------------------
                                                        case 1:
                                                            circleAllBean.itemType = 1;
                                                            circleAllBean.imgUrl = recordsBean.getImgUrl();
                                                            circleAllBean.content = recordsBean.getContent();
                                                            circleAllBean.thumbs = recordsBean.getThumbs();
                                                            circleAllBean.collect = recordsBean.getCollect();
                                                            circleAllBean.createTime = recordsBean.getCreateTime();

                                                            //------------------------评论-----------------------
                                                            List<CircleAllDynamic.DataBean.RecordsBean.CommentReplyResultListBean> commentReplyResultListLive = recordsBean.getCommentReplyResultList();
                                                            if (commentReplyResultListLive != null) {
                                                                int sizeLive = commentReplyResultListLive.size();
                                                                if (sizeLive > 0) {
                                                                    for (int j = 0; j < sizeLive; j++) {
                                                                        CircleAllDynamic.DataBean.RecordsBean.CommentReplyResultListBean commentReplyResultListBean = commentReplyResultListLive.get(j);
                                                                        CommentItem commentItem = new CommentItem();

                                                                        commentItem.contentUser = commentReplyResultListBean.getContentUser();
                                                                        commentItem.contentUserName = commentReplyResultListBean.getContentUserName();
                                                                        commentItem.replyUser = commentReplyResultListBean.getReplyUser();
                                                                        commentItem.replyUserName = commentReplyResultListBean.getReplyUserName();
                                                                        commentItem.content = commentReplyResultListBean.getContent();
                                                                        circleAllBean.comments.add(commentItem);
                                                                    }
                                                                }
                                                            }
                                                            //------------------------点赞--------------
                                                            List<CircleAllDynamic.DataBean.RecordsBean.ThumbsResultListBean> thumbsResultListLive = recordsBean.getThumbsResultList();
                                                            if (thumbsResultListLive != null) {
                                                                int countLive = thumbsResultListLive.size();
                                                                if (countLive > 0) {
                                                                    for (int z = 0; z < countLive; z++) {
                                                                        CircleAllDynamic.DataBean.RecordsBean.ThumbsResultListBean thumbsResultListBean = thumbsResultListLive.get(z);
                                                                        FavortItem favortItem = new FavortItem();
                                                                        favortItem.thumbsUserId = thumbsResultListBean.getThumbsUserId();
                                                                        favortItem.thumbsName = thumbsResultListBean.getThumbsName();
                                                                        favortItem.thumbsHeadImg = thumbsResultListBean.getThumbsHeadImg();
                                                                        circleAllBean.favorters.add(favortItem);
                                                                    }
                                                                }
                                                            }
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                    list.add(circleAllBean);
                                                }
                                                mView.updateloadCircleData(list);
                                            } else {
                                                ToastUtils.showShort("没有更多的数据");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        mView.hideLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void requestShopDynamic(String userId, String current, String size) {
        if (!mBaseFragment.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.CIRCLE_SHOP_DYNAMIC)
                .params("userId", userId)
                .params("current", current)
                .params("size", size)
                .execute(new StringCallback() {

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        mView.showLoading();
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                CircleAllDynamic circleAllDynamic = GsonUtil.GsonToBean(body, CircleAllDynamic.class);
                                if (circleAllDynamic != null) {
                                    int statusCode = circleAllDynamic.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        CircleAllDynamic.DataBean data = circleAllDynamic.getData();
                                        if (data != null) {
                                            List<CircleAllBean> list = new ArrayList<>();
                                            List<CircleAllDynamic.DataBean.RecordsBean> records = data.getRecords();
                                            int size = records.size();
                                            if (size > 0) {
                                                for (int i = 0; i < size; i++) {
                                                    CircleAllBean circleAllBean = new CircleAllBean();
                                                    CircleAllDynamic.DataBean.RecordsBean recordsBean = records.get(i);
                                                    circleAllBean.id = recordsBean.getId();//淘友圈id
                                                    circleAllBean.tableId = recordsBean.getTableId();
                                                    //--------------用户信息--------------

                                                    circleAllBean.userId = recordsBean.getUserId();
                                                    circleAllBean.userName = recordsBean.getUserName();
                                                    circleAllBean.headImg = recordsBean.getHeadImg();

                                                    //------------------------------------商品-------------------------------
                                                    circleAllBean.itemType = 0;
                                                    circleAllBean.commodityShopId = recordsBean.getCommodityShopId();
                                                    circleAllBean.commodityName = recordsBean.getCommodityName();
                                                    circleAllBean.commodityDescribe = recordsBean.getCommodityDescribe();
                                                    circleAllBean.originalPrice = recordsBean.getOriginalPrice();
                                                    circleAllBean.retailPrice = recordsBean.getRetailPrice();
                                                    circleAllBean.collect = recordsBean.getCollect();
                                                    circleAllBean.thumbs = recordsBean.getThumbs();
                                                    circleAllBean.imgUrl = recordsBean.getImgUrl();
                                                    circleAllBean.createTime = recordsBean.getCreateTime();

                                                    //--------------------------评论---------------------------
                                                    List<CircleAllDynamic.DataBean.RecordsBean.CommentReplyResultListBean> commentReplyResultListShop = recordsBean.getCommentReplyResultList();
                                                    if (commentReplyResultListShop != null) {
                                                        int sizeShop = commentReplyResultListShop.size();
                                                        if (sizeShop > 0) {
                                                            for (int j = 0; j < sizeShop; j++) {
                                                                CircleAllDynamic.DataBean.RecordsBean.CommentReplyResultListBean commentReplyResultListBean = commentReplyResultListShop.get(j);
                                                                CommentItem commentItem = new CommentItem();

                                                                commentItem.contentUser = commentReplyResultListBean.getContentUser();
                                                                commentItem.contentUserName = commentReplyResultListBean.getContentUserName();
                                                                commentItem.replyUser = commentReplyResultListBean.getReplyUser();
                                                                commentItem.replyUserName = commentReplyResultListBean.getReplyUserName();
                                                                commentItem.content = commentReplyResultListBean.getContent();
                                                                circleAllBean.comments.add(commentItem);
                                                            }
                                                        }
                                                    }

                                                    //---------------------------点赞---------------------------
                                                    List<CircleAllDynamic.DataBean.RecordsBean.ThumbsResultListBean> thumbsResultListShop = recordsBean.getThumbsResultList();
                                                    if (thumbsResultListShop != null) {
                                                        int countShop = thumbsResultListShop.size();
                                                        if (countShop > 0) {
                                                            for (int z = 0; z < countShop; z++) {
                                                                CircleAllDynamic.DataBean.RecordsBean.ThumbsResultListBean thumbsResultListBean = thumbsResultListShop.get(z);
                                                                FavortItem favortItem = new FavortItem();
                                                                favortItem.thumbsUserId = thumbsResultListBean.getThumbsUserId();
                                                                favortItem.thumbsName = thumbsResultListBean.getThumbsName();
                                                                favortItem.thumbsHeadImg = thumbsResultListBean.getThumbsHeadImg();
                                                                circleAllBean.favorters.add(favortItem);
                                                            }
                                                        }
                                                    }
                                                    list.add(circleAllBean);
                                                }
                                                mView.updateloadCircleData(list);
                                            } else {
                                                ToastUtils.showShort("没有更多的数据");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        mView.hideLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void requestLiveDynamic(String userId, String current, String size) {
        if (!mBaseFragment.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.CIRCLE_LIVE_DYNAMIC)
                .params("userId", userId)
                .params("current", current)
                .params("size", size)
                .execute(new StringCallback() {

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        mView.showLoading();
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                CircleAllDynamic circleAllDynamic = GsonUtil.GsonToBean(body, CircleAllDynamic.class);
                                if (circleAllDynamic != null) {
                                    int statusCode = circleAllDynamic.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        CircleAllDynamic.DataBean data = circleAllDynamic.getData();
                                        if (data != null) {
                                            List<CircleAllBean> list = new ArrayList<>();
                                            List<CircleAllDynamic.DataBean.RecordsBean> records = data.getRecords();
                                            int size = records.size();
                                            if (size > 0) {
                                                for (int i = 0; i < size; i++) {
                                                    CircleAllBean circleAllBean = new CircleAllBean();
                                                    CircleAllDynamic.DataBean.RecordsBean recordsBean = records.get(i);
                                                    circleAllBean.id = recordsBean.getId();//淘友圈id
                                                    circleAllBean.tableId = recordsBean.getTableId();
                                                    //--------------------用户信息---------------------

                                                    circleAllBean.userId = recordsBean.getUserId();
                                                    circleAllBean.userName = recordsBean.getUserName();
                                                    circleAllBean.headImg = recordsBean.getHeadImg();

                                                    //------------------------------------生活------------------------------
                                                    circleAllBean.itemType = 1;
                                                    circleAllBean.imgUrl = recordsBean.getImgUrl();
                                                    circleAllBean.content = recordsBean.getContent();
                                                    circleAllBean.thumbs = recordsBean.getThumbs();
                                                    circleAllBean.collect = recordsBean.getCollect();
                                                    circleAllBean.createTime = recordsBean.getCreateTime();

                                                    //------------------------评论-----------------------
                                                    List<CircleAllDynamic.DataBean.RecordsBean.CommentReplyResultListBean> commentReplyResultListLive = recordsBean.getCommentReplyResultList();
                                                    if (commentReplyResultListLive != null) {
                                                        int sizeLive = commentReplyResultListLive.size();
                                                        if (sizeLive > 0) {
                                                            for (int j = 0; j < sizeLive; j++) {
                                                                CircleAllDynamic.DataBean.RecordsBean.CommentReplyResultListBean commentReplyResultListBean = commentReplyResultListLive.get(j);
                                                                CommentItem commentItem = new CommentItem();

                                                                commentItem.contentUser = commentReplyResultListBean.getContentUser();
                                                                commentItem.contentUserName = commentReplyResultListBean.getContentUserName();
                                                                commentItem.replyUser = commentReplyResultListBean.getReplyUser();
                                                                commentItem.replyUserName = commentReplyResultListBean.getReplyUserName();
                                                                commentItem.content = commentReplyResultListBean.getContent();
                                                                circleAllBean.comments.add(commentItem);
                                                            }
                                                        }
                                                    }
                                                    //------------------------点赞-------------------------
                                                    List<CircleAllDynamic.DataBean.RecordsBean.ThumbsResultListBean> thumbsResultListLive = recordsBean.getThumbsResultList();
                                                    if (thumbsResultListLive != null) {
                                                        int countLive = thumbsResultListLive.size();
                                                        if (countLive > 0) {
                                                            for (int z = 0; z < countLive; z++) {
                                                                CircleAllDynamic.DataBean.RecordsBean.ThumbsResultListBean thumbsResultListBean = thumbsResultListLive.get(z);
                                                                FavortItem favortItem = new FavortItem();
                                                                favortItem.thumbsUserId = thumbsResultListBean.getThumbsUserId();
                                                                favortItem.thumbsName = thumbsResultListBean.getThumbsName();
                                                                favortItem.thumbsHeadImg = thumbsResultListBean.getThumbsHeadImg();
                                                                circleAllBean.favorters.add(favortItem);
                                                            }
                                                        }
                                                    }
                                                    list.add(circleAllBean);
                                                }
                                                mView.updateloadCircleData(list);
                                            } else {
                                                ToastUtils.showShort("没有更多的数据");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        mView.hideLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mView.hideLoading();
                    }

                });
    }

    /**
     * 点赞
     *
     * @param favortConfig isThumbsId 被点赞id
     * @param favortConfig thumbsType 点赞类型 1.生活圈 2.淘友圈 3.秀场
     * @param userId       用户id
     */
    @Override
    public void requestGive(FavortConfig favortConfig, String userId) {
        if (!mBaseFragment.isConnectNetWorkAndBindView()) {
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("isThumbsId", favortConfig.isThumbsId);
        map.put("thumbsType", favortConfig.thumbsType);
        map.put("userId", userId);
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.SHOW_GIVE_THUMBS_UP)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                InfoBean infoBean = GsonUtil.GsonToBean(body, InfoBean.class);
                                if (infoBean != null) {
                                    int statusCode = infoBean.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        FavortItem favortItem = new FavortItem();
                                        favortItem.thumbsName = SPUtils.getInstance().getString("name");
                                        mView.updateAddFavorite(favortConfig.itemType, favortConfig.circlePosition, favortItem);
                                    }
                                }
                            }
                        }
                    }
                });
    }

    /**
     * @param commentConfig commentType 评论的类型(0:普通评论 1：对人回复)
     *                      content      评论内容
     *                      contentUser    当前评论的用户id
     *                      replyUser  被回复的用户id
     *                      tableId   商品表ID/生活表id
     *                      tableType  1 生活  2商品
     */
    @Override
    public void replyComment(String content, CommentConfig commentConfig) {
        if (!mBaseFragment.isConnectNetWorkAndBindView()) {
            return;
        }

        if (!StringUtils.isEmpty(content)) {
            Map<String, Object> map = new HashMap<>();
            map.put("commentType", commentConfig.commentType);
            map.put("content", content);
            map.put("contentUser", commentConfig.contentUser);
            map.put("replyUser", commentConfig.replyUser);
            map.put("tableId", commentConfig.tableId);
            map.put("tableType", commentConfig.tableType);
            String json = GsonUtil.GsonToString(map);

            OkGo.<String>post(Api.CIRCLE_ADD_COMMENT_REPLY)
                    .upJson(json)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            if (response != null) {
                                if (response.code() == Constants.SUCCESS_CODE) {
                                    CommentItem commentItem = new CommentItem();
                                    commentItem.contentUser = SPUtils.getInstance().getString("userId");
                                    commentItem.contentUserName = SPUtils.getInstance().getString("name");
                                    commentItem.content = content;
                                    //对人回复
                                    if (commentConfig.commentType == 1) {
                                        commentItem.replyUserName = commentConfig.replyUserName;
                                    }
                                    mView.updateReplyComment(commentConfig.tableType, commentConfig.circlePosition, commentItem);
                                }
                            }
                        }
                    });
        } else {
            ToastUtils.showShort("评论内容不能为空...");
        }

    }

    /**
     * @param commodityId 商品id
     * @param userId      用户id
     * @param shopId      店铺id
     * @param position    当前位置
     */
    @Override
    public void addCollect(String commodityId, String userId, String shopId, int position) {
        if (!mBaseFragment.isConnectNetWorkAndBindView()) {
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("commodityId", commodityId);
        map.put("userId", userId);
        map.put("shopId", shopId);
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.CIRCLE_ADD_COLLECT)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        if (!StringUtils.isEmpty(body)) {
                            InfoBean infoBean = GsonUtil.GsonToBean(body, InfoBean.class);
                            if (infoBean != null) {
                                int statusCode = infoBean.getStatusCode();
                                if (statusCode == Constants.SUCCESS_CODE) {
                                    mView.updateCollect(position, true);
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void requestQueryUserInfo(String userId) {
        if (!mBaseFragment.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.USER_QUERY)
                .params("id", userId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                User user = GsonUtil.GsonToBean(body, User.class);
                                if (user != null) {
                                    int statusCode = user.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        User.DataBean data = user.getData();
                                        if (data != null) {
                                            String headImg = data.getHeadImg();
                                            String byname = data.getName();
                                            String settingImg = data.getSettingImg();
                                            if (!StringUtils.isEmpty(headImg)) {
                                                mView.showHeadImg(headImg);
                                            }
                                            if (!StringUtils.isEmpty(byname)) {
                                                mView.showUsername(byname);
                                            }
                                            if (!StringUtils.isEmpty(settingImg)) {
                                                mView.showBgImg(settingImg);
                                            } else {
                                                mView.showDefaultImg();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
    }

    /**
     * @param localMedia 上传图片
     * @param type       1.更换背景  2.更换头像
     */
    @Override
    public void uploadPictures(LocalMedia localMedia, int type) {

        if (!mBaseFragment.isConnectNetWorkAndBindView()) {
            return;
        }

        String path;
        if (localMedia.isCut() && !localMedia.isCompressed()) {
            //裁剪过
            path = localMedia.getCutPath();
        } else if (localMedia.isCompressed() || (localMedia.isCut() && localMedia.isCompressed())) {
            // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
            path = localMedia.getCompressPath();
        } else {
            // 原图
            path = localMedia.getPath();
        }

        OkGo.<String>post(Api.CIRCLE_UPLOAD_PICTURES)
                .params("file", new File(path))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        if (!StringUtils.isEmpty(body)) {
                            CirclePhotoBean circlePhotoBean = GsonUtil.GsonToBean(body, CirclePhotoBean.class);
                            if (circlePhotoBean != null) {
                                if (circlePhotoBean.getStatusCode() == Constants.SUCCESS_CODE) {
                                    String userId = SPUtils.getInstance().getString("userId");
                                    String imageUrl = circlePhotoBean.getData();
                                    //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
                                    PictureFileUtils.deleteCacheDirFile(mBaseFragment.mBaseActivity);
                                    switch (type) {
                                        case 1://背景图
                                            mView.showBgImg(imageUrl);
                                            requestReplacePicture(userId, imageUrl, 1);
                                            break;
                                        case 2://头像
                                            mView.showHeadImg(imageUrl);
                                            requestReplacePicture(userId, imageUrl, 2);
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }
                        }
                    }

                });
    }

    /**
     * 更新用户信息
     *
     * @param userId   用户id
     * @param imageUrl 图片地址
     * @param type     1.背景图  2.头像
     */
    private void requestReplacePicture(String userId, String imageUrl, int type) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", Integer.parseInt(userId));
        switch (type) {
            case 1:
                map.put("settingImg", imageUrl);
                break;
            case 2:
                map.put("headImg", imageUrl);
                break;
            default:
                break;
        }
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.USER_UPDATE)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //                        String body = response.body();
                        //                        InfoBean infoBean = GsonUtil.GsonToBean(body, InfoBean.class);
                        //                        if (infoBean != null) {
                        //                            int statusCode = infoBean.getStatusCode();
                        //                            if (statusCode == Constants.SUCCESS_CODE) {
                        //
                        //
                        //                            }
                        //                        }
                    }
                });
    }

}
