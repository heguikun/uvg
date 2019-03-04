package com.uvgouapp.dialog.user;


import android.content.Context;
import android.view.View;

import com.uvgouapp.R;
import com.uvgouapp.presenter.user.ChangeUserInfoPresenter;

import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/2/28
 * - @Description:  情感
 */
public class UserEmotionDialog extends BasePopupWindow implements View.OnClickListener {

    private ChangeUserInfoPresenter mPresenter;

    public UserEmotionDialog(Context context, ChangeUserInfoPresenter presenter) {
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
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_choose_emotion);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_one://单身
                mPresenter.uploadEmotion("单身");
                dismiss();
                break;
            case R.id.tv_two://恋爱
                mPresenter.uploadEmotion("恋爱");
                dismiss();
                break;
            case R.id.tv_three://貌似恋爱
                mPresenter.uploadEmotion("貌似恋爱");
                dismiss();
                break;
            case R.id.tv_four://已婚
                mPresenter.uploadEmotion("已婚");
                dismiss();
                break;
            case R.id.tv_five://分居
                mPresenter.uploadEmotion("分居");
                dismiss();
                break;
            case R.id.tv_six://离异
                mPresenter.uploadEmotion("离异");
                dismiss();
                break;
            default:
                break;
        }
    }
}
