package com.uvgouapp.ui.circle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.uvgouapp.R;
import com.uvgouapp.adapter.circle.CircleAdapter;
import com.uvgouapp.common.base.BaseFragment;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.contract.circle.CircleContract;
import com.uvgouapp.dialog.circle.CircleCameraDialog;
import com.uvgouapp.dialog.circle.CircleReplaceImageDialog;
import com.uvgouapp.home.HomeActivity;
import com.uvgouapp.model.circle.CircleAllBean;
import com.uvgouapp.model.circle.CommentItem;
import com.uvgouapp.model.circle.FavortItem;
import com.uvgouapp.model.other.MessageEvent;
import com.uvgouapp.presenter.circle.CirclePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

/**
 * - @Author:  ying
 * - @Time:  2018/12/23
 * - @Description:  企友圈
 */

public class CircleFragment extends BaseFragment<CirclePresenter> implements CircleContract.View, View.OnClickListener {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.fl_title)
    FrameLayout mFrameLayout;//标题布局
    @BindView(R.id.tv_title)
    TextView mTvTitle;//标题
    @BindView(R.id.iv_top)
    ImageView mIvTop;//顶部图片
    @BindView(R.id.iv_photo)
    ImageView mIvPhoto;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    //------------------头部-----------------
    private ImageView mIvHeadBg;//背景图片
    private ImageView mIvHeadPortrait;//头像
    private TextView mTvAddress;//地址
    private TextView mTvName;//用户名
    private SegmentTabLayout mSegmentTabLayout;

    private final String[] mTitles = {"全部", "商品", "生活"};

    private AMapLocationClient mLocationClient = null;
    private CircleAdapter mCircleAdapter = null;
    private LinearLayoutManager mLayoutManager = null;

    private int page = 1;//当前页

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefresh(MessageEvent messageEvent) {
        //---------------------接收发布过来的刷新数据
        switch (messageEvent.refresh) {
            case 10://刷新数据
                mRefreshLayout.autoRefresh();
                break;
            default:
                break;
        }
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_circle_two;
    }

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

    @Override
    protected void initView() {
        List<CircleAllBean> list = new ArrayList<>();
        View view = View.inflate(mBaseActivity, R.layout.item_circle_head, null);
        mPresenter = new CirclePresenter(this);
        mPresenter.attachView(this);

        mLayoutManager = new LinearLayoutManager(mBaseActivity);
        mCircleAdapter = new CircleAdapter(list);
        mCircleAdapter.addHeaderView(view);//添加头部

        mIvHeadBg = view.findViewById(R.id.iv_head_bg);
        mIvHeadPortrait = view.findViewById(R.id.iv_head_portrait);
        mTvAddress = view.findViewById(R.id.tv_address);
        mTvName = view.findViewById(R.id.tv_name);
        mSegmentTabLayout = view.findViewById(R.id.segment_tab_layout);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mCircleAdapter);
        mSegmentTabLayout.setTabData(mTitles);
        mSegmentTabLayout.setCurrentTab(0);//默认选中全部
        mCircleAdapter.setPresenter(mPresenter);
        mCircleAdapter.setHomeActivity((HomeActivity) mBaseActivity);

    }

    @Override
    protected void initData() {
        initLocation();
        mPresenter.requestQueryUserInfo(SPUtils.getInstance().getString("userId"));
        page = 1;
        loadData();
    }

    @Override
    protected void initListener() {
        //--------------背景,头像,发布,顶部-----------------
        mIvHeadBg.setOnClickListener(this);
        mIvHeadPortrait.setOnClickListener(this);
        mIvPhoto.setOnClickListener(this);
        mIvTop.setOnClickListener(this);

        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                loadData();
                mRefreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData();
                mRefreshLayout.finishRefresh();
            }
        });

        mSegmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                page = 1;
                loadData();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mLayoutManager != null) {
                    //第一条目索引
                    int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
                    if (firstVisibleItemPosition > 0) {
                        mTvTitle.setVisibility(View.VISIBLE);
                        mIvTop.setVisibility(View.VISIBLE);
                        mFrameLayout.setBackgroundColor(mBaseActivity.getResources().getColor(R.color.color_white));
                    } else {
                        mTvTitle.setVisibility(View.INVISIBLE);
                        mIvTop.setVisibility(View.INVISIBLE);
                        mFrameLayout.setBackgroundColor(mBaseActivity.getResources().getColor(R.color.transparent));
                    }
                }
            }

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            List<LocalMedia> list = PictureSelector.obtainMultipleResult(data);
            LocalMedia localMedia = list.get(0);//获取第一张图片
            switch (requestCode) {
                case 1://更换背景图片
                    if (localMedia != null)
                        mPresenter.uploadPictures(localMedia, 1);
                    else
                        ToastUtils.showShort("请重新选择照片");
                    break;
                case 2://更换头像
                    if (localMedia != null)
                        mPresenter.uploadPictures(localMedia, 2);
                    else
                        ToastUtils.showShort("请重新选择照片");
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 加载不同类型的数据
     */
    private void loadData() {
        switch (mSegmentTabLayout.getCurrentTab()) {
            case 0://全部
                mPresenter.requestCircleAllData(SPUtils.getInstance().getString("userId"), String.valueOf(page), "10");
                break;
            case 1://商品
                mPresenter.requestShopDynamic(SPUtils.getInstance().getString("userId"), String.valueOf(page), "10");
                break;
            case 2://生活
                mPresenter.requestLiveDynamic(SPUtils.getInstance().getString("userId"), String.valueOf(page), "10");
                break;
            default:
                break;
        }
    }

    private synchronized void initLocation() {
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
                    double latitude = amapLocation.getLatitude();//获取纬度
                    double longitude = amapLocation.getLongitude();//获取经度
                    Log.i("uwe", "纬度" + latitude + "经度" + longitude);
                    //String province = amapLocation.getProvince();//获取省份
                    String city = amapLocation.getCity();//获取城市
                    String district = amapLocation.getDistrict();//获取城区
                    String street = amapLocation.getStreet();//街道信息
                    //String streetNum = amapLocation.getStreetNum();//街道门牌号信息
                    //String poiName = amapLocation.getPoiName();//获取位置
                    mTvAddress.setText(String.format(Locale.ENGLISH, "%s%s%s", city, district, street));
                }
            }
        }
    };

    @Override
    public void showBgImg(String bgImg) {
        ImageLoaderUtil.into(mBaseActivity, bgImg, mIvHeadBg);
    }

    @Override
    public void showDefaultImg() {
        ImageLoaderUtil.into(mBaseActivity, R.drawable.tyq_bg, mIvHeadBg);
    }

    @Override
    public void showHeadImg(String headImg) {
        ImageLoaderUtil.into(mBaseActivity, headImg, mIvHeadPortrait);
    }

    @Override
    public void showUsername(String username) {
        mTvName.setText(username);
    }

    @Override
    public void updateloadCircleData(List<CircleAllBean> datas) {
        if (page == 1) {
            mCircleAdapter.setNewData(datas);
        } else {
            mCircleAdapter.addData(datas);
            mCircleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void updateAddFavorite(int itemType, int position, FavortItem favortItem) {
        if (favortItem != null) {
            CircleAllBean circleAllBean = mCircleAdapter.getData().get(position);
            switch (itemType) {
                case CircleAllBean.TYPE_SHOP://商品
                    circleAllBean.thumbs = true;
                    circleAllBean.favorters.add(favortItem);
                    break;
                case CircleAllBean.TYPE_LIVE://生活
                    circleAllBean.thumbs = true;
                    circleAllBean.favorters.add(favortItem);
                    break;
                default:
                    break;
            }
            mCircleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void updateReplyComment(int tableType, int position, CommentItem commentItem) {
        if (commentItem != null) {
            CircleAllBean circleAllBean = mCircleAdapter.getData().get(position);
            switch (tableType) {
                case 1://生活
                    circleAllBean.comments.add(commentItem);
                    break;
                case 2://商品
                    circleAllBean.comments.add(commentItem);
                    break;
                default:
                    break;
            }
            mCircleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void updateCollect(int position, boolean collect) {
        if (collect) {
            CircleAllBean circleAllBean = mCircleAdapter.getData().get(position);
            circleAllBean.collect = true;
            mCircleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head_bg://1   更换背景图片
                CircleReplaceImageDialog circleReplaceBgDialog = new CircleReplaceImageDialog(mBaseActivity, this, 1);
                circleReplaceBgDialog.showPopupWindow();
                break;
            case R.id.iv_head_portrait://2   更换头像
                CircleReplaceImageDialog circleReplaceHeadDialog = new CircleReplaceImageDialog(mBaseActivity, this, 2);
                circleReplaceHeadDialog.showPopupWindow();
                break;
            case R.id.iv_photo://发布商品  分享生活
                CircleCameraDialog circleCameraDialog = new CircleCameraDialog(mBaseActivity);
                circleCameraDialog.showPopupWindow();
                break;
            case R.id.iv_top://顶部
                //滚动到顶部
                mRecyclerView.smoothScrollToPosition(0);
                break;
            default:
                break;
        }
    }
}
