package com.uvgouapp.ui.circle;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uvgouapp.R;
import com.uvgouapp.adapter.circle.CircleSelectPhotosAdapter;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.common.util.PermissionsUtil;
import com.uvgouapp.contract.circle.ReleaseShopContract;
import com.uvgouapp.dialog.other.AboutCommissionDialog;
import com.uvgouapp.model.other.CityBean;
import com.uvgouapp.presenter.circle.ReleaseShopPresenter;
import com.uvgouapp.ui.other.ChooseCategoryActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * - @Author:  ying
 * - @Time:  2019/1/4
 * - @Description:  发布商品  标准发布  快捷发布
 */
public class ReleaseShopActivity extends BaseActivity<ReleaseShopPresenter> implements ReleaseShopContract.View {

    @BindView(R.id.et_shop_name)
    EditText mEtShopName;//商品名称
    @BindView(R.id.et_content)
    EditText mEtContent;//描述商品内容
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;//九宫格图片
    @BindView(R.id.tv_location)
    TextView mTvLocation;//地址
    @BindView(R.id.et_retail_price)
    EditText mEtRetailPrice;//零售价
    @BindView(R.id.et_trade_price_commision)
    EditText mEtTradePriceCommision;//批发价折扣
    @BindView(R.id.et_trade_price_conversion_money)
    EditText mEtTradePriceConversionMoney;//批发价换算金额
    @BindView(R.id.ll_quick_stock_number)
    LinearLayout mLlQuickStockNumber;//快捷库存布局
    @BindView(R.id.et_quick_stock_number)
    EditText mEtQuickStockNumber;//快捷库存件数

    @BindView(R.id.et_buy_show_commision)
    EditText mEtBuyShowCommision;//买家秀佣金
    @BindView(R.id.et_buy_show_conversion_money)
    EditText mEtBuyShowConversionMoney;//买家秀佣金换算金额
    @BindView(R.id.et_release_commision)
    EditText mEtReleaseCommision;//发布佣金
    @BindView(R.id.et_release_conversion_money)
    EditText mEtReleaseConversionMoney;//发布佣金换算金额
    @BindView(R.id.et_give_commision)
    EditText mEtGiveCommision;//点赞佣金
    @BindView(R.id.et_give_conversion_money)
    EditText mEtGiveConversionMoney;//点赞佣金换算金额

    @BindView(R.id.tv_product_info)
    TextView mTvProductInfo;//产品信息文本
    @BindView(R.id.ll_product_info)
    LinearLayout mLlProductInfo;//产品信息布局
    //@BindView(R.id.tv_brand)
    //TextView mTvBrand;//品牌
    @BindView(R.id.et_brand)
    EditText mEtBrand;//品牌
    @BindView(R.id.tv_classification)
    TextView mTvClassification;//宝贝分类
    @BindView(R.id.et_goods_number)
    EditText mEtGoodsNumber;//货号
    @BindView(R.id.et_standard_stock_number)
    EditText mEtStandardStockNumber;//标准库存件数
    @BindView(R.id.et_freight)
    EditText mEtFreight;//运费
    @BindView(R.id.tv_deliver_goods_address)
    TextView mTvDeliverGoodsAddress;//发货地址

    private List<LocalMedia> mChoiceList = null;//选择图片list
    private List<String> mImageUrlList = null;//成功回调图片list
    private CircleSelectPhotosAdapter mAdapter = null;
    private AMapLocationClient mLocationClient = null;
    private AboutCommissionDialog mAboutCommissionDialog = null;

    private static final int REQUEST_CODE_SCAN = 0x01;
    private static final int REQUEST_CODE_CATEGORY_NAME = 0x02;

    private int releaseShop;  //0.快捷发布  1.标准发布

