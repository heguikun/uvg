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
public class WeightAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public WeightAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        TextView tvWeight = helper.getView(R.id.tv_weight);
        if (!StringUtils.isEmpty(item)) {
            tvWeight.setText(item);
        }
    }
}
