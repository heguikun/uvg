package com.uvgouapp.ui.circle;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.uvgouapp.R;
import com.uvgouapp.adapter.circle.CirclePhotoAdapter;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.dialog.circle.CirclePhotoDialog;
import com.uvgouapp.model.circle.CircleLookAllBean;
import com.uvgouapp.view.CommentListView;
import com.uvgouapp.view.PraiseListView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * - @Author:  ying
 * - @Time:  2019/3/2
 * - @Description:  详情
 */
public class CircleDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_head)
    ImageView mIvHead;//头像
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;//用户名
    @BindView(R.id.tv_shop_name)
    TextView mTvShopName;//商品名称
    @BindView(R.id.tv_shop_price)
    TextView mTvShopPrice;//商品价格
    @BindView(R.id.tv_details)
    TextView mTvDetails;//详情
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;//图片列表
    @BindView(R.id.tv_time)
    TextView mTvTime;//时间
    @BindView(R.id.iv_shop_collection)
    ImageView mIvShopCollection;//收藏
    @BindView(R.id.tv_shop_give)
    TextView mTvShopGive;//商品点赞
    @BindView(R.id.ll_shop)
    LinearLayout mLlShop;//商品布局
    @BindView(R.id.tv_live_give)
    TextView mTvLiveGive;//生活点赞
    @BindView(R.id.rl_live)
    RelativeLayout mRlLive;//生活布局
    //---------------------点赞,评论相关内容信息-------------
    @BindView(R.id.praiseListView)
    PraiseListView mPraiseListView;
    @BindView(R.id.lin_dig)
    View mLinDig;
    @BindView(R.id.commentList)
    CommentListView mCommentList;
    @BindView(R.id.digCommentBody)
    LinearLayout mDigCommentBody;

    private CirclePhotoAdapter mPhotoAdapterShop = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            CircleLookAllBean circleLookAllBean = (CircleLookAllBean) intent.getSerializableExtra("CircleLookAllBean");
            switch (circleLookAllBean.itemType) {
                case CircleLookAllBean.TYPE_SHOP://商品
                    mTvShopName.setVisibility(View.VISIBLE);
                    mTvShopPrice.setVisibility(View.VISIBLE);
                    mLlShop.setVisibility(View.VISIBLE);
                    mRlLive.setVisibility(View.GONE);
                    mTvShopName.setText(circleLookAllBean.commodityName);
                    mTvShopPrice.setText(String.format(Locale.ENGLISH, "%.2f", circleLookAllBean.retailPrice));
                    mTvDetails.setText(EncodeUtils.urlDecode(circleLookAllBean.commodityDescribe, "UTF-8"));
                    break;
                case CircleLookAllBean.TYPE_LIVE://生活
                    mTvShopName.setVisibility(View.GONE);
                    mTvShopPrice.setVisibility(View.GONE);
                    mLlShop.setVisibility(View.GONE);
                    mRlLive.setVisibility(View.VISIBLE);
                    mTvDetails.setText(EncodeUtils.urlDecode(circleLookAllBean.content, "UTF-8"));
                    break;
                default:
                    break;
            }

            //-----------------图片--------------------------
            String imgUrl = circleLookAllBean.imgUrl;
            String[] split = imgUrl.split(",");
            List<String> list = Arrays.asList(split);

            mPhotoAdapterShop = new CirclePhotoAdapter(R.layout.item_shop_photo, list);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
            mRecyclerView.setLayoutManager(gridLayoutManager);
            mRecyclerView.setAdapter(mPhotoAdapterShop);

        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mPhotoAdapterShop.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<String> list = mPhotoAdapterShop.getData();
                CirclePhotoDialog circlePhotoDialog = new CirclePhotoDialog(mContext, ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight(), list, position);
                circlePhotoDialog.showPopupWindow();
            }
        });

    }

    @OnClick({R.id.iv_back, R.id.iv_shop_collection, R.id.iv_share, R.id.tv_shop_purchase, R.id.tv_shop_contact,
            R.id.tv_shop_give, R.id.tv_shop_comment, R.id.tv_shop_show, R.id.tv_live_comment, R.id.tv_live_give})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.iv_shop_collection://收藏
                break;
            case R.id.iv_share://分享

                break;
            case R.id.tv_shop_purchase://购买
                break;
            case R.id.tv_shop_contact://联系
                break;
            case R.id.tv_shop_give://商品点赞
                break;
            case R.id.tv_shop_comment://商品评论
                break;
            case R.id.tv_shop_show://商品秀场
                break;
            case R.id.tv_live_comment://生活评论
                break;
            case R.id.tv_live_give://生活点赞
                break;
            default:
                break;
        }
    }
}