    @Override
    protected int getLayoutId() {
        return R.layout.activity_release_shop;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            //0.快捷发布  1.标准发布
            releaseShop = intent.getIntExtra("releaseShop", 0);
            switch (releaseShop) {
                case 0://快捷发布
                    mLlQuickStockNumber.setVisibility(View.VISIBLE);
                    mTvProductInfo.setVisibility(View.GONE);
                    mLlProductInfo.setVisibility(View.GONE);
                    break;
                case 1://标准发布
                    mLlQuickStockNumber.setVisibility(View.GONE);
                    mTvProductInfo.setVisibility(View.VISIBLE);
                    mLlProductInfo.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
        mChoiceList = new ArrayList<>();
        mImageUrlList = new ArrayList<>();
        mPresenter = new ReleaseShopPresenter(this);
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
        //零售价
        mEtRetailPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String retailPrice = mEtRetailPrice.getText().toString().trim();//零售价
                String tradePriceCommision = mEtTradePriceCommision.getText().toString().trim();//批发价折扣
                String buyShowCommision = mEtBuyShowCommision.getText().toString().trim();//买家秀佣金百分比
                String releaseCommision = mEtReleaseCommision.getText().toString().trim();//发布佣金百分比
                String giveCommision = mEtGiveCommision.getText().toString().trim();//点赞佣金百分比

                if (!StringUtils.isEmpty(retailPrice) && !StringUtils.isEmpty(tradePriceCommision)) {
                    double retailPriceD = Double.parseDouble(retailPrice);
                    double tradePriceCommisionD = Double.parseDouble(tradePriceCommision);
                    if (tradePriceCommisionD > 0 && tradePriceCommisionD <= 10) {
                        //批发价金额
                        mEtTradePriceConversionMoney.setText(String.format(Locale.ENGLISH, "%.2f", retailPriceD * tradePriceCommisionD / 10));
                    } else {
                        ToastUtils.showShort("折扣1到10之间");
                        mEtTradePriceConversionMoney.setText(String.format(Locale.ENGLISH, "%s", "0.00"));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //批发价折扣
        mEtTradePriceCommision.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String retailPrice = mEtRetailPrice.getText().toString().trim();//零售价
                String tradePriceCommision = mEtTradePriceCommision.getText().toString().trim();//批发价折扣
                if (!StringUtils.isEmpty(retailPrice) && !StringUtils.isEmpty(tradePriceCommision)) {
                    double retailPriceD = Double.parseDouble(retailPrice);
                    double tradePriceCommisionD = Double.parseDouble(tradePriceCommision);
                    if (tradePriceCommisionD > 0 && tradePriceCommisionD <= 10) {
                        //批发价金额
                        mEtTradePriceConversionMoney.setText(String.format(Locale.ENGLISH, "%.2f", retailPriceD * tradePriceCommisionD / 10));
                    } else {
                        ToastUtils.showShort("折扣1到10之间");
                        mEtTradePriceConversionMoney.setText(String.format(Locale.ENGLISH, "%s", "0.00"));
                    }
                } else {
                    mEtTradePriceConversionMoney.setText(String.format(Locale.ENGLISH, "%s", "0.00"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //买家秀佣金
        mEtBuyShowCommision.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String buyShowCommision = mEtBuyShowCommision.getText().toString().trim();//买家秀百分比
                String releaseCommision = mEtReleaseCommision.getText().toString().trim();//发布佣金百分比
                String giveCommision = mEtGiveCommision.getText().toString().trim();//点赞佣金百分比
                String tradePriceConversionMoney = mEtTradePriceConversionMoney.getText().toString().trim();//批发价金额

                if (!StringUtils.isEmpty(tradePriceConversionMoney) && !StringUtils.isEmpty(buyShowCommision)
                        && !StringUtils.isEmpty(releaseCommision) && !StringUtils.isEmpty(giveCommision)) {
                    double buyShowCommisionD = Double.parseDouble(buyShowCommision);
                    double tradePriceConversionMoneyD = Double.parseDouble(tradePriceConversionMoney);
                    int releaseCommisionD = Integer.parseInt(releaseCommision);
                    if (buyShowCommisionD > 0 && buyShowCommisionD <= 100) {
                        double buyShowConversionMoneyD = buyShowCommisionD / 100 * tradePriceConversionMoneyD;
                        mEtBuyShowConversionMoney.setText(String.format(Locale.ENGLISH, "%.2f", buyShowConversionMoneyD));
                        mEtReleaseConversionMoney.setText(String.format(Locale.ENGLISH, "%.2f", releaseCommisionD / 100.0 * buyShowConversionMoneyD));
                        mEtGiveCommision.setText(String.format(Locale.ENGLISH, "%d", 100 - releaseCommisionD));
                        mEtGiveConversionMoney.setText(String.format(Locale.ENGLISH, "%.2f", buyShowConversionMoneyD - releaseCommisionD / 100.0 * buyShowConversionMoneyD));
                    } else {
                        ToastUtils.showShort("百分比1到100之间");
                        mEtBuyShowConversionMoney.setText(String.format(Locale.ENGLISH, "%s", "0.00"));
                    }
                } else {
                    if (!StringUtils.isEmpty(tradePriceConversionMoney) && !StringUtils.isEmpty(buyShowCommision)) {
                        double buyShowCommisionD = Double.parseDouble(buyShowCommision);
                        double tradePriceConversionMoneyD = Double.parseDouble(tradePriceConversionMoney);
                        if (buyShowCommisionD > 0 && buyShowCommisionD <= 100) {
                            double buyShowConversionMoneyD = buyShowCommisionD / 100 * tradePriceConversionMoneyD;
                            mEtBuyShowConversionMoney.setText(String.format(Locale.ENGLISH, "%.2f", buyShowConversionMoneyD));
                        } else {
                            ToastUtils.showShort("百分比1到100之间");
                            mEtBuyShowConversionMoney.setText(String.format(Locale.ENGLISH, "%s", "0.00"));
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //发布佣金
        mEtReleaseCommision.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String buyShowConversionMoney = mEtBuyShowConversionMoney.getText().toString().trim();//买家秀金额
                String releaseCommision = mEtReleaseCommision.getText().toString().trim();//发布佣金百分比
                if (!StringUtils.isEmpty(buyShowConversionMoney) && !StringUtils.isEmpty(releaseCommision)) {
                    double buyShowConversionMoneyD = Double.parseDouble(buyShowConversionMoney);
                    int releaseCommisionD = Integer.parseInt(releaseCommision);
                    if (releaseCommisionD > 0 && releaseCommisionD <= 100) {
                        mEtReleaseConversionMoney.setText(String.format(Locale.ENGLISH, "%.2f", releaseCommisionD / 100.0 * buyShowConversionMoneyD));
                        mEtGiveCommision.setText(String.format(Locale.ENGLISH, "%d", 100 - releaseCommisionD));
                        mEtGiveConversionMoney.setText(String.format(Locale.ENGLISH, "%.2f", buyShowConversionMoneyD - releaseCommisionD / 100.0 * buyShowConversionMoneyD));
                    } else {
                        ToastUtils.showShort("百分比1到100之间");
                        mEtReleaseConversionMoney.setText(String.format(Locale.ENGLISH, "%s", "0.00"));
                        mEtGiveCommision.setText(String.format(Locale.ENGLISH, "%s", "0"));
                        mEtGiveConversionMoney.setText(String.format(Locale.ENGLISH, "%s", "0.00"));
                    }
                } else {
                    mEtReleaseConversionMoney.setText(String.format(Locale.ENGLISH, "%s", "0.00"));
                    mEtGiveCommision.setText(String.format(Locale.ENGLISH, "%s", "0"));
                    mEtGiveConversionMoney.setText(String.format(Locale.ENGLISH, "%s", "0.00"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @OnClick({R.id.iv_back, R.id.ll_commision, R.id.rl_brand, R.id.rl_classification, R.id.iv_scan,
            R.id.rl_details, R.id.rl_deliver_goods_address, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.ll_commision://佣金设置
                mAboutCommissionDialog = new AboutCommissionDialog(this);
                mAboutCommissionDialog.showPopupWindow();
                break;
            // case R.id.rl_brand://品牌
            // startActivity(new Intent(mContext, BrandActivity.class));
            // break;
            case R.id.rl_classification://宝贝分类
                Intent intent = new Intent(mContext, ChooseCategoryActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CATEGORY_NAME);
                break;
            case R.id.iv_scan://扫一扫
                PermissionsUtil.checkCameraPermissions(this, new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Intent intent = new Intent(mContext, CaptureActivity.class);
                            startActivityForResult(intent, REQUEST_CODE_SCAN);
                        }
                    }
                });
                break;
            case R.id.rl_details://宝贝详情
                ToastUtils.showShort("宝贝详情");
                break;
            case R.id.rl_deliver_goods_address://发货地址
                mPresenter.parseData(this);//解析省市县
                break;
            case R.id.btn_ok://确认发布
                if (mChoiceList.size() > 0) {
                    switch (releaseShop) {
                        case 0://快捷发布
                            String shopName = mEtShopName.getText().toString().trim();//商品名称
                            String shopContent = mEtContent.getText().toString().trim();//商品内容
                            String address = mTvLocation.getText().toString().trim();//地址
                            String retailPrice = mEtRetailPrice.getText().toString().trim();//零售价
                            String tradePriceCommision = mEtTradePriceCommision.getText().toString().trim();//批发价折扣
                            String tradePriceConversionMoney = mEtTradePriceConversionMoney.getText().toString().trim();//批发价换算金额
                            String quickStockNumber = mEtQuickStockNumber.getText().toString().trim();//快捷库存件数
                            String buyShowConversionMoney = mEtBuyShowConversionMoney.getText().toString().trim();//买家秀佣金换算金额
                            String releaseConversionMoney = mEtReleaseConversionMoney.getText().toString().trim();//发布佣金换算金额
                            String giveConversionMoney = mEtGiveConversionMoney.getText().toString().trim();//点赞佣金换算金额
                            String userId = SPUtils.getInstance().getString("userId");//取出用户id
                            String shopId = SPUtils.getInstance().getString("shopId");//取出商铺id

                            mPresenter.requestQuickReleaseShop(shopName, shopContent, mImageUrlList, address, retailPrice,
                                    tradePriceCommision, tradePriceConversionMoney, quickStockNumber, buyShowConversionMoney,
                                    releaseConversionMoney, giveConversionMoney, userId, shopId);
                            break;
                        case 1://标准发布
                            String shopNameStandard = mEtShopName.getText().toString().trim();//商品名称
                            String shopContentStandard = mEtContent.getText().toString().trim();//商品内容
                            String addressStandard = mTvLocation.getText().toString().trim();//地址
                            String retailPriceStandard = mEtRetailPrice.getText().toString().trim();//零售价
                            String tradePriceCommisionStandard = mEtTradePriceCommision.getText().toString().trim();//批发价折扣
                            String tradePriceConversionMoneyStandard = mEtTradePriceConversionMoney.getText().toString().trim();//批发价换算金额
                            String buyShowConversionMoneyStandard = mEtBuyShowConversionMoney.getText().toString().trim();//买家秀佣金换算金额
                            String releaseConversionMoneyStandard = mEtReleaseConversionMoney.getText().toString().trim();//发布佣金换算金额
                            String giveConversionMoneyStandard = mEtGiveConversionMoney.getText().toString().trim();//点赞佣金换算金额
                            String brand = mEtBrand.getText().toString().trim();//品牌
                            String classification = mTvClassification.getText().toString().trim();//宝贝分类
                            String goodsNumber = mEtGoodsNumber.getText().toString().trim();//货号
                            String stockNumberStandard = mEtStandardStockNumber.getText().toString().trim();//标准库存件数
                            String freight = mEtFreight.getText().toString().trim();//运费
                            String goodsAddress = mTvDeliverGoodsAddress.getText().toString().trim();//发货地
                            String userIdStandard = SPUtils.getInstance().getString("userId");//取出用户id
                            String shopIdStandard = SPUtils.getInstance().getString("shopId");//取出商铺id

                            mPresenter.requestStandardReleaseShop(shopNameStandard, shopContentStandard, mImageUrlList, addressStandard,
                                    retailPriceStandard, tradePriceCommisionStandard, tradePriceConversionMoneyStandard, buyShowConversionMoneyStandard,
                                    releaseConversionMoneyStandard, giveConversionMoneyStandard, brand, classification, goodsNumber,
                                    stockNumberStandard, freight, goodsAddress, userIdStandard, shopIdStandard);
                            break;
                        default:
                            break;
                    }
                } else {
                    ToastUtils.showShort("请选择图片");
                }
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
                case PictureConfig.CHOOSE_REQUEST://相册选择图片或者拍照
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
                case REQUEST_CODE_SCAN://扫一扫
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        if (bundle == null) {
                            return;
                        }
                        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                            String result = bundle.getString(CodeUtils.RESULT_STRING);
                            mEtGoodsNumber.setText(result);//显示货号
                        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                            ToastUtils.showShort("重新扫一扫");
                        }
                    }
                case REQUEST_CODE_CATEGORY_NAME://类目名称
                    if (data != null) {
                        String result = data.getStringExtra("result");
                        mTvClassification.setText(result);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void addImageUrl(String imageUrl) {
        mImageUrlList.add(imageUrl);
    }

    @Override
    public void onSuccess() {
        startActivity(new Intent(this, ReleaseSuccessActivity.class));
        finish();
    }

    @Override
    public void onFail(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void setListData(List<CityBean> provinceList, List<List<String>> cityList, List<List<List<String>>> countyList) {
        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = provinceList.size() > 0 ?
                        provinceList.get(options1).getPickerViewText() : "";

                String opt2tx = cityList.size() > 0
                        && cityList.get(options1).size() > 0 ?
                        cityList.get(options1).get(options2) : "";

                String opt3tx = cityList.size() > 0
                        && countyList.get(options1).size() > 0
                        && countyList.get(options1).get(options2).size() > 0 ?
                        countyList.get(options1).get(options2).get(options3) : "";

                String tx = opt1tx + opt2tx + opt3tx;

                mTvDeliverGoodsAddress.setText(tx);//发货地址
            }
        }).build();
        pvOptions.setPicker(provinceList, cityList, countyList);
        pvOptions.show();
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
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.stopLocation();//停止定位
            mLocationClient.onDestroy();
            mLocationClient = null;
        }
        if (mAMapLocationListener != null) {
            mAMapLocationListener = null;
        }
        if (mAboutCommissionDialog != null) {//防止内存泄漏
            mAboutCommissionDialog.dismiss();
            mAboutCommissionDialog = null;
        }
    }
}
