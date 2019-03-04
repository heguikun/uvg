package com.uvgouapp.contract.user;


import com.uvgouapp.common.base.BaseView;

/**
 * - @Author:  ying
 * - @Time:  2019/1/19
 * - @Description:  申请店铺
 */
public interface ApplyStoreContract {

    interface Presenter {

        //店铺名  经营范围  姓名  身份证  手机号  验证码 用户id
        void requestApplyStore(String shopName, String businessScope, String userName, String idCard, String mobilePhone,
                               String smsCode, String userId, boolean select);

        //发送手机号码短息
        void sendPhoneSms(String phone);

    }

    interface View extends BaseView {

        void onSuccess();

    }

}
