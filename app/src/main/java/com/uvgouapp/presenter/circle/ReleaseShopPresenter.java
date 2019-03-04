package com.uvgouapp.presenter.circle;


import android.app.Activity;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.model.Result;
import com.lzy.okgo.request.base.Request;
import com.lzy.okrx2.adapter.ObservableResult;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.common.base.BasePresenter;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.contract.circle.ReleaseShopContract;
import com.uvgouapp.model.circle.CirclePhotoBean;
import com.uvgouapp.model.other.CityBean;
import com.uvgouapp.model.other.InfoBean;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * - @Author:  ying
 * - @Time:  2019/1/19
 * - @Description:  发布商品
 */
public class ReleaseShopPresenter extends BasePresenter<ReleaseShopContract.View> implements ReleaseShopContract.Presenter {

    private BaseActivity mBaseActivity;

    public ReleaseShopPresenter(BaseActivity baseActivity) {
        this.mBaseActivity = baseActivity;
    }


    /**
     * 快捷发布商品
     *
     * @param commodityName              商品名字
     * @param commodityDescribe          商品描述
     * @param imgUrlList                 图片集合
     * @param positionName               地理位置名称
     * @param retailPrice                零售价
     * @param forwardPercentage          零售价折扣
     * @param forwardAmt                 批发价
     * @param commodityStock             商品库存
     * @param advertisementSumCommission 买家秀佣金
     * @param lssueCommission            发布佣金
     * @param thumbsCommission           点赞佣金
     * @param userId                     用户id
     * @param shopId                     店铺id
     */
    @Override
    public void requestQuickReleaseShop(String commodityName, String commodityDescribe, List<String> imgUrlList,
                                        String positionName, String retailPrice, String forwardPercentage,
                                        String forwardAmt, String commodityStock, String advertisementSumCommission, String lssueCommission,
                                        String thumbsCommission, String userId, String shopId) {

        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        if (verificationQuickString(commodityName, commodityDescribe, imgUrlList, positionName,
                retailPrice, forwardPercentage, forwardAmt, commodityStock, advertisementSumCommission,
                lssueCommission, thumbsCommission)) {

            //字符串拼接 ","  组成一串字符串
            StringBuffer stringBuffer = new StringBuffer();
            int size = imgUrlList.size();
            for (int i = 0; i < size; i++) {
                stringBuffer.append(imgUrlList.get(i)).append(",");
            }
            //删掉拼接后最后一个字符串的  ,
            String s = stringBuffer.toString();
            String imgUrl = s.substring(0, s.length() - 1);

            Map<String, Object> map = new HashMap<>();
            map.put("commodityName", commodityName);
            map.put("commodityDescribe", EncodeUtils.urlEncode(commodityDescribe, "UTF-8"));
            map.put("imgUrl", imgUrl);
            map.put("positionName", positionName);
            map.put("retailPrice", Double.parseDouble(retailPrice));
            map.put("forwardPercentage", Double.parseDouble(forwardPercentage));
            map.put("forwardAmt", Double.parseDouble(forwardAmt));
            map.put("commodityStock", Integer.parseInt(commodityStock));
            map.put("advertisementSumCommission", Double.parseDouble(advertisementSumCommission));
            map.put("lssueCommission", Double.parseDouble(lssueCommission));
            map.put("thumbsCommission", Double.parseDouble(thumbsCommission));
            map.put("userId", userId);
            map.put("commodityShopId", shopId);
            String jsonObject = GsonUtil.GsonToString(map);

            OkGo.<String>post(Api.RELEASE_SHOP)
                    //.isSpliceUrl(true)
                    .upJson(jsonObject)
                    //.params("shopId", shopId)
                    .converter(new StringConvert())
                    .adapt(new ObservableResult<>())
                    .subscribeOn(Schedulers.io())
                    .compose(mBaseActivity.<Result<String>>bindUntilEvent(ActivityEvent.DESTROY))
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {
                            mView.showLoading();
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Result<String>>() {

                        @Override
                        public void onSubscribe(Disposable d) {
                            mBaseActivity.addDisposable(d);
                        }

                        @Override
                        public void onNext(Result<String> stringResult) {
                            Response<String> response = stringResult.response();
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                InfoBean infoBean = GsonUtil.GsonToBean(body, InfoBean.class);
                                if (infoBean != null) {
                                    int statusCode = infoBean.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        mView.onSuccess();
                                    } else {
                                        mView.onFail("发布失败");
                                    }
                                }
                            } else {
                                mView.onFail("发布失败");
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.onFailError(e);
                        }

                        @Override
                        public void onComplete() {
                            mView.hideLoading();
                        }
                    });
        }

    }


    /**
     * 标准发布商品
     *
     * @param commodityName              商品名字
     * @param commodityDescribe          商品描述
     * @param imgUrlList                 图片集合
     * @param positionName               地理位置名称
     * @param retailPrice                零售价
     * @param forwardPercentage          零售价折扣
     * @param forwardAmt                 批发价
     * @param advertisementSumCommission 买家秀佣金
     * @param lssueCommission            发布佣金
     * @param thumbsCommission           点赞佣金
     * @param brand                      品牌
     * @param commodityType              商品类目
     * @param goodsCode                  货号
     * @param commodityStock             商品库存
     * @param freight                    运费
     * @param payLocal                   发货地
     * @param userId                     用户id
     * @param shopId                     店铺id
     */
    @Override
    public void requestStandardReleaseShop(String commodityName, String commodityDescribe, List<String> imgUrlList,
                                           String positionName, String retailPrice, String forwardPercentage,
                                           String forwardAmt, String advertisementSumCommission,
                                           String lssueCommission, String thumbsCommission, String brand,
                                           String commodityType, String goodsCode, String commodityStock,
                                           String freight, String payLocal, String userId, String shopId) {

        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        if (verificationStandardString(commodityName, commodityDescribe, imgUrlList, positionName, retailPrice, forwardPercentage,
                forwardAmt, advertisementSumCommission, lssueCommission, thumbsCommission, brand, commodityType, goodsCode,
                commodityStock, freight, payLocal)) {

            //字符串拼接 ","  组成一串字符串
            StringBuffer stringBuffer = new StringBuffer();
            int size = imgUrlList.size();
            for (int i = 0; i < size; i++) {
                stringBuffer.append(imgUrlList.get(i)).append(",");
            }
            //删掉拼接后最后一个字符串的  ,
            String s = stringBuffer.toString();
            String imgUrl = s.substring(0, s.length() - 1);

            Map<String, Object> map = new HashMap<>();
            map.put("commodityName", commodityName);
            map.put("commodityDescribe", EncodeUtils.urlEncode(commodityDescribe, "UTF-8"));
            map.put("imgUrl", imgUrl);
            map.put("positionName", positionName);
            map.put("retailPrice", Double.parseDouble(retailPrice));
            map.put("forwardPercentage", Double.parseDouble(forwardPercentage));
            map.put("forwardAmt", Double.parseDouble(forwardAmt));
            map.put("advertisementSumCommission", Double.parseDouble(advertisementSumCommission));
            map.put("lssueCommission", Double.parseDouble(lssueCommission));
            map.put("thumbsCommission", Double.parseDouble(thumbsCommission));
            map.put("brand", brand);
            map.put("commodityType", commodityType);
            map.put("goodsCode", goodsCode);
            map.put("commodityStock", Integer.parseInt(commodityStock));
            map.put("freight", Integer.parseInt(freight));
            map.put("payLocal", payLocal);
            map.put("userId", userId);
            map.put("commodityShopId", shopId);
            String jsonObject = GsonUtil.GsonToString(map);

            OkGo.<String>post(Api.RELEASE_SHOP)
                    //.isSpliceUrl(true)
                    .upJson(jsonObject)
                    //.params("shopId", shopId)
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
                                    InfoBean infoBean = GsonUtil.GsonToBean(body, InfoBean.class);
                                    if (infoBean != null) {
                                        int statusCode = infoBean.getStatusCode();
                                        if (statusCode == Constants.SUCCESS_CODE) {
                                            mView.onSuccess();
                                        } else {
                                            mView.onFail("发布失败");
                                        }
                                    }
                                } else {
                                    mView.onFail("发布失败");
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
    }

    /**
     * @param localMedia 图片上传
     */
    @Override
    public void uploadPictures(LocalMedia localMedia) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
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
                                    String imageUrl = circlePhotoBean.getData();
                                    mView.addImageUrl(imageUrl);
                                    //包括裁剪和压缩后的缓存  要在上传成功后调用  注意：需要系统sd卡权限
                                    PictureFileUtils.deleteCacheDirFile(mBaseActivity);
                                }
                            }
                        }
                    }
                });
    }

    /**
     * 解析省市县
     */
    @Override
    public void parseData(Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = ResourceUtils.readAssets2String("city.json");
                if (!StringUtils.isEmpty(json)) {
                    List<CityBean> provinceList = GsonUtil.JsonToList(json, CityBean.class);
                    List<List<String>> cityList = new ArrayList<>();
                    List<List<List<String>>> countyList = new ArrayList<>();

                    int provinceSize = provinceList.size();
                    for (int i = 0; i < provinceSize; i++) {
                        List<String> list = new ArrayList<>();
                        List<List<String>> lists = new ArrayList<>();

                        CityBean cityBean = provinceList.get(i);
                        List<CityBean.CBean> cityBeanC = cityBean.getC();
                        int citySize = cityBeanC.size();

                        for (int j = 0; j < citySize; j++) {
                            List<String> stringList = new ArrayList<>();

                            CityBean.CBean cBean = cityBeanC.get(j);
                            list.add(cBean.getN());
                            List<CityBean.CBean.DBean> d = cBean.getD();
                            int countySize = d.size();

                            for (int z = 0; z < countySize; z++) {
                                CityBean.CBean.DBean dBean = d.get(z);
                                stringList.add(dBean.getN());
                            }
                            lists.add(stringList);
                        }
                        cityList.add(list);
                        countyList.add(lists);
                    }

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mView.setListData(provinceList, cityList, countyList);
                        }
                    });

                }
            }
        }).start();

    }

    /**
     * 验证快捷发布商品
     *
     * @param commodityName              商品名字
     * @param commodityDescribe          商品描述
     * @param imgUrlList                 图片集合
     * @param positionName               地理位置名称
     * @param retailPrice                零售价
     * @param forwardPercentage          零售价折扣
     * @param forwardAmt                 批发价
     * @param commodityStock             商品库存
     * @param advertisementSumCommission 买家秀佣金
     * @param lssueCommission            发布佣金
     * @param thumbsCommission           点赞佣金
     * @return
     */
    private boolean verificationQuickString(String commodityName, String commodityDescribe, List<String> imgUrlList,
                                            String positionName, String retailPrice, String forwardPercentage, String forwardAmt,
                                            String commodityStock, String advertisementSumCommission, String lssueCommission, String thumbsCommission) {

        if (StringUtils.isEmpty(commodityName)) {
            ToastUtils.showShort("请输入商品名称");
            return false;
        }
        if (StringUtils.isEmpty(commodityDescribe)) {
            ToastUtils.showShort("请输入商品描述");
            return false;
        }
        if (imgUrlList == null || imgUrlList.size() <= 0) {
            ToastUtils.showShort("请重新选择图片");
            return false;
        }
        if (StringUtils.isEmpty(positionName)) {
            ToastUtils.showShort("正在重新定位");
            return false;
        }
        if (StringUtils.isEmpty(retailPrice)) {
            ToastUtils.showShort("请输入零售价");
            return false;
        }
        if (StringUtils.isEmpty(forwardPercentage)) {
            ToastUtils.showShort("请输入批发价折扣");
            return false;
        }
        if (StringUtils.isEmpty(forwardAmt)) {
            ToastUtils.showShort("请输入批发价折扣");
            return false;
        }
        if (StringUtils.isEmpty(commodityStock)) {
            ToastUtils.showShort("请输入库存件数");
            return false;
        }
        if (StringUtils.isEmpty(advertisementSumCommission)) {
            ToastUtils.showShort("请输入买家秀佣金折扣");
            return false;
        }
        if (StringUtils.isEmpty(lssueCommission)) {
            ToastUtils.showShort("请输入发布佣金折扣");
            return false;
        }
        if (StringUtils.isEmpty(thumbsCommission)) {
            ToastUtils.showShort("请输入点赞佣金折扣");
            return false;
        }
        return true;
    }

    /**
     * 验证标准发布商品
     *
     * @param commodityName              商品名字
     * @param commodityDescribe          商品描述
     * @param imgUrlList                 图片集合
     * @param positionName               地理位置名称
     * @param retailPrice                零售价
     * @param forwardPercentage          零售价折扣
     * @param forwardAmt                 批发价
     * @param advertisementSumCommission 买家秀佣金
     * @param lssueCommission            发布佣金
     * @param thumbsCommission           点赞佣金
     * @param brand                      品牌
     * @param commodityType              商品类目
     * @param goodsCode                  货号
     * @param commodityStock             商品库存
     * @param freight                    运费
     * @param payLocal                   发货地
     * @return
     */
    private boolean verificationStandardString(String commodityName, String commodityDescribe, List<String> imgUrlList,
                                               String positionName, String retailPrice, String forwardPercentage,
                                               String forwardAmt, String advertisementSumCommission,
                                               String lssueCommission, String thumbsCommission, String brand,
                                               String commodityType, String goodsCode, String commodityStock,
                                               String freight, String payLocal) {

        if (StringUtils.isEmpty(commodityName)) {
            ToastUtils.showShort("请输入商品名称");
            return false;
        }
        if (StringUtils.isEmpty(commodityDescribe)) {
            ToastUtils.showShort("请输入商品描述");
            return false;
        }
        if (imgUrlList == null || imgUrlList.size() <= 0) {
            ToastUtils.showShort("请重新选择图片");
            return false;
        }
        if (StringUtils.isEmpty(positionName)) {
            ToastUtils.showShort("正在重新定位");
            return false;
        }
        if (StringUtils.isEmpty(retailPrice)) {
            ToastUtils.showShort("请输入零售价");
            return false;
        }
        if (StringUtils.isEmpty(forwardPercentage)) {
            ToastUtils.showShort("请输入批发价折扣");
            return false;
        }
        if (StringUtils.isEmpty(forwardAmt)) {
            ToastUtils.showShort("请输入批发价折扣");
            return false;
        }
        if (StringUtils.isEmpty(advertisementSumCommission)) {
            ToastUtils.showShort("请输入买家秀佣金折扣");
            return false;
        }
        if (StringUtils.isEmpty(lssueCommission)) {
            ToastUtils.showShort("请输入发布佣金折扣");
            return false;
        }
        if (StringUtils.isEmpty(thumbsCommission)) {
            ToastUtils.showShort("请输入点赞佣金折扣");
            return false;
        }
        if (StringUtils.isEmpty(brand)) {
            ToastUtils.showShort("请输入品牌");
            return false;
        }
        if (StringUtils.isEmpty(commodityType)) {
            ToastUtils.showShort("请选择宝贝分类");
            return false;
        }
        if (StringUtils.isEmpty(goodsCode)) {
            ToastUtils.showShort("请输入货号");
            return false;
        }
        if (StringUtils.isEmpty(commodityStock)) {
            ToastUtils.showShort("请输入库存");
            return false;
        }
        if (StringUtils.isEmpty(freight)) {
            ToastUtils.showShort("请输入运费");
            return false;
        }
        if (StringUtils.isEmpty(payLocal)) {
            ToastUtils.showShort("请选择发货地址");
            return false;
        }
        return true;
    }

}
