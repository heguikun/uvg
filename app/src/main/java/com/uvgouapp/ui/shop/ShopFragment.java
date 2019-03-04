package com.uvgouapp.ui.shop;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.uvgouapp.R;
import com.uvgouapp.adapter.shop.ShopCategoryAdapter;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.base.BaseFragment;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.contract.shop.ShopContract;
import com.uvgouapp.model.shop.ShopCategoryBean;
import com.uvgouapp.presenter.shop.ShopPresenter;
import com.uvgouapp.ui.other.WebViewActivity;
import com.uvgouapp.view.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * - @Author:  ying
 * - @Time:  2018/12/23
 * - @Description:  商城
 */
public class ShopFragment extends BaseFragment<ShopPresenter> implements ShopContract.View {

    @BindView(R.id.et_search)
    AppCompatEditText mEtSearch;//商品搜索
    @BindView(R.id.tv_hot_search_one)
    AppCompatTextView mTvHotSearchOne;//热搜1
    @BindView(R.id.tv_hot_search_two)
    AppCompatTextView mTvHotSearchTwo;//热搜2
    @BindView(R.id.tv_hot_search_three)
    AppCompatTextView mTvHotSearchThree;//热搜3
    @BindView(R.id.tv_hot_search_four)
    AppCompatTextView mTvHotSearchFour;//热搜4
    @BindView(R.id.banner)
    Banner mBanner;//轮播图

    //-----------------限时抢购------------------
    @BindView(R.id.tv_hour)
    AppCompatTextView tvHour;//限时抢购    时
    @BindView(R.id.tv_minute)
    AppCompatTextView tvMinute;//限时抢购  分
    @BindView(R.id.tv_second)
    AppCompatTextView tvSecond;//限时抢购  秒
    @BindView(R.id.iv_buy_one)
    ImageView mIvBuyOne;
    @BindView(R.id.iv_buy_two)
    ImageView mIvBuyTwo;

    //---------------热销------------------------
    @BindView(R.id.iv_top_one)
    ImageView mIvTopOne;
    @BindView(R.id.iv_top_two)
    ImageView mIvTopTwo;

    //---------------新品--------------
    @BindView(R.id.iv_new_one)
    ImageView mIvNewOne;
    @BindView(R.id.iv_new_two)
    ImageView mIvNewTwo;

    //---------------国际-------------
    @BindView(R.id.iv_international_one)
    ImageView mIvInternationalOne;
    @BindView(R.id.iv_international_two)
    ImageView mIvInternationalTwo;

    @BindView(R.id.rv_shop_category)
    RecyclerView mRecyclerView;//商品分类列表
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;//刷新
    @BindView(R.id.nested_scrollview)
    NestedScrollView mNestedScrollView;

    //------------------商品分类---------------
    @BindView(R.id.tv_all)
    TextView mTvAll;//全部
    @BindView(R.id.tv_like)
    TextView mTvLike;//猜你喜欢
    @BindView(R.id.tv_global)
    TextView mTvGlobal;//全球
    @BindView(R.id.tv_good_goods)
    TextView mTvGoodGoods;//进口好货
    @BindView(R.id.tv_fashion)
    TextView mTvFashion; //时尚
    @BindView(R.id.tv_trend)
    TextView mTvTrend;//潮流好货
    @BindView(R.id.tv_infant)
    TextView mTvInfant; //母婴
    @BindView(R.id.tv_grand_reward)
    TextView mTvGrandReward;//母婴大赏
    @BindView(R.id.tv_live)
    TextView mTvLive; //生活
    @BindView(R.id.tv_enjoy)
    TextView mTvEnjoy;//享受生活
    @BindView(R.id.tv_men_wear)
    TextView mTvMenWear;//男装
    @BindView(R.id.tv_stylish_man)
    TextView mTvStylishMan;//时尚型男

    private int page = 1;//页码数

    private ShopCategoryAdapter mShopCategoryAdapter = null;

