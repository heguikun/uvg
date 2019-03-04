package com.uvgouapp.ui.circle;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.uvgouapp.R;
import com.uvgouapp.adapter.circle.CircleSelectPhotosAdapter;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.contract.circle.ShareLiveContract;
import com.uvgouapp.model.other.MessageEvent;
import com.uvgouapp.presenter.circle.ShareLivePresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class ShareLiveActivity extends BaseActivity<ShareLivePresenter> implements ShareLiveContract.View {

    @BindView(R.id.et_content)
    EditText mEtContent;//输入内容
    @BindView(R.id.tv_location)
    TextView mTvLocation;//显示位置
    @BindView(R.id.tv_look)
    TextView mTvLook;//公开  私密  部分可见  不给谁看
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private List<LocalMedia> mChoiceList = null;//选择图片list
    private List<String> mImageUrlList = null;//成功回调图片list
    private CircleSelectPhotosAdapter mAdapter = null;

    private AMapLocationClient mLocationClient = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share_live;
    }

    @Override
    protected void initView() {
        mChoiceList = new ArrayList<>();
        mImageUrlList = new ArrayList<>();
        mPresenter = new ShareLivePresenter(this);
        mPresenter.attachView(this);
        mAdapter = new CircleSelectPhotosAdapter(mChoiceList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        initLocation();
    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.tv_cancel, R.id.tv_release, R.id.rl_location, R.id.rl_look})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel://取消
                finish();
                break;
            case R.id.tv_release://发布
                String userId = SPUtils.getInstance().getString("userId");//取出用户id
                String content = mEtContent.getText().toString().trim();//分享内容
                String address = mTvLocation.getText().toString().trim();//地址
                if (mChoiceList.size() > 0) {
                    mPresenter.sendLiveDynamicState(content, mImageUrlList, address, userId);
                } else {
                    mPresenter.sendLiveContentState(content, address, userId);
                }
                break;
            case R.id.rl_location://位置
                ToastUtils.showShort("暂未开发");
                break;
            case R.id.rl_look://谁可以看
                ToastUtils.showShort("暂未开发");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    mChoiceList = PictureSelector.obtainMultipleResult(data);
                    mAdapter.addData(mChoiceList);
                    mAdapter.notifyDataSetChanged();

                    if (mChoiceList.size() > 0) {
                        int size = mChoiceList.size();
                        for (int i = 0; i < size; i++) {
                            LocalMedia localMedia = mChoiceList.get(i);
                            mPresenter.uploadPictures(localMedia);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * @param imageUrl 图片链接
     */
    @Override
    public void addImageUrl(String imageUrl) {
        mImageUrlList.add(imageUrl);
    }

    @Override
    public void onStatusCode(String msg) {
        ToastUtils.showShort(msg);
        finish();
        //------------------发送消息给淘友圈刷新生活数据----------------
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.refreshLoad = 7;
        EventBus.getDefault().post(messageEvent);
    }

    @Override
    public void onFail(String msg) {
        ToastUtils.showShort(msg);
    }

    /**
     * 初始化所在位置
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
        mLocationClient = new AMapLocationClient(mContext);
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
                    mTvLocation.setText(String.format(Locale.ENGLISH, "%s%s%s", city, district, street));
                    mTvLocation.setTextColor(getResources().getColor(R.color.app_color));
                    //设置左边图片
                    Drawable drawable = getResources().getDrawable(R.drawable.circle_location);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mTvLocation.setCompoundDrawables(drawable, null, null, null);
                }
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.stopLocation();//停止定位
            mLocationClient.onDestroy();
            mLocationClient = null;
        }
        if (mAMapLocationListener != null) {
            mAMapLocationListener = null;
        }
    }
}
