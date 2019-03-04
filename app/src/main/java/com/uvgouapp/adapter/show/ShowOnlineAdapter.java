package com.uvgouapp.adapter.show;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.uvgouapp.R;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.model.show.ShowOnlineBean;
import com.uvgouapp.ui.circle.CircleInformationActivity;

import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.annotation.NonNull;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * - @Author:  ying
 * - @Time:  2019/1/11
 * - @Description:  在线
 */
public class ShowOnlineAdapter extends BaseQuickAdapter<ShowOnlineBean.MapListBean, BaseViewHolder> {

    public ShowOnlineAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        CircleImageView ivHead = holder.getView(R.id.iv_head);
        if (ivHead != null) {
            ImageLoaderUtil.clearImageView(mContext, ivHead);
            ImageLoaderUtil.GuideClearMemory(mContext);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, ShowOnlineBean.MapListBean item) {

        //-----------------------用户头像 用户名 时间------------------------
        TextView tvName = helper.getView(R.id.tv_nickname);
        CircleImageView ivHead = helper.getView(R.id.iv_head);
        TextView tvTime = helper.getView(R.id.tv_time);

        String byname = item.getName();
        String headImg = item.getHeadImg();
        String dist = item.getDist();
        long modifyTime = item.getModifyTime();

        if (!StringUtils.isEmpty(byname)) {
            tvName.setText(byname);
        }

        if (!StringUtils.isEmpty(headImg)) {
            ImageLoaderUtil.into(mContext, headImg, ivHead);
        }

        if (!StringUtils.isEmpty(dist)) {
            tvTime.setText(String.format(Locale.ENGLISH, "%skm", dist));
        } else {
            String date = TimeUtils.millis2String(modifyTime, new SimpleDateFormat("MM-dd", Locale.ENGLISH));
            tvTime.setText(date);
        }

        int position = helper.getLayoutPosition();
        CircleImageView circleImageView = helper.getView(R.id.iv_head);

        if (position == 0 || position == 2) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(circleImageView.getLayoutParams());
            lp.setMargins(0, 100, 0, 0);
            circleImageView.setLayoutParams(lp);
        } else {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(circleImageView.getLayoutParams());
            lp.setMargins(0, 0, 0, 0);
            circleImageView.setLayoutParams(lp);
        }
        //当第二张图片设置图片宽度黄色
        if (position == 1) {
            circleImageView.setBorderWidth(6);
            circleImageView.setBorderColor(mContext.getResources().getColor(R.color.color_ffff00));
        } else {
            circleImageView.setBorderWidth(0);
            circleImageView.setBorderColor(mContext.getResources().getColor(R.color.transparent));
        }

        //-----------------------头像点击事件--------------------
        ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = item.getId();
                Intent intent = new Intent(mContext, CircleInformationActivity.class);
                intent.putExtra("userId", String.valueOf(userId));
                mContext.startActivity(intent);
            }
        });
    }

}
