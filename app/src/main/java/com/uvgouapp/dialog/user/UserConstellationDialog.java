package com.uvgouapp.dialog.user;


import android.content.Context;
import android.view.View;

import com.uvgouapp.R;
import com.uvgouapp.presenter.user.ChangeUserInfoPresenter;

import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/2/28
 * - @Description:  星座
 */
public class UserConstellationDialog extends BasePopupWindow implements View.OnClickListener {

    private ChangeUserInfoPresenter mPresenter;

    public UserConstellationDialog(Context context, ChangeUserInfoPresenter presenter) {
        super(context);
        this.mPresenter = presenter;
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_one).setOnClickListener(this);
        findViewById(R.id.tv_two).setOnClickListener(this);
        findViewById(R.id.tv_three).setOnClickListener(this);
        findViewById(R.id.tv_four).setOnClickListener(this);
        findViewById(R.id.tv_five).setOnClickListener(this);
        findViewById(R.id.tv_six).setOnClickListener(this);
        findViewById(R.id.tv_seven).setOnClickListener(this);
        findViewById(R.id.tv_eight).setOnClickListener(this);
        findViewById(R.id.tv_nine).setOnClickListener(this);
        findViewById(R.id.tv_ten).setOnClickListener(this);
        findViewById(R.id.tv_eleven).setOnClickListener(this);
        findViewById(R.id.tv_twelve).setOnClickListener(this);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_choose_constellation);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_one://白羊座
                mPresenter.uploadConstellation("白羊座");
                dismiss();
                break;
            case R.id.tv_two://金牛座
                mPresenter.uploadConstellation("金牛座");
                dismiss();
                break;
            case R.id.tv_four://双子座
                mPresenter.uploadConstellation("双子座");
                dismiss();
                break;
            case R.id.tv_three://巨蟹座
                mPresenter.uploadConstellation("巨蟹座");
                dismiss();
                break;
            case R.id.tv_five://狮子座
                mPresenter.uploadConstellation("狮子座");
                dismiss();
                break;
            case R.id.tv_six://处女座
                mPresenter.uploadConstellation("处女座");
                dismiss();
                break;
            case R.id.tv_seven://天秤座
                mPresenter.uploadConstellation("天秤座");
                dismiss();
                break;
            case R.id.tv_eight://天蝎座
                mPresenter.uploadConstellation("天蝎座");
                dismiss();
                break;
            case R.id.tv_nine://射手座
                mPresenter.uploadConstellation("射手座");
                dismiss();
                break;
            case R.id.tv_ten://摩羯座
                mPresenter.uploadConstellation("摩羯座");
                dismiss();
                break;
            case R.id.tv_eleven://水瓶座
                mPresenter.uploadConstellation("水瓶座");
                dismiss();
                break;
            case R.id.tv_twelve://双鱼座
                mPresenter.uploadConstellation("双鱼座");
                dismiss();
                break;
            default:
                break;
        }
    }
}
