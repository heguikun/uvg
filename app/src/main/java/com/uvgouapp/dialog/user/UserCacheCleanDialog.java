package com.uvgouapp.dialog.user;


import android.content.Context;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.uvgouapp.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/1/24
 * - @Description: 清理缓存
 */
public class UserCacheCleanDialog extends BasePopupWindow implements View.OnClickListener {

    public UserCacheCleanDialog(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        //获取缓存大小
        //long cacheSize = CacheDiskUtils.getInstance().getCacheSize();
        //TextView tvContent = findViewById(R.id.tv_content);
        //tvContent.setText(String.format(Locale.ENGLISH, "存在%.2fM的缓存，是否立即清除？", cacheSize / 1.0f));

        findViewById(R.id.tv_yes).setOnClickListener(this);
        findViewById(R.id.tv_no).setOnClickListener(this);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_mine_clean_cache);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_yes://马上清理
                dismiss();
                ToastUtils.showShort("清除成功");
                break;
            case R.id.tv_no://以后再说
                dismiss();
                break;
            default:
                break;
        }

    }
}
