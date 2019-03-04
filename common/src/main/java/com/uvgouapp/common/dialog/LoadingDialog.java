package com.uvgouapp.common.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.uvgouapp.common.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * - @Author:  ying
 * - @Time:  2018/12/22
 * - @Description:  加载框
 */
public class LoadingDialog {

    private AVLoadingIndicatorView mLoading = null;
    private Dialog mDialog = null;

    private void showLoadingDialog(Activity context) { //需要activity上下文  不然的话会报错
        mDialog = new Dialog(context, R.style.loadingDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.loading_dialog, null);
        mLoading = view.findViewById(R.id.loading);

        mDialog.setCancelable(false);
        mDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        mDialog.show();

        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_SEARCH) {
                    mLoading.smoothToHide();
                    mDialog.dismiss();
                    return true;
                }
                return false;
            }
        });

    }

    public void show(Activity context) {
        showLoadingDialog(context);
        mLoading.smoothToShow();
    }

    public void hide() {
        mLoading.smoothToHide();
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

}
