package com.uvgouapp.adapter.user;


import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.uvgouapp.R;

/**
 * - @Author:  ying
 * - @Time:  2019/2/28
 * - @Description:  身高
 */
public class HeightAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HeightAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        TextView tvHeight = helper.getView(R.id.tv_height);
        if (!StringUtils.isEmpty(item)) {
            tvHeight.setText(item);
        }
    }
}
