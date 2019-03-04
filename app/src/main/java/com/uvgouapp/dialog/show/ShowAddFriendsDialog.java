package com.uvgouapp.dialog.show;

import android.content.Context;
import android.view.View;


import com.uvgouapp.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/1/2
 * - @Description:  加好友
 */
public class ShowAddFriendsDialog extends BasePopupWindow {

    public ShowAddFriendsDialog(Context context) {
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
        return createPopupById(R.layout.dialog_show_friends);
    }
}
