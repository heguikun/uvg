package com.uvgouapp.contract.circle;


import android.app.Activity;

import com.luck.picture.lib.entity.LocalMedia;
import com.uvgouapp.common.base.BaseView;
import com.uvgouapp.model.other.CityBean;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/1/19
 * - @Description:  发布商品
 */
public interface ReleaseShopContract {

    interface Presenter {

        //快捷发布--->商品名称  商品描述内容  图片集合  地址  零售价  零售价折扣  批发价 库存 买家秀佣金  发布佣金  点赞佣金  用户id  店铺id
        void requestQuickReleaseShop(String commodityName, String commodityDescribe, List<String> imgUrlList,
                                     String positionName, String retailPrice, String forwardPercentage,
                                     String forwardAmt, String commodityStock, String advertisementSumCommission, String lssueCommission,
                                     String thumbsCommission, String userId, String shopId);

        //标准发布--->商品名称  商品描述内容  图片集合  地址  零售价  零售价折扣  批发价  买家秀佣金  发布佣金  点赞佣金
        // 品牌  宝贝分类  货号  库存  运费   发货地  用户id  店铺id
        void requestStandardReleaseShop(String commodityName, String commodityDescribe, List<String> imgUrlList,
                                        String positionName, String retailPrice, String forwardPercentage,
                                        String forwardAmt, String advertisementSumCommission, String lssueCommission,
                                        String thumbsCommission, String brand, String commodityType, String goodsCode,
                                        String commodityStock, String freight, String payLocal, String userId, String shopId);

        //上传图片
        void uploadPictures(LocalMedia localMedia);

        //解析省市县
        void parseData(Activity activity);
    }

    interface View extends BaseView {

        void addImageUrl(String imageUrl);

        void onSuccess();

        void onFail(String msg);

        void setListData(List<CityBean> provinceList, List<List<String>> cityList, List<List<List<String>>> countyList);

    }

}
