package com.uvgouapp.dialog.other;


import android.content.Context;
import android.view.View;

import com.uvgouapp.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/2/13
 * - @Description:  关于佣金
 */
public class AboutCommissionDialog extends BasePopupWindow {

    public AboutCommissionDialog(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_about_commission);
    }
}
