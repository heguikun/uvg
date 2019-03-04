package com.uvgouapp.common.util;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;

public class ClipboardUtil {

    /**
     * @param context 上下文
     * @param content 复制
     */
    public static void copy(Context context, String content) {
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("", content);
        if (cmb != null) {
            cmb.setPrimaryClip(clipData);
            ToastUtils.showShort("复制成功");
        }
    }

    /**
     * @param context 上下文
     * @return 粘贴
     */
    public static String paste(Context context) {
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (cmb != null) {
            ClipData clipData = cmb.getPrimaryClip();
            if (clipData != null) {
                return clipData.toString().trim();
            }
        }
        return "";
    }
}
