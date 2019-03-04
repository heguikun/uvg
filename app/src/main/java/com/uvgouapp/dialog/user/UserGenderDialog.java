package com.uvgouapp.dialog.user;


import android.content.Context;
import android.view.View;
import android.widget.RadioGroup;

import com.uvgouapp.R;
import com.uvgouapp.presenter.user.ChangeUserInfoPresenter;

import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/2/28
 * - @Description:  性别
 */
public class UserGenderDialog extends BasePopupWindow {

    private ChangeUserInfoPresenter mPresenter;

    public UserGenderDialog(Context context, ChangeUserInfoPresenter presenter) {
        super(context);
        this.mPresenter = presenter;
        initView();
    }

    private void initView() {

        RadioGroup radioGroup = findViewById(R.id.rg_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_man://true
                        mPresenter.uploadGender(true);
                        dismiss();
                        break;
                    case R.id.rb_girl://false
                        mPresenter.uploadGender(false);
                        dismiss();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_choose_gender);
    }
}
