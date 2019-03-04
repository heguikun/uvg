package com.uvgouapp.dialog.user;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.uvgouapp.R;
import com.uvgouapp.ui.user.ApplyStoreActivity;

import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/1/5
 * - @Description:  我的申请店铺
 */
public class UserStoreDialog extends BasePopupWindow implements View.OnClickListener {

    public UserStoreDialog(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_yes).setOnClickListener(this);
        findViewById(R.id.tv_no).setOnClickListener(this);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_mine_store);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_yes:
                dismiss();
                getContext().startActivity(new Intent(getContext(), ApplyStoreActivity.class));
                break;
            case R.id.tv_no:
                dismiss();
                break;
            default:
                break;
        }
    }
}
