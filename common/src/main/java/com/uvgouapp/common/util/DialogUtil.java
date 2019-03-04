package com.uvgouapp.common.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.uvgouapp.common.R;

/**
 * - @Author:  ying
 * - @Time:  2018/12/22
 * - @Description:  对话框工具类
 */
public class DialogUtil {

    public static Dialog createProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context, R.style.progress_dialog_theme);// Widget.DeviceDefault.Light.ProgressBar.Inverse
        progressDialog.setCancelable(true);
        return progressDialog;
    }

    public static AlertDialog showDialog(Activity context, String title, String msg, String confirm) {
        return showDialog(context, title, msg, confirm, null, null, true);
    }

    public static AlertDialog showDialog(Activity context, String title, String msg, String confirm,
                                         String cancel, final OnCusDialogInterface dialogInterface, boolean isCancel) {
        AlertDialog mAlertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        }
        builder.setCancelable(false);
        builder.setMessage(msg);
        if (confirm != null) {
            builder.setPositiveButton(confirm, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (dialogInterface != null) {
                        dialogInterface.onConfirmClick();
                    }
                }
            });
        }
        if (cancel != null) {
            builder.setNegativeButton(cancel, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (dialogInterface != null) {
                        dialogInterface.onCancelClick();
                    }
                }
            });
        }
        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(isCancel);
        if (!context.isFinishing()) {
            mAlertDialog.show();
        }
        return mAlertDialog;
    }

    public interface OnCusDialogInterface {

        void onConfirmClick();

        void onCancelClick();
    }
}
