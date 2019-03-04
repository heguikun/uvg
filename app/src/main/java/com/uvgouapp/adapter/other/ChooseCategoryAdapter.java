package com.uvgouapp.adapter.other;


import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.uvgouapp.R;
import com.uvgouapp.model.other.ChooseCategoryBean;

/**
 * - @Author:  ying
 * - @Time:  2019/2/17
 * - @Description:  选择类目
 */
public class ChooseCategoryAdapter extends BaseQuickAdapter<ChooseCategoryBean.DataBean, BaseViewHolder> {

    public ChooseCategoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChooseCategoryBean.DataBean item) {

        TextView tvCateGoryName = helper.getView(R.id.tv_category_name);
        String name = item.getName();
        if (!StringUtils.isEmpty(name)) {
            tvCateGoryName.setText(name);
        }
    }
}
