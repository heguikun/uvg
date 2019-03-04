package com.uvgouapp.adapter.circle;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.uvgouapp.R;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.common.util.WxShareAndLoginUtil;
import com.uvgouapp.dialog.circle.CircleAllCollectionDialog;
import com.uvgouapp.dialog.circle.CircleAllCommentDialog;
import com.uvgouapp.dialog.circle.CirclePhotoDialog;
import com.uvgouapp.home.HomeActivity;
import com.uvgouapp.model.circle.CircleAllBean;
import com.uvgouapp.model.circle.CommentConfig;
import com.uvgouapp.model.circle.CommentItem;
import com.uvgouapp.model.circle.FavortConfig;
import com.uvgouapp.model.circle.FavortItem;
import com.uvgouapp.presenter.circle.CirclePresenter;
import com.uvgouapp.ui.circle.CircleInformationActivity;
import com.uvgouapp.ui.other.WebViewActivity;
import com.uvgouapp.view.CommentListView;
import com.uvgouapp.view.PraiseListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * - @Author:  ying
 * - @Time:  2019/2/20
 * - @Description:
 */
public class CircleAdapter extends BaseMultiItemQuickAdapter<CircleAllBean, BaseViewHolder> {

    private static final int HEADVIEW_SIZE = 1; //减去头布局

    private CirclePresenter presenter = null;

    private HomeActivity mHomeActivity = null;

    public CircleAdapter(List<CircleAllBean> data) {
        super(data);
        addItemType(CircleAllBean.TYPE_SHOP, R.layout.item_type_shop);
        addItemType(CircleAllBean.TYPE_LIVE, R.layout.item_type_live);
    }

    public void setPresenter(CirclePresenter presenter) {
        this.presenter = presenter;
    }

    public void setHomeActivity(HomeActivity homeActivity) {
        this.mHomeActivity = homeActivity;
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        ImageView ivHeadShop = holder.getView(R.id.iv_shop_head);
        if (ivHeadShop != null) {
            ImageLoaderUtil.clearImageView(mContext, ivHeadShop);
            ImageLoaderUtil.GuideClearMemory(mContext);
        }

        ImageView ivLiveHead = holder.getView(R.id.iv_live_head);
        if (ivLiveHead != null) {
            ImageLoaderUtil.clearImageView(mContext, ivLiveHead);
            ImageLoaderUtil.GuideClearMemory(mContext);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, CircleAllBean item) {

        switch (helper.getItemViewType()) {
            //---------------------------------商品--------------------------------------
            case CircleAllBean.TYPE_SHOP:
                //-------------昵称  头像 商品名称,价格,描述,详情--------------
                TextView tvNameShop = helper.getView(R.id.tv_shop_nickname);
                ImageView ivHeadShop = helper.getView(R.id.iv_shop_head);
                TextView tvShopName = helper.getView(R.id.tv_shop_name);
                TextView tvShopPrice = helper.getView(R.id.tv_shop_price);
                TextView tvShopDescribe = helper.getView(R.id.tv_shop_describe);
                TextView tvShopDetails = helper.getView(R.id.tv_shop_details);
                TextView tvShopTime = helper.getView(R.id.tv_shop_time);
                TextView tvGiveShop = helper.getView(R.id.tv_shop_give);
                LinearLayout digCommentBody = helper.getView(R.id.digCommentBody);
                PraiseListView praiseListView = helper.getView(R.id.praiseListView);
                CommentListView commentList = helper.getView(R.id.commentList);
                View digLine = helper.getView(R.id.lin_dig);
                RecyclerView rvShop = helper.getView(R.id.rv_shop);
                ImageView ivCollection = helper.getView(R.id.iv_shop_collection);
                TextView tvShopPurchase = helper.getView(R.id.tv_shop_purchase);

                String userNameShop = item.userName;
                String userHeadImgShop = item.headImg;
                String shopName = item.commodityName;
                double shopPrice = item.retailPrice;
                String shopDescribe = item.commodityDescribe;
                long shopCreateTime = item.createTime;

                if (!StringUtils.isEmpty(userNameShop)) {
                    tvNameShop.setText(userNameShop);
                }
                if (!StringUtils.isEmpty(userHeadImgShop)) {
                    ImageLoaderUtil.into(mContext, userHeadImgShop, ivHeadShop);
                }
                if (!StringUtils.isEmpty(shopName)) {
                    tvShopName.setText(shopName);
                }
                if (shopPrice > 0) {
                    tvShopPrice.setText(String.format(Locale.ENGLISH, "价格:%.2f", shopPrice));
                }
                if (!StringUtils.isEmpty(shopDescribe)) {
                    tvShopDescribe.setText(EncodeUtils.urlDecode(shopDescribe, "UTF-8"));
                    tvShopDescribe.post(new Runnable() {
                        @Override
                        public void run() {
                            int lineCount = tvShopDescribe.getLineCount();
                            //---------详情显示和隐藏---------
                            tvShopDetails.setVisibility(lineCount > 4 ? View.VISIBLE : View.GONE);
                        }
                    });
                }
                if (shopCreateTime > 0) {
                    String date = TimeUtils.millis2String(shopCreateTime, new SimpleDateFormat("MM-dd", Locale.ENGLISH));
                    tvShopTime.setText(date);
                }

                //---------------------图片以及点击事件----------------
                String imgUrlShop = item.imgUrl;
                List<String> listShop = new ArrayList<>();
                if (!StringUtils.isEmpty(imgUrlShop)) {
                    String[] split = imgUrlShop.split(",");
                    listShop = Arrays.asList(split);
                }

                CirclePhotoAdapter photoAdapterShop = new CirclePhotoAdapter(R.layout.item_shop_photo, listShop);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
                rvShop.setLayoutManager(gridLayoutManager);
                rvShop.setAdapter(photoAdapterShop);

                //----------------------点击看图片------------------
                photoAdapterShop.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        List<String> list = photoAdapterShop.getData();
                        CirclePhotoDialog circlePhotoDialog = new CirclePhotoDialog(mContext, ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight(), list, position);
                        circlePhotoDialog.showPopupWindow();
                    }
                });

