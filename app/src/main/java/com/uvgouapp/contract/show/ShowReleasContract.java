package com.uvgouapp.contract.show;


import com.luck.picture.lib.entity.LocalMedia;
import com.uvgouapp.common.base.BaseView;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/1/21
 * - @Description:  发布秀场
 */
public interface ShowReleasContract {

    interface Presenter {
        //内容,图片链接集合,所在位置,用户id
        void sendShowDynamicState(String content, List<String> imgUrlList, String positionName, String userId);

        //上传图片
        void uploadPictures(LocalMedia localMedia);

    }

    interface View extends BaseView {

        void addImageUrl(String imageUrl);

        void onStatusCode(String msg);

        void onFail(String msg);

    }

}
