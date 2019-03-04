package com.uvgouapp.ui.show;

import android.content.res.TypedArray;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.uvgouapp.R;
import com.uvgouapp.adapter.show.ShowOnlineAdapter;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.contract.show.ShowOnlineContract;
import com.uvgouapp.dialog.show.ShowRocketDialog;
import com.uvgouapp.model.show.ShowOnlineBean;
import com.uvgouapp.presenter.show.ShowOnlinePresenter;
import com.uvgouapp.view.FrameAnimation;

import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ShowOnlineActivity extends BaseActivity<ShowOnlinePresenter> implements ShowOnlineContract.View {

    @BindView(R.id.iv_bg_rocket)
    ImageView mIvBgRocket;//火箭图片动画
    @BindView(R.id.iv_rocket)
    ImageView mIvRocket;//火箭
    @BindView(R.id.iv_head)
    CircleImageView mIvHead;//头像
    @BindView(R.id.tv_rocket)
    TextView mTvRocket;//火箭数量
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private ShowOnlineAdapter mShowOnlineAdapter = null;

    private Timer mTimer = new Timer();

    private int page = 1;
    private StaggeredGridLayoutManager mManager = null;

    private AMapLocationClient mLocationClient = null;

    private double latitude, longitude;//纬度  经度

    private ShowRocketDialog mShowRocketDialog = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_online;
    }

    @Override
    protected void initView() {
        mPresenter = new ShowOnlinePresenter(this);
        mPresenter.attachView(this);
        mShowOnlineAdapter = new ShowOnlineAdapter(R.layout.item_show_online);
        mManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mShowOnlineAdapter);
        mShowOnlineAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);//动画

    }

    @Override
    protected void initData() {
        initLocation();
        String userId = SPUtils.getInstance().getString("userId");
        mPresenter.requestRocketNumber(userId);
        mPresenter.requestQueryUserInfo(userId);
        mPresenter.requestUserOnline(String.valueOf(longitude), String.valueOf(latitude), "", String.valueOf(page), "10");
    }

    @Override
    protected void initListener() {
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@Nullable RefreshLayout refreshLayout) {
                page++;
                mPresenter.requestUserOnline(String.valueOf(longitude), String.valueOf(latitude), "", String.valueOf(page), "10");
                mRefreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@Nullable RefreshLayout refreshLayout) {
                page = 1;
                initData();
                mRefreshLayout.finishRefresh();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mManager.invalidateSpanAssignments();    //防止item 交换位置
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {//静止
                    ImageLoaderUtil.resumeRequests(mContext);
                } else {
                    ImageLoaderUtil.pauseRequests(mContext);
                }
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_rocket})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.tv_rocket://火箭
                int rocketNumber = Integer.parseInt(mTvRocket.getText().toString().trim());  //火箭数量
                if (rocketNumber > 0) {
                    String userId = SPUtils.getInstance().getString("userId");
                    mPresenter.requestRocketTop(userId);
                } else {
                    mShowRocketDialog = new ShowRocketDialog(this);
                    mShowRocketDialog.showPopupWindow();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setOnlineData(List<ShowOnlineBean.MapListBean> data) {
        if (page == 1) {
            mShowOnlineAdapter.setNewData(data);
        } else {
            mShowOnlineAdapter.addData(data);
            mShowOnlineAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showRocketNumber(int sumNumber) {
        mTvRocket.setText(String.format(Locale.ENGLISH, "%d", sumNumber));
    }

    @Override
    public void showUserHeadImg(String headImg) {
        ImageLoaderUtil.into(mContext, headImg, mIvHead);
    }

    @Override
    public void onSuccess() {
        //火箭动画
        rocketAnimation();
    }

    @Override
    public void onFail(String msg) {
        ToastUtils.showShort(msg);
    }

    /**
     * 火箭动画
     */
    private void rocketAnimation() {
        FrameAnimation frameAnimation = new FrameAnimation(mIvBgRocket, getRes(), 0, false);
        frameAnimation.setAnimationListener(new FrameAnimation.AnimationListener() {
            @Override
            public void onAnimationStart() {
                //-----------------------加载头像----------------
                mIvHead.setVisibility(View.VISIBLE);
                mIvRocket.setVisibility(View.VISIBLE);
                //动画集合   渐显  平移
                AnimationSet animationSet = new AnimationSet(true);
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                TranslateAnimation translateAnimationHead = new TranslateAnimation(0, 0, 20, -ScreenUtils.getScreenHeight() / 4);
                animationSet.setDuration(1000);
                //设置不回到原来位置
                animationSet.setFillEnabled(true);
                animationSet.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                animationSet.addAnimation(translateAnimationHead);
                mIvHead.startAnimation(animationSet);

                if (mTimer != null) {
                    mTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //平移动画
                                    TranslateAnimation translateAnimationHead = new TranslateAnimation(0, 0, ScreenUtils.getScreenHeight() / 2.0f, -ScreenUtils.getScreenHeight() / 1.6f);
                                    translateAnimationHead.setDuration(500);
                                    TranslateAnimation translateAnimationRocket = new TranslateAnimation(0, 0, ScreenUtils.getScreenHeight() / 2.0f, -ScreenUtils.getScreenHeight() / 3.0f);
                                    translateAnimationRocket.setDuration(500);
                                    translateAnimationHead.setFillEnabled(true);
                                    translateAnimationHead.setFillAfter(true);
                                    translateAnimationRocket.setFillEnabled(true);
                                    translateAnimationRocket.setFillAfter(true);
                                    mIvHead.setAnimation(translateAnimationHead);
                                    mIvRocket.setAnimation(translateAnimationRocket);
                                }
                            });
                        }
                    }, 1000);
                }

            }

            @Override
            public void onAnimationEnd() {
                //渐变动画
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(300);
                mIvHead.startAnimation(alphaAnimation);
                mIvRocket.setAnimation(alphaAnimation);
                mIvHead.setVisibility(View.GONE);
                mIvRocket.setVisibility(View.GONE);

                mRecyclerView.smoothScrollToPosition(0);//回到顶部

                String rocketNumber = mTvRocket.getText().toString().trim();
                int number = Integer.parseInt(rocketNumber);
                if (number > 0) {
                    number--;
                    mTvRocket.setText(String.format(Locale.ENGLISH, "%d", number));
                    //取出当前用户放到中间
                    List<ShowOnlineBean.MapListBean> data = mShowOnlineAdapter.getData();
                    int size = data.size();
                    for (int i = 0; i < size; i++) {
                        ShowOnlineBean.MapListBean mapListBean = mShowOnlineAdapter.getData().get(i);
                        int id = mapListBean.getId();
                        if (String.valueOf(id).equals(SPUtils.getInstance().getString("userId"))) {
                            data.remove(mapListBean);
                            data.add(1, mapListBean);
                            mShowOnlineAdapter.notifyDataSetChanged();
                            return;
                        }
                    }
                } else {
                    mShowRocketDialog = new ShowRocketDialog(ShowOnlineActivity.this);
                    mShowRocketDialog.showPopupWindow();
                }

            }

            @Override
            public void onAnimationRepeat() {

            }
        });

    }

    /**
     * 获取需要播放的动画资源
     */
    private int[] getRes() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.image_rocket);
        int len = typedArray.length();
        int[] resId = new int[len];
        for (int i = 0; i < len; i++) {
            resId[i] = typedArray.getResourceId(i, -1);
        }
        typedArray.recycle();
        return resId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mLocationClient != null) {
            mLocationClient.stopLocation();//停止定位
            mLocationClient.onDestroy();
            mLocationClient = null;
        }
        if (mAMapLocationListener != null) {
            mAMapLocationListener = null;
        }
        if (mShowRocketDialog != null) {
            mShowRocketDialog.dismiss();
            mShowRocketDialog = null;
        }
    }

    private void initLocation() {
        AMapLocationClientOption locationClientOption = new AMapLocationClientOption();
        //设置为高精度
        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置默认返回地址
        locationClientOption.setNeedAddress(true);
        //设置是否只定位一次
        locationClientOption.setOnceLocation(false);
        if (locationClientOption.isOnceLocation()) {
            locationClientOption.setOnceLocationLatest(true);
        }
        //设置是否强制刷新WiFi
        locationClientOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        locationClientOption.setMockEnable(true);
        //设置定位间隔,单位毫秒,默认为2000ms
        locationClientOption.setInterval(1000);
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        //为定位进行设置
        mLocationClient.setLocationOption(locationClientOption);
        //设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener);
        //启动定位
        mLocationClient.startLocation();
    }

    private AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //获取经纬度
                    latitude = amapLocation.getLatitude();//获取纬度
                    longitude = amapLocation.getLongitude();//获取经度
                    Log.i("uwe", "纬度" + latitude + "经度" + longitude);
                    //String province = amapLocation.getProvince();//获取省份
                    //String city = amapLocation.getCity();//获取城市
                    //String district = amapLocation.getDistrict();//获取城区
                    //String street = amapLocation.getStreet();//街道信息
                    //String streetNum = amapLocation.getStreetNum();//街道门牌号信息
                    //String poiName = amapLocation.getPoiName();//获取位置
                    //mTvAddress.setText(String.format(Locale.ENGLISH, "%s%s%s", city, district, street));
                }
            }
        }
    };

}