                //------------------------收藏--------------------
                if (item.userId.equals(SPUtils.getInstance().getString("userId"))) {
                    ivCollection.setVisibility(View.INVISIBLE);
                } else {
                    ivCollection.setVisibility(View.VISIBLE);
                }

                Object collect = item.collect;
                if (collect != null) {
                    ivCollection.setBackground(mContext.getResources().getDrawable(R.drawable.item_full_follow));
                } else {
                    ivCollection.setBackground(mContext.getResources().getDrawable(R.drawable.item_follow));
                }

                ivCollection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Object collect = item.collect;
                        if (collect != null) {
                            ivCollection.setBackground(mContext.getResources().getDrawable(R.drawable.item_full_follow));
                            ToastUtils.showShort("已经收藏成功");
                        } else {
                            if (presenter != null) {
                                String commodityId = String.valueOf(item.tableId);
                                String commodityShopId = item.commodityShopId;
                                CircleAllCollectionDialog circleCollectionDialog = new CircleAllCollectionDialog(mContext, commodityId, commodityShopId, collect, helper.getLayoutPosition() - HEADVIEW_SIZE, presenter);
                                circleCollectionDialog.showPopupWindow();
                            }
                        }
                    }
                });

                //---------------------------分享------------------------
                helper.getView(R.id.iv_shop_share).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WxShareAndLoginUtil.WxUrlShare(mContext, String.format(Locale.ENGLISH, Api.SHOP_CATEGORY_DETAILS, SPUtils.getInstance().getString("userId"), item.tableId), item.commodityName, EncodeUtils.urlDecode(item.commodityDescribe, "UTF-8"), WxShareAndLoginUtil.WECHAT_FRIEND);
                    }
                });

                //--------------------点赞------评论-------------------
                Object thumbsShop = item.thumbs;
                //判断是否点赞
                if (thumbsShop != null) {//是
                    tvGiveShop.setSelected(true);
                } else {//否
                    tvGiveShop.setSelected(false);
                }

                boolean hasFavort = item.hasFavort();
                boolean hasComment = item.hasComment();
                List<FavortItem> favorters = item.favorters;
                List<CommentItem> comments = item.comments;

                if (hasFavort || hasComment) {
                    digCommentBody.setVisibility(View.VISIBLE);
                    if (hasFavort) {//处理点赞列表
                        praiseListView.setDatas(favorters);
                        praiseListView.setVisibility(View.VISIBLE);
                        praiseListView.setOnItemClickListener(new PraiseListView.OnItemClickListener() {
                            @Override
                            public void onClick(int position) {
                                //-------------------进入个人详情页-----------------
                                FavortItem favortItem = favorters.get(position);
                                Intent intent = new Intent(mContext, CircleInformationActivity.class);
                                intent.putExtra("userId", favortItem.thumbsUserId);
                                mContext.startActivity(intent);
                            }
                        });
                    } else {
                        praiseListView.setVisibility(View.GONE);
                    }
                    if (hasComment) {//处理评论列表
                        commentList.setDatas(comments);
                        commentList.setVisibility(View.VISIBLE);
                        //----------------点击回复商品评论--------------
                        commentList.setOnItemClickListener(new CommentListView.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                if (presenter != null) {
                                    //---------自己不能回复自己----------
                                    if (SPUtils.getInstance().getString("userId").equals(comments.get(position).contentUser)) {
                                        return;
                                    }
                                    CommentConfig commentConfig = new CommentConfig();
                                    commentConfig.commentType = 1;//对人回复
                                    commentConfig.tableType = 2;//商品
                                    commentConfig.tableId = item.tableId;
                                    commentConfig.contentUser = SPUtils.getInstance().getString("userId");
                                    commentConfig.replyUser = comments.get(position).contentUser;
                                    commentConfig.replyUserName = comments.get(position).contentUserName;
                                    commentConfig.circlePosition = helper.getLayoutPosition() - HEADVIEW_SIZE;
                                    CircleAllCommentDialog circleAllCommentDialog = new CircleAllCommentDialog(mContext, presenter, commentConfig);
                                    circleAllCommentDialog.showPopupWindow();
                                }
                            }
                        });
                    } else {
                        commentList.setVisibility(View.GONE);
                    }
                } else {
                    digCommentBody.setVisibility(View.GONE);
                }
                digLine.setVisibility(hasFavort && hasComment ? View.VISIBLE : View.GONE);
                //------------------------头像  详情  购买  联系  点赞  评论  秀场  收藏----------------------------
                helper.getView(R.id.iv_shop_head).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userId = item.userId;
                        Intent intent = new Intent(mContext, CircleInformationActivity.class);
                        intent.putExtra("userId", userId);
                        mContext.startActivity(intent);
                    }
                });
                //--------------------------购买------------------------
                if (item.userId.equals(SPUtils.getInstance().getString("userId"))) {
                    tvShopPurchase.setVisibility(View.INVISIBLE);
                } else {
                    tvShopPurchase.setVisibility(View.VISIBLE);
                }
                tvShopPurchase.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);
                        intent.putExtra("URL", String.format(Locale.ENGLISH, Api.SHOP_CATEGORY_DETAILS, SPUtils.getInstance().getString("userId"), String.valueOf(item.tableId)));
                        mContext.startActivity(intent);
                    }
                });
                //--------------------------商品点赞--------------------------
                tvGiveShop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Object thumbsShop = item.thumbs;
                        if (thumbsShop != null) {//有
                            tvGiveShop.setSelected(true);
                            ToastUtils.showShort("已经点赞成功");
                        } else {//否
                            if (presenter != null) {
                                FavortConfig favortConfig = new FavortConfig();
                                favortConfig.itemType = 0;//商品
                                favortConfig.isThumbsId = String.valueOf(item.tableId);
                                favortConfig.thumbsType = Constants.SHOP_CIRCLE;
                                favortConfig.circlePosition = helper.getLayoutPosition() - HEADVIEW_SIZE;
                                presenter.requestGive(favortConfig, SPUtils.getInstance().getString("userId"));
                            }
                        }
                    }
                });
                //---------------------添加商品评论--------------------
                helper.getView(R.id.tv_shop_comment).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (presenter != null) {
                            CommentConfig commentConfig = new CommentConfig();
                            commentConfig.commentType = 0;//普通回复
                            commentConfig.tableType = 2;//商品
                            commentConfig.tableId = item.tableId;
                            commentConfig.contentUser = SPUtils.getInstance().getString("userId");
                            commentConfig.circlePosition = helper.getLayoutPosition() - HEADVIEW_SIZE;
                            CircleAllCommentDialog circleAllCommentDialog = new CircleAllCommentDialog(mContext, presenter, commentConfig);
                            circleAllCommentDialog.showPopupWindow();
                        }
                    }
                });
                //-----------------进入秀场-------------
                helper.getView(R.id.tv_shop_show).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mHomeActivity != null) {
                            mHomeActivity.getViewPager().setCurrentItem(1);
                            mHomeActivity.getCommonTabLayout().setCurrentTab(1);
                        }
                    }
                });
                break;
            //--------------------------------生活-----------------------------
            case CircleAllBean.TYPE_LIVE:
                //----------------头像,用户名,内容,日期 ------------------------
                ImageView ivLiveHead = helper.getView(R.id.iv_live_head);
                TextView tvLiveName = helper.getView(R.id.tv_live_nickname);
                TextView tvLiveContent = helper.getView(R.id.tv_live_content);
                TextView tvLiveTime = helper.getView(R.id.tv_live_time);
                TextView tvGiveLive = helper.getView(R.id.tv_live_give);
                LinearLayout digCommentBodyLive = helper.getView(R.id.digCommentBody);
                PraiseListView praiseListViewLive = helper.getView(R.id.praiseListView);
                CommentListView commentListLive = helper.getView(R.id.commentList);
                View digLineLive = helper.getView(R.id.lin_dig);

                String userName = item.userName;
                String userHeadImg = item.headImg;
                String contentLive = item.content;
                long createTimeLive = item.createTime;

                if (!StringUtils.isEmpty(userHeadImg)) {
                    ImageLoaderUtil.into(mContext, userHeadImg, ivLiveHead);
                }
                if (!StringUtils.isEmpty(userName)) {
                    tvLiveName.setText(userName);
                }
                if (!StringUtils.isEmpty(contentLive)) {
                    tvLiveContent.setText(EncodeUtils.urlDecode(contentLive, "UTF-8"));
                }
                if (createTimeLive > 0) {
                    String date = TimeUtils.millis2String(createTimeLive, new SimpleDateFormat("MM-dd", Locale.ENGLISH));
                    tvLiveTime.setText(date);
                }

                //---------------------图片以及点击事件----------------
                String imgUrlLive = item.imgUrl;
                List<String> listLive = new ArrayList<>();
                if (!StringUtils.isEmpty(imgUrlLive)) {
                    String[] split = imgUrlLive.split(",");
                    listLive = Arrays.asList(split);
                }
                RecyclerView rvLive = helper.getView(R.id.rv_live);
                CirclePhotoAdapter photoAdapterLive = new CirclePhotoAdapter(R.layout.item_shop_photo, listLive);
                GridLayoutManager gridLayoutManagerLive = new GridLayoutManager(mContext, 3);
                rvLive.setLayoutManager(gridLayoutManagerLive);
                rvLive.setAdapter(photoAdapterLive);

                //-------------------点击看图片------------------
                photoAdapterLive.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        List<String> list = photoAdapterLive.getData();
                        CirclePhotoDialog circlePhotoDialog = new CirclePhotoDialog(mContext, ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight(), list, position);
                        circlePhotoDialog.showPopupWindow();
                    }
                });

                //-------------------------分享-----------------------
                helper.getView(R.id.iv_live_share).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WxShareAndLoginUtil.WxUrlShare(mContext, String.format(Locale.ENGLISH, Api.SHOP_CATEGORY_DETAILS, SPUtils.getInstance().getString("userId"), item.tableId), "美好生活", EncodeUtils.urlDecode(item.content, "UTF-8"), WxShareAndLoginUtil.WECHAT_FRIEND);
                    }
                });

                //--------------------点赞--------------评论---------------
                Object thumbs = item.thumbs;
                if (thumbs != null) {
                    tvGiveLive.setSelected(true);
                } else {
                    tvGiveLive.setSelected(false);
                }
                boolean hasFavortLive = item.hasFavort();
                boolean hasCommentLive = item.hasComment();
                List<FavortItem> favortersLive = item.favorters;
                List<CommentItem> commentsLive = item.comments;

                if (hasFavortLive || hasCommentLive) {
                    digCommentBodyLive.setVisibility(View.VISIBLE);
                    if (hasFavortLive) {//处理点赞列表
                        praiseListViewLive.setDatas(favortersLive);
                        praiseListViewLive.setVisibility(View.VISIBLE);

                        praiseListViewLive.setOnItemClickListener(new PraiseListView.OnItemClickListener() {
                            @Override
                            public void onClick(int position) {
                                //----------------进入个人详情页---------------------
                                FavortItem favortItem = favortersLive.get(position);
                                Intent intent = new Intent(mContext, CircleInformationActivity.class);
                                intent.putExtra("userId", favortItem.thumbsUserId);
                                mContext.startActivity(intent);
                            }
                        });
                    } else {
                        praiseListViewLive.setVisibility(View.GONE);
                    }
                    if (hasCommentLive) {//处理评论列表
                        commentListLive.setDatas(commentsLive);
                        commentListLive.setVisibility(View.VISIBLE);
                        commentListLive.setOnItemClickListener(new CommentListView.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                //----------------自己不能回复自己-------------------
                                if (SPUtils.getInstance().getString("userId").equals(commentsLive.get(position).contentUser)) {
                                    return;
                                }
                                CommentConfig commentConfig = new CommentConfig();
                                commentConfig.commentType = 1;//对人回复
                                commentConfig.tableType = 1;//生活
                                commentConfig.tableId = item.tableId;
                                commentConfig.contentUser = SPUtils.getInstance().getString("userId");
                                commentConfig.replyUser = commentsLive.get(position).contentUser;
                                commentConfig.replyUserName = commentsLive.get(position).contentUserName;
                                commentConfig.circlePosition = helper.getLayoutPosition() - HEADVIEW_SIZE;
                                CircleAllCommentDialog circleAllCommentDialog = new CircleAllCommentDialog(mContext, presenter, commentConfig);
                                circleAllCommentDialog.showPopupWindow();
                            }
                        });
                    } else {
                        commentListLive.setVisibility(View.GONE);
                    }
                } else {
                    digCommentBodyLive.setVisibility(View.GONE);
                }
                digLineLive.setVisibility(hasFavortLive && hasCommentLive ? View.VISIBLE : View.GONE);
                //-----------------------头像   评论   点赞------------------------------
                helper.getView(R.id.iv_live_head).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userId = item.userId;
                        Intent intent = new Intent(mContext, CircleInformationActivity.class);
                        intent.putExtra("userId", userId);
                        mContext.startActivity(intent);
                    }
                });
                //---------------------添加生活评论--------------------
                helper.getView(R.id.tv_live_comment).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (presenter != null) {
                            CommentConfig commentConfig = new CommentConfig();
                            commentConfig.commentType = 0;//普通回复
                            commentConfig.tableType = 1;//生活
                            commentConfig.tableId = item.tableId;
                            commentConfig.contentUser = SPUtils.getInstance().getString("userId");
                            commentConfig.circlePosition = helper.getLayoutPosition() - HEADVIEW_SIZE;
                            CircleAllCommentDialog circleAllCommentDialog = new CircleAllCommentDialog(mContext, presenter, commentConfig);
                            circleAllCommentDialog.showPopupWindow();
                        }
                    }
                });
                //----------------------添加生活点赞-------------------
                tvGiveLive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Object thumbsShop = item.thumbs;
                        if (thumbsShop != null) {//有
                            tvGiveLive.setSelected(true);
                            ToastUtils.showShort("已经点赞成功");
                        } else {//否
                            if (presenter != null) {
                                //----------生活点赞配置信息--------------
                                FavortConfig favortConfig = new FavortConfig();
                                favortConfig.itemType = 1;//生活
                                favortConfig.isThumbsId = String.valueOf(item.tableId);
                                favortConfig.thumbsType = Constants.LIFE_CIRCLE;
                                favortConfig.circlePosition = helper.getLayoutPosition() - HEADVIEW_SIZE;
                                presenter.requestGive(favortConfig, SPUtils.getInstance().getString("userId"));
                            }
                        }
                    }
                });
                break;
            default:
                break;
        }
    }
}
