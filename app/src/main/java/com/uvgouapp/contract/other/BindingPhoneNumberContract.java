package com.uvgouapp.contract.other;


import com.uvgouapp.common.base.BaseView;

/**
 * - @Author:  ying
 * - @Time:  2019/2/21
 * - @Description:  绑定手机号码
 */
public interface BindingPhoneNumberContract {

    interface Presenter {

        //发送手机号码短息
        void sendPhoneSms(String phone);

        //绑定用户手机号  用户id  手机号码  验证码
        void requestUserBindingPhone(String userId, String phone, String smsCode);
    }

    interface View extends BaseView {

        void onSuccess(String phone);
    }

}
