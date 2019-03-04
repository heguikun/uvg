package com.uvgouapp.ui.show;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.uvgouapp.R;
import com.uvgouapp.adapter.show.ShowAdapter;
import com.uvgouapp.common.base.BaseFragment;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.contract.show.ShowContract;
import com.uvgouapp.model.other.MessageEvent;
import com.uvgouapp.model.show.ShowBean;
import com.uvgouapp.presenter.show.ShowPresenter;
import com.uvgouapp.view.PagingScrollHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * - @Author:  ying
 * - @Time:  2018/12/23
 * - @Description:  秀场
 */
public class ShowFragment extends BaseFragment<ShowPresenter> implements ShowContract.View {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_recommend)
    TextView mTvRecommend;//推荐
    @BindView(R.id.tv_nearby)
    TextView mTvNearby;//附近

    private ShowAdapter mShowAdapter = null;

    private AMapLocationClient mLocationClient = null;

    private double latitude, longitude;//纬度  经度

    private int pageNo = 1;

    private int type = 1;// 1.推荐  2.附近

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mLocationClient != null) {
            mLocationClient.stopLocation();//停止定位
            mLocationClient.onDestroy();
            mLocationClient = null;
        }
        if (mAMapLocationListener != null) {
            mAMapLocationListener = null;
        }
    }

    //---------------接收秀场发布页面发过来的消息---------------
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadAndRefresh(MessageEvent messageEvent) {
        switch (messageEvent.refreshLoad) {
            case 3://秀场刷新
                mRefreshLayout.autoRefresh();
                break;
            default:
                break;
        }
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_show;
    }

    @Override
    protected void initView() {
        mPresenter = new ShowPresenter(this);
        mPresenter.attachView(this);

        PagingScrollHelper pagingScrollHelper = new PagingScrollHelper();
        mShowAdapter = new ShowAdapter(R.layout.item_show);
        LinearLayoutManager linearLayoutManagerV = new LinearLayoutManager(mBaseActivity);
        mRecyclerView.setLayoutManager(linearLayoutManagerV);
        mRecyclerView.setAdapter(mShowAdapter);
        pagingScrollHelper.setUpRecycleView(mRecyclerView);
        pagingScrollHelper.updateLayoutManger();
        pagingScrollHelper.scrollToPosition(0);
        mRecyclerView.setHorizontalScrollBarEnabled(true);
        mShowAdapter.setShowPresenter(mPresenter);
    }

    @Override
    protected void initData() {
        mPresenter.requestNewQueryAll("", "", "", "500000", "", "", SPUtils.getInstance().getString("userId"), String.valueOf(pageNo), "10");
    }

    @Override
    protected void initListener() {
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                switch (type) {
                    case 1://推荐
                        mPresenter.requestNewQueryAll("", "", "", "500000", "", "", SPUtils.getInstance().getString("userId"), String.valueOf(pageNo), "10");
                        break;
                    case 2://附近
                        initLocation();
                        mPresenter.requestNewQueryAll(String.valueOf(longitude), String.valueOf(latitude), "", "500000", "", "", SPUtils.getInstance().getString("userId"), String.valueOf(pageNo), "10");
                        break;
                    default:
                        break;
                }
                mRefreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                switch (type) {
                    case 1://推荐
                        mPresenter.requestNewQueryAll("", "", "", "500000", "", "", SPUtils.getInstance().getString("userId"), String.valueOf(pageNo), "10");
                        break;
                    case 2://附近
                        initLocation();
                        mPresenter.requestNewQueryAll(String.valueOf(longitude), String.valueOf(latitude), "", "500000", "", "", SPUtils.getInstance().getString("userId"), String.valueOf(pageNo), "10");
                        break;
                    default:
                        break;
                }
                mRefreshLayout.finishRefresh();
            }
        });

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
    public void setNewQueryAllListData(List<ShowBean.MapListBean> listData) {
        if (pageNo == 1) {
            mShowAdapter.setNewData(listData);
        } else {
            mShowAdapter.addData(listData);
            mShowAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void update2Follow(int position) {
        //---------------刷新关注-----------
        ShowBean.MapListBean mapListBean = mShowAdapter.getData().get(position);
        mapListBean.setsId(true);
        mShowAdapter.notifyItemChanged(position);
    }

    @Override
    public void update2Favort(int position) {
        //---------------刷新点赞-------------
        ShowBean.MapListBean mapListBean = mShowAdapter.getData().get(position);
        int thumbsCount = mapListBean.getThumbsCount();//点赞数量+1
        mapListBean.setTId(true);
        mapListBean.setThumbsCount(thumbsCount + 1);
        mShowAdapter.notifyItemChanged(position);
    }

    @OnClick({R.id.tv_release, R.id.tv_recommend, R.id.tv_nearby})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_release://发布秀场
                startActivity(new Intent(mBaseActivity, ShowReleaseActivity.class));
                break;
            case R.id.tv_recommend://推荐
                type = 1;
                initData();
                mTvRecommend.setTextColor(getResources().getColor(R.color.white));
                mTvNearby.setTextColor(getResources().getColor(R.color.color_cccccc));
                break;
            case R.id.tv_nearby://附近
                type = 2;
                initLocation();
                mPresenter.requestNewQueryAll(String.valueOf(longitude), String.valueOf(latitude), "", "500000", "", "", SPUtils.getInstance().getString("userId"), String.valueOf(pageNo), "10");
                mTvNearby.setTextColor(getResources().getColor(R.color.white));
                mTvRecommend.setTextColor(getResources().getColor(R.color.color_cccccc));
                break;
            default:
                break;
        }
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        AMapLocationClientOption locationClientOption = new AMapLocationClientOption();
        //设置为高精度
        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置默认返回地址
        locationClientOption.setNeedAddress(true);
        //设置是否只定位一次
        locationClientOption.setOnceLocation(true);
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
        mLocationClient = new AMapLocationClient(mBaseActivity);
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