    @Override
    protected Object setLayout() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initView() {
        List<String> images = new ArrayList<>();
        mPresenter = new ShopPresenter(this);
        mPresenter.attachView(this);

        mShopCategoryAdapter = new ShopCategoryAdapter(R.layout.item_shop_category);

        images.add("https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/333.jpg");
        images.add("https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/sadfasfdsa.jpg");
        images.add("https://uwz.blob.core.chinacloudapi.cn/quickstartcontainer/222.jpg");
        //------------轮播图 设置属性-------------------
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        mBanner.setBannerAnimation(Transformer.DepthPage);
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(1500);
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.start();

        setTextViewColorAndBg(mTvAll, mTvLike);//默认选中全部
        //-------------------商品分类列表--------------------
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mBaseActivity, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mShopCategoryAdapter);
    }

    @Override
    protected void initData() {

        //mPresenter.requestShopLogin();//商城登录

        mPresenter.requestShopCategory("6", String.valueOf(page), "10");

    }

    @Override
    protected void initListener() {

        mShopCategoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShopCategoryBean.DataBean.RecordsBean recordsBean = mShopCategoryAdapter.getData().get(position);
                Intent intent = new Intent(mBaseActivity, WebViewActivity.class);
                intent.putExtra("URL", String.format(Locale.ENGLISH, Api.SHOP_CATEGORY_DETAILS, SPUtils.getInstance().getString("userId"), String.valueOf(recordsBean.getId())));
                startActivity(intent);
            }
        });

        //-----------------------刷新加载-------------------------------
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.requestShopCategory("1", String.valueOf(page), "10");
                mRefreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initData();
                mRefreshLayout.finishRefresh();
            }
        });
        //--------------------刷新加载-------------------------------

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {//静止
                    ImageLoaderUtil.resumeRequests(mBaseActivity);
                } else {
                    ImageLoaderUtil.pauseRequests(mBaseActivity);
                }
            }
        });
    }

    @Override
    public void setShopCategoryListData(List<ShopCategoryBean.DataBean.RecordsBean> listData) {
        if (page == 1) {
            mShopCategoryAdapter.setNewData(listData);
        } else {
            mShopCategoryAdapter.addData(listData);
            mShopCategoryAdapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.tv_scan, R.id.tv_member_code, R.id.tv_hot_search_one, R.id.tv_hot_search_two,
            R.id.tv_hot_search_three, R.id.tv_hot_search_four, R.id.iv_shopping_cart, R.id.iv_up, R.id.ll_all, R.id.ll_global,
            R.id.ll_fashion, R.id.ll_infant, R.id.ll_live, R.id.ll_men_wear, R.id.tv_one, R.id.tv_two, R.id.tv_three,
            R.id.tv_four, R.id.tv_five, R.id.tv_six, R.id.tv_seven, R.id.tv_eight, R.id.tv_nine, R.id.tv_ten,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_scan://扫一扫
                break;
            case R.id.tv_member_code://会员码
                break;
            case R.id.tv_hot_search_one://热搜1
                //ToastUtils.showShort("热搜1");
                break;
            case R.id.tv_hot_search_two://热搜2
                //ToastUtils.showShort("热搜2");
                break;
            case R.id.tv_hot_search_three://热搜3
                //ToastUtils.showShort("热搜3");
                break;
            case R.id.tv_hot_search_four://热搜4
                //ToastUtils.showShort("热搜4");
                break;
            case R.id.iv_shopping_cart://购物车
                Intent intent = new Intent(mBaseActivity, WebViewActivity.class);
                intent.putExtra("URL", String.format(Locale.ENGLISH, Api.SHOPPING_CART, SPUtils.getInstance().getString("userId")));
                startActivity(intent);
                break;
            case R.id.iv_up://回到顶部
                mNestedScrollView.fullScroll(View.FOCUS_UP);
                break;
            case R.id.ll_all://全部
                setResetTextViewColorAndBg();
                setTextViewColorAndBg(mTvAll, mTvLike);
                break;
            case R.id.ll_global://全球
                setResetTextViewColorAndBg();
                setTextViewColorAndBg(mTvGlobal, mTvGoodGoods);
                break;
            case R.id.ll_fashion://时尚
                setResetTextViewColorAndBg();
                setTextViewColorAndBg(mTvFashion, mTvTrend);
                break;
            case R.id.ll_infant://母婴
                setResetTextViewColorAndBg();
                setTextViewColorAndBg(mTvInfant, mTvGrandReward);
                break;
            case R.id.ll_live://生活
                setResetTextViewColorAndBg();
                setTextViewColorAndBg(mTvLive, mTvEnjoy);
                break;
            case R.id.ll_men_wear://男装
                setResetTextViewColorAndBg();
                setTextViewColorAndBg(mTvMenWear, mTvStylishMan);
                break;
            case R.id.tv_one://美妆护肤
                Intent intent1 = new Intent(mBaseActivity, WebViewActivity.class);
                intent1.putExtra("title", "美妆护肤");
                intent1.putExtra("URL", String.format(Locale.ENGLISH, Api.SHOP_CATEGORY, "1", SPUtils.getInstance().getString("userId")));
                startActivity(intent1);
                break;
            case R.id.tv_two://服装
                Intent intent2 = new Intent(mBaseActivity, WebViewActivity.class);
                intent2.putExtra("title", "服装");
                intent2.putExtra("URL", String.format(Locale.ENGLISH, Api.SHOP_CATEGORY, "2", SPUtils.getInstance().getString("userId")));
                startActivity(intent2);
                break;
            case R.id.tv_three://鞋靴
                Intent intent3 = new Intent(mBaseActivity, WebViewActivity.class);
                intent3.putExtra("title", "鞋靴");
                intent3.putExtra("URL", String.format(Locale.ENGLISH, Api.SHOP_CATEGORY, "3", SPUtils.getInstance().getString("userId")));
                startActivity(intent3);
                break;
            case R.id.tv_four://母婴
                Intent intent4 = new Intent(mBaseActivity, WebViewActivity.class);
                intent4.putExtra("title", "母婴");
                intent4.putExtra("URL", String.format(Locale.ENGLISH, Api.SHOP_CATEGORY, "4", SPUtils.getInstance().getString("userId")));
                startActivity(intent4);
                break;
            case R.id.tv_five://家电
                Intent intent5 = new Intent(mBaseActivity, WebViewActivity.class);
                intent5.putExtra("title", "家电");
                intent5.putExtra("URL", String.format(Locale.ENGLISH, Api.SHOP_CATEGORY, "5", SPUtils.getInstance().getString("userId")));
                startActivity(intent5);
                break;
            case R.id.tv_six://居家百货
                Intent intent6 = new Intent(mBaseActivity, WebViewActivity.class);
                intent6.putExtra("title", "居家百货");
                intent6.putExtra("URL", String.format(Locale.ENGLISH, Api.SHOP_CATEGORY, "6", SPUtils.getInstance().getString("userId")));
                startActivity(intent6);
                break;
            case R.id.tv_seven://食品
                Intent intent7 = new Intent(mBaseActivity, WebViewActivity.class);
                intent7.putExtra("title", "食品");
                intent7.putExtra("URL", String.format(Locale.ENGLISH, Api.SHOP_CATEGORY, "7", SPUtils.getInstance().getString("userId")));
                startActivity(intent7);
                break;
            case R.id.tv_eight://配饰
                Intent intent8 = new Intent(mBaseActivity, WebViewActivity.class);
                intent8.putExtra("title", "配饰");
                intent8.putExtra("URL", String.format(Locale.ENGLISH, Api.SHOP_CATEGORY, "8", SPUtils.getInstance().getString("userId")));
                startActivity(intent8);
                break;
            case R.id.tv_nine://手机数码
                Intent intent9 = new Intent(mBaseActivity, WebViewActivity.class);
                intent9.putExtra("title", "手机数码");
                intent9.putExtra("URL", String.format(Locale.ENGLISH, Api.SHOP_CATEGORY, "9", SPUtils.getInstance().getString("userId")));
                startActivity(intent9);
                break;
            case R.id.tv_ten://整车车品
                Intent intent10 = new Intent(mBaseActivity, WebViewActivity.class);
                intent10.putExtra("title", "整车车品");
                intent10.putExtra("URL", String.format(Locale.ENGLISH, Api.SHOP_CATEGORY, "10", SPUtils.getInstance().getString("userId")));
                startActivity(intent10);
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mBanner != null)
            mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null)
            mBanner.stopAutoPlay();
    }

    /**
     * @param textViewTop  上面文本
     * @param textViewDown 下面文本
     */
    private void setTextViewColorAndBg(TextView textViewTop, TextView textViewDown) {
        textViewTop.setTextColor(mBaseActivity.getResources().getColor(R.color.app_color));
        textViewDown.setTextColor(mBaseActivity.getResources().getColor(R.color.white));
        textViewDown.setBackground(mBaseActivity.getResources().getDrawable(R.drawable.shape_shop_bg_category));
    }

    /**
     * 重置文本颜色和背景
     */
    private void setResetTextViewColorAndBg() {
        mTvAll.setTextColor(mBaseActivity.getResources().getColor(R.color.color_1a1a1a));
        mTvGlobal.setTextColor(mBaseActivity.getResources().getColor(R.color.color_1a1a1a));
        mTvFashion.setTextColor(mBaseActivity.getResources().getColor(R.color.color_1a1a1a));
        mTvInfant.setTextColor(mBaseActivity.getResources().getColor(R.color.color_1a1a1a));
        mTvLive.setTextColor(mBaseActivity.getResources().getColor(R.color.color_1a1a1a));
        mTvMenWear.setTextColor(mBaseActivity.getResources().getColor(R.color.color_1a1a1a));

        mTvLike.setTextColor(mBaseActivity.getResources().getColor(R.color.color_999999));
        mTvLike.setBackgroundResource(R.color.transparent);
        mTvGoodGoods.setTextColor(mBaseActivity.getResources().getColor(R.color.color_999999));
        mTvGoodGoods.setBackgroundResource(R.color.transparent);
        mTvTrend.setTextColor(mBaseActivity.getResources().getColor(R.color.color_999999));
        mTvTrend.setBackgroundResource(R.color.transparent);
        mTvGrandReward.setTextColor(mBaseActivity.getResources().getColor(R.color.color_999999));
        mTvGrandReward.setBackgroundResource(R.color.transparent);
        mTvEnjoy.setTextColor(mBaseActivity.getResources().getColor(R.color.color_999999));
        mTvEnjoy.setBackgroundResource(R.color.transparent);
        mTvStylishMan.setTextColor(mBaseActivity.getResources().getColor(R.color.color_999999));
        mTvStylishMan.setBackgroundResource(R.color.transparent);
    }

}
