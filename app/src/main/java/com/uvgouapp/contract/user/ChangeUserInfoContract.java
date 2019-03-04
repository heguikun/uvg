package com.uvgouapp.contract.user;


import android.app.Activity;

import com.luck.picture.lib.entity.LocalMedia;
import com.uvgouapp.common.base.BaseView;
import com.uvgouapp.model.other.CityBean;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/2/22
 * - @Description:  修改信息
 */
public interface ChangeUserInfoContract {

    interface Presenter {
        //用户id 查询用户
        void requestQueryUserInfo(String userId);

        void replaceUserHeadImg();

        //上传图片
        void uploadPictures(LocalMedia localMedia);

        void uploadName(String name);

        //true  男   false  女
        void uploadGender(boolean sex);

        //常住地
        void uploadAddress(String address);

        //出生年月
        void uploadDateOfBirth(String dateOfBirth);

        //职业
        void uploadOccupation(String occupation);

        //星座
        void uploadConstellation(String constellation);

        //身高
        void uploadHeight(String height);

        //体重
        void uploadWeight(String weight);

        //情感
        void uploadEmotion(String emotion);

        //解析省市县
        void parseData(Activity activity);
    }

    interface View extends BaseView {

        void showHeadImg(String headImg);

        void showName(String name);

        void showMobile(String mobile);

        void showO2(String O2);

        void showSex(boolean sex);

        void showOccupation(String occupation);

        void showAddress(String obode);

        void showDateOfBirth(String dateOfBirth);

        void showConstellation(String constellation);

        void showHeight(String height);

        void showWeight(String weight);

        void showEmotion(String emotion);

        void setListData(List<CityBean> provinceList, List<List<String>> cityList, List<List<List<String>>> countyList);

    }

}
