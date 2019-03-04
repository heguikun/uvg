package com.uvgouapp.ui.circle;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.uvgouapp.R;
import com.uvgouapp.adapter.user.ShopAdapter;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.contract.circle.CircleInformationContract;
import com.uvgouapp.dialog.user.UserStoreDialog;
import com.uvgouapp.model.user.ShopBean;
import com.uvgouapp.presenter.circle.CircleInformationPresenter;
import com.uvgouapp.ui.other.WebViewActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class CircleInformationActivity extends BaseActivity<CircleInformationPresenter> implements CircleInformationContract.View {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.sv_root)
    NestedScrollView mScrollView;
    @BindView(R.id.rl_title)
    RelativeLayout mRelativeLayoutTop;//顶部布局
    @BindView(R.id.tv_cancle)
    TextView mTvCancle;//取消
    @BindView(R.id.tv_ok)
    TextView mTvOk;//确认
    @BindView(R.id.iv_head_bg)
    ImageView mIvHeadBg;//背景图片
    @BindView(R.id.iv_head_portrait)
    ImageView mIvHeadPortrait;//头像
    @BindView(R.id.iv_follow)
    ImageView mIvFollow;//关注
    @BindView(R.id.tv_address)
    TextView mTvAddress;//地址
    @BindView(R.id.tv_name)
    TextView mTvName;//用户名
    @BindView(R.id.rl_head)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.ll_user_info)
    LinearLayout mLlUserInfo;//用户信息布局
    @BindView(R.id.tv_sex)
    TextView mTvSex;//性别
    @BindView(R.id.tv_date_of_birth)
    TextView mTvDateOfBirth;//出生年月
    @BindView(R.id.tv_constellation)
    TextView mTvConstellation;//星座
    @BindView(R.id.tv_affective_state)
    TextView mTvAffectiveState;//感情状态
    @BindView(R.id.tv_permanent_residence)
    TextView mTvPermanentResidence;//常住地
    @BindView(R.id.tv_occupation)
    TextView mTvOccupation;//职业
    @BindView(R.id.tv_height)
    TextView mTvHeight;//身高
    @BindView(R.id.tv_weight)
    TextView mTvWeight;//体重
    @BindView(R.id.ll_shop_info)
    LinearLayout mLlShopInfo;//店铺信息
    @BindView(R.id.tv_shop_name)
    TextView mTvShopName;//店铺名
    @BindView(R.id.tv_main_business)
    TextView mTvMainBusiness;//主营业务
    @BindView(R.id.tv_business_hours)
    TextView mTvBusinessHours;//营业时间
    @BindView(R.id.tv_detailed_address)
    TextView mTvDetailedAddress;//详细地址
    @BindView(R.id.tv_introduction)
    TextView mTvIntroduction;//简介
    @BindView(R.id.iv_one)
    ImageView mIvOne;//图片1
    @BindView(R.id.iv_two)
    ImageView mIvTwo;//图片2
    @BindView(R.id.iv_three)
    ImageView mIvThree;//图片3
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;//商品列表

    private ShopAdapter mShopAdapter = null;
    private boolean flag = false;//标记  true 复制  false  进入详情页
    private int sum = 0;//计数

    private int page = 1;
    private String userId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_circle_information;
    }

    @Override
    protected void initView() {
        mTvAddress.setVisibility(View.INVISIBLE);//隐藏定位不需要定位
        mPresenter = new CircleInformationPresenter(this);
        mPresenter.attachView(this);
        mShopAdapter = new ShopAdapter(R.layout.shop_item_recommend);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mShopAdapter);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getStringExtra("userId");
            mPresenter.requestQueryUserInfo(userId);
            mPresenter.requestQueryShopInfo(userId);
            mPresenter.requestQueryUserFollow(SPUtils.getInstance().getString("userId"), userId);
            mPresenter.requestQueryUserLiveDynamic(userId, String.valueOf(page), "10");
            mPresenter.requestQueryUserShop(userId, String.valueOf(page), "10");
            if (userId.equals(SPUtils.getInstance().getString("userId"))) {
                mIvFollow.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void initListener() {
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int height = mRelativeLayout.getHeight();
                if (scrollY > height) {//显示标题  改变背景色
                    mRelativeLayoutTop.setBackgroundColor(mContext.getResources().getColor(R.color.color_white));
                } else {
                    mRelativeLayoutTop.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
                }
            }
        });

        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.requestQueryUserShop(userId, String.valueOf(page), "10");
                mRefreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initData();
                mRefreshLayout.finishRefresh();
            }
        });

        mShopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (flag) {
                    //选中
                    TextView tvSelect = (TextView) mShopAdapter.getViewByPosition(mRecyclerView, position, R.id.tv_select_shop);
                    if (tvSelect != null) {
                        if (tvSelect.isSelected()) {
                            sum--;
                            tvSelect.setSelected(false);
                        } else {
                            sum++;
                            tvSelect.setSelected(true);
                        }
                        mTvOk.setText(String.format(Locale.ENGLISH, "确认(%d)", sum > 0 ? sum : 0));
                    }
                } else { //进入商品详情页
                    ShopBean.MapListBean mapListBean = mShopAdapter.getData().get(position);
                    Intent intent = new Intent(mContext, WebViewActivity.class);
                    intent.putExtra("URL", String.format(Locale.ENGLISH, Api.SHOP_CATEGORY_DETAILS, SPUtils.getInstance().getString("userId"), String.valueOf(mapListBean.getId())));
                    startActivity(intent);
                }
            }
        });
    }


    @OnClick({R.id.iv_head_portrait, R.id.ll_photo, R.id.tv_about_ta,
            R.id.tv_query_shop, R.id.tv_one_key_shop, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head_portrait://头像
                break;
            case R.id.ll_photo://淘友圈生活图片  查看他人淘友圈
                Intent intent = new Intent(mContext, CircleLookOtherInfoActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
                break;
            case R.id.tv_about_ta://关于ta
                if (mLlUserInfo.getVisibility() == View.GONE) {
                    mLlUserInfo.setVisibility(View.VISIBLE);
                } else {
                    mLlUserInfo.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_query_shop://查看店铺
                if (mLlShopInfo.getVisibility() == View.GONE) {
                    mLlShopInfo.setVisibility(View.VISIBLE);
                } else {
                    mLlShopInfo.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_one_key_shop://一键开店
                mPresenter.requestQueryMyShopInfo(userId);
                break;
            case R.id.iv_back://返回
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void showBgImage(String bgImage) {
        ImageLoaderUtil.into(mContext, bgImage, mIvHeadBg);
    }

    @Override
    public void showDefaultImg() {
        ImageLoaderUtil.into(mContext, R.drawable.tyq_bg, mIvHeadBg);
    }

    @Override
    public void showHeadImage(String headImage) {
        ImageLoaderUtil.into(mContext, headImage, mIvHeadPortrait);
    }

    @Override
    public void showUsername(String username) {
        mTvName.setText(username);
    }

    @Override
    public void showSex(boolean sex) {
        if (sex) {
            mTvSex.setText("男");
        } else {
            mTvSex.setText("女");
        }
    }

    @Override
    public void showDateOfBirth(String dateOfBirth) {
        mTvDateOfBirth.setText(dateOfBirth);
    }

    @Override
    public void showConstellation(String constellation) {
        mTvConstellation.setText(constellation);
    }

    @Override
    public void showEmotionalState(String emotionalState) {
        mTvAffectiveState.setText(emotionalState);
    }

    @Override
    public void showObode(String obode) {
        mTvPermanentResidence.setText(obode);
    }

    @Override
    public void showOccupation(String occupation) {
        mTvOccupation.setText(occupation);
    }

    @Override
    public void showStature(String stature) {
        mTvHeight.setText(stature);
    }

    @Override
    public void showWeight(String weight) {
        mTvWeight.setText(weight);
    }

    @Override
    public void showShopName(String shopName) {
        mTvShopName.setText(shopName);
    }

    @Override
    public void showMainOperation(String mainOperation) {
        mTvMainBusiness.setText(mainOperation);
    }

    @Override
    public void setShopListData(List<ShopBean.MapListBean> listData) {
        if (page == 1) {
            mShopAdapter.setNewData(listData);
        } else {
            mShopAdapter.addData(listData);
            mShopAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setCopyShopListData(List<ShopBean.MapListBean> listData) {
        mShopAdapter.getData().clear();
        mShopAdapter.addData(listData);
        mShopAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLiveImage1(List<String> listImage) {
        ImageLoaderUtil.into(mContext, listImage.get(0), mIvOne);
    }

    @Override
    public void showLiveImage2(List<String> listImage) {
        ImageLoaderUtil.into(mContext, listImage.get(0), mIvOne);
        ImageLoaderUtil.into(mContext, listImage.get(1), mIvTwo);
    }

    @Override
    public void showLiveImage3(List<String> listImage) {
        ImageLoaderUtil.into(mContext, listImage.get(0), mIvOne);
        ImageLoaderUtil.into(mContext, listImage.get(1), mIvTwo);
        ImageLoaderUtil.into(mContext, listImage.get(2), mIvThree);
    }

    @Override
    public void applyStore() {
        UserStoreDialog userStoreDialog = new UserStoreDialog(this);
        userStoreDialog.showPopupWindow();
    }

    @Override
    public void onSuccess() {
        //显示确认取消改变颜色
        if (mShopAdapter.getData().size() > 0) {
            mTvCancle.setVisibility(View.VISIBLE);
            mTvOk.setVisibility(View.VISIBLE);
            flag = true;//标记是否复制商品
            mShopAdapter.setFlag(true);//显示按钮

            mTvOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> list = new ArrayList<>();//商品id集合
                    int size = mShopAdapter.getData().size();
                    //---------------遍历获取选中的商品-------------
                    for (int i = 0; i < size; i++) {
                        TextView tvSelelct = (TextView) mShopAdapter.getViewByPosition(mRecyclerView, i, R.id.tv_select_shop);
                        if (tvSelelct != null) {
                            if (tvSelelct.isSelected()) {
                                int id = mShopAdapter.getData().get(i).getId();
                                list.add(String.valueOf(id));
                            }
                        }

                    }
                    //---------------一键开店---------------
                    mPresenter.requestOneKeyShop(list, 1,
                            SPUtils.getInstance().getString("userId"), SPUtils.getInstance().getString("userId"), SPUtils.getInstance().getString("shopId"));
                }
            });

            mTvCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag = false;
                    sum = 0;//归零
                    mShopAdapter.setFlag(false);//隐藏按钮
                    mTvCancle.setVisibility(View.INVISIBLE);
                    mTvOk.setVisibility(View.INVISIBLE);
                    mTvOk.setText("确认(0)");
                    mRefreshLayout.autoRefresh();
                }
            });

        } else {
            flag = false;
            sum = 0;//归零
            mShopAdapter.setFlag(false);//隐藏按钮
            mTvCancle.setVisibility(View.INVISIBLE);
            mTvOk.setVisibility(View.INVISIBLE);
            mTvOk.setText("确认(0)");
            ToastUtils.showShort("该店铺没有商品");
        }
    }

    @Override
    public void onSuccess(String msg) {
        ToastUtils.showShort(msg);
        flag = false;
        sum = 0;//归零
        mShopAdapter.setFlag(false);//隐藏按钮
        mTvCancle.setVisibility(View.INVISIBLE);
        mTvOk.setVisibility(View.INVISIBLE);
        mTvOk.setText("确认(0)");
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void AddFollow() {
        mIvFollow.setImageResource(R.drawable.tyq_cktrxx_tjhy);
        mIvFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.requestFollow(userId, SPUtils.getInstance().getString("userId"));
            }
        });
    }

    @Override
    public void alreadyFollow() {
        mIvFollow.setImageResource(R.drawable.tyq_cktrxx_ygz);
    }

    @Override
    public void followSuccess() {
        mIvFollow.setImageResource(R.drawable.tyq_cktrxx_ygz);
    }
}
