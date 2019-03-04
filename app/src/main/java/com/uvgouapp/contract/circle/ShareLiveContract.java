package com.uvgouapp.contract.circle;

import com.luck.picture.lib.entity.LocalMedia;
import com.uvgouapp.common.base.BaseView;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/1/14
 * - @Description:  分享生活
 */
public interface ShareLiveContract {

    interface Presenter {
        //内容,图片链接集合,所在位置,用户id
        void sendLiveDynamicState(String content, List<String> imgUrlList, String positionName, String userId);

        //发布内容
        void sendLiveContentState(String content, String positionName, String userId);

        //上传图片
        void uploadPictures(LocalMedia localMedia);

    }

    interface View extends BaseView {

        void addImageUrl(String imageUrl);

        void onStatusCode(String msg);

        void onFail(String msg);

    }

}
