package com.uvgouapp.presenter.user;


import android.app.Activity;

import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.common.base.BasePresenter;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.common.util.PermissionsUtil;
import com.uvgouapp.contract.user.ChangeUserInfoContract;
import com.uvgouapp.model.circle.CirclePhotoBean;
import com.uvgouapp.model.other.CityBean;
import com.uvgouapp.model.user.User;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.reactivex.functions.Consumer;

/**
 * - @Author:  ying
 * - @Time:  2019/2/22
 * - @Description:
 */
public class ChangeUserInfoPresenter extends BasePresenter<ChangeUserInfoContract.View> implements ChangeUserInfoContract.Presenter {

    private BaseActivity mBaseActivity;

    public ChangeUserInfoPresenter(BaseActivity baseActivity) {
        this.mBaseActivity = baseActivity;
    }


    @Override
    public void requestQueryUserInfo(String userId) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.QUERY_MY)
                .params("id", userId)
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
                                User user = GsonUtil.GsonToBean(body, User.class);
                                if (user != null) {
                                    int statusCode = user.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        User.DataBean data = user.getData();
                                        if (data != null) {
                                            String headImg = data.getHeadImg();//头像
                                            String name = data.getName();//用户名
                                            boolean sex = data.isSex();//性别
                                            String mobile = data.getMobile();//手机号
                                            double o2 = data.getO2();//O2
                                            String obode = data.getObode();//常住地
                                            String dateOfBirth = data.getDateOfBirth();//出生年月
                                            String occupation = data.getOccupation();//职业
                                            String constellation = data.getConstellation();//星座
                                            String stature = data.getStature();//身高
                                            String weight = data.getWeight();//体重
                                            String emotionalState = data.getEmotionalState();//情感状态
                                            if (!StringUtils.isEmpty(headImg)) {
                                                mView.showHeadImg(headImg);
                                            }
                                            if (!StringUtils.isEmpty(name)) {
                                                SPUtils.getInstance().remove("name");
                                                SPUtils.getInstance().put("name", name);
                                                mView.showName(name);
                                            }
                                            mView.showSex(sex);
                                            if (!StringUtils.isEmpty(mobile)) {
                                                mView.showMobile(mobile);
                                            }
                                            if (o2 > 0.00) {
                                                mView.showO2(String.format(Locale.ENGLISH, "%.5f", o2));
                                            } else {
                                                mView.showO2("0.00000");
                                            }
                                            if (!StringUtils.isEmpty(obode)) {
                                                mView.showAddress(obode);
                                            }
                                            if (!StringUtils.isEmpty(dateOfBirth)) {
                                                mView.showDateOfBirth(dateOfBirth);
                                            }
                                            if (!StringUtils.isEmpty(occupation)) {
                                                mView.showOccupation(occupation);
                                            }
                                            if (!StringUtils.isEmpty(constellation)) {
                                                mView.showConstellation(constellation);
                                            }
                                            if (!StringUtils.isEmpty(stature)) {
                                                mView.showHeight(stature);
                                            }
                                            if (!StringUtils.isEmpty(weight)) {
                                                mView.showWeight(weight);
                                            }
                                            if (!StringUtils.isEmpty(emotionalState)) {
                                                mView.showEmotion(emotionalState);
                                            }
                                        }
                                    }
                                }
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

    @Override
    public void replaceUserHeadImg() {
        PermissionsUtil.checkFileReadWritePermissions(mBaseActivity, new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    PictureSelector.create(mBaseActivity)//影响回调到哪个地方的onActivityResult()
                            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll() 图片.ofImage() 视频.ofVideo() 音频.ofAudio()
                            .maxSelectNum(1)//最大图片选择数量
                            .minimumCompressSize(1)//最小选择数量
                            .imageSpanCount(4)//每行显示个数
                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                            //.enableCrop(true)//是否裁剪
                            .compress(true)//是否压缩 true or false
                            .cropCompressQuality(10)// 裁剪压缩质量 默认90
                            .minimumCompressSize(100)// 小于100kb的图片不压缩
                            .forResult(PictureConfig.CHOOSE_REQUEST);//1.更换背景 2更换头像
                }
            }
        });
    }

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
                                    String userId = SPUtils.getInstance().getString("userId");
                                    String imageUrl = circlePhotoBean.getData();
                                    mView.showHeadImg(imageUrl);
                                    //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
                                    PictureFileUtils.deleteCacheDirFile(mBaseActivity);
                                    requestReplacePicture(userId, imageUrl);
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void uploadName(String name) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", SPUtils.getInstance().getString("userId"));
        map.put("name", name);
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.USER_UPDATE)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            if (response.code() == Constants.SUCCESS_CODE) {
                                if (!StringUtils.isEmpty(name)) {
                                    mView.showName(name);
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void uploadGender(boolean sex) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", SPUtils.getInstance().getString("userId"));
        map.put("sex", sex);
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.USER_UPDATE)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            if (response.code() == Constants.SUCCESS_CODE) {
                                mView.showSex(sex);
                            }
                        }
                    }
                });
    }

    @Override
    public void uploadAddress(String address) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("id", SPUtils.getInstance().getString("userId"));
        map.put("obode", address);
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.USER_UPDATE)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }
                });
    }

    @Override
    public void uploadDateOfBirth(String dateOfBirth) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("id", SPUtils.getInstance().getString("userId"));
        map.put("dateOfBirth", dateOfBirth);
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.USER_UPDATE)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            if (response.code() == Constants.SUCCESS_CODE) {
                                if (!StringUtils.isEmpty(dateOfBirth)) {
                                    mView.showDateOfBirth(dateOfBirth);
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void uploadOccupation(String occupation) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("id", SPUtils.getInstance().getString("userId"));
        map.put("occupation", occupation);
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.USER_UPDATE)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            if (response.code() == Constants.SUCCESS_CODE) {
                                if (!StringUtils.isEmpty(occupation)) {
                                    mView.showOccupation(occupation);
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void uploadConstellation(String constellation) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("id", SPUtils.getInstance().getString("userId"));
        map.put("constellation", constellation);
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.USER_UPDATE)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            if (response.code() == Constants.SUCCESS_CODE) {
                                mView.showConstellation(constellation);
                            }
                        }
                    }
                });

    }

    @Override
    public void uploadHeight(String height) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("id", SPUtils.getInstance().getString("userId"));
        map.put("stature", height);
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.USER_UPDATE)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            if (response.code() == Constants.SUCCESS_CODE) {
                                mView.showHeight(height);
                            }
                        }
                    }
                });
    }

    @Override
    public void uploadWeight(String weight) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("id", SPUtils.getInstance().getString("userId"));
        map.put("weight", weight);
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.USER_UPDATE)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            if (response.code() == Constants.SUCCESS_CODE) {
                                mView.showWeight(weight);
                            }
                        }
                    }
                });
    }

    @Override
    public void uploadEmotion(String emotion) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("id", SPUtils.getInstance().getString("userId"));
        map.put("emotionalState", emotion);
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.USER_UPDATE)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            if (response.code() == Constants.SUCCESS_CODE) {
                                mView.showEmotion(emotion);
                            }
                        }
                    }
                });
    }

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

    private void requestReplacePicture(String userId, String imageUrl) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", Integer.parseInt(userId));
        map.put("headImg", imageUrl);

        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.USER_UPDATE)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }
                });
    }
}
