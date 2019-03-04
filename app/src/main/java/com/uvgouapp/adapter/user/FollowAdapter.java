package com.uvgouapp.adapter.user;


import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.daimajia.swipe.SwipeLayout;
import com.uvgouapp.R;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.model.user.FollowFriendsBean;
import com.uvgouapp.presenter.user.FollowPresenter;

import androidx.annotation.NonNull;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * - @Author:  ying
 * - @Time:  2019/1/26
 * - @Description:
 */
public class FollowAdapter extends BaseQuickAdapter<FollowFriendsBean.MapListBean, BaseViewHolder> {

    private FollowPresenter mPresenter;

    public FollowAdapter(int layoutResId) {
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
    protected void convert(BaseViewHolder helper, FollowFriendsBean.MapListBean item) {

        SwipeLayout swipeLayout = helper.getView(R.id.swipe_layout);
        swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        CircleImageView ivHead = helper.getView(R.id.iv_head);
        TextView tvNickname = helper.getView(R.id.tv_nickname);

        String uHeadImg = item.getUHeadImg();
        String uName = item.getUName();
        if (!StringUtils.isEmpty(uHeadImg)) {
            ImageLoaderUtil.into(mContext, uHeadImg, ivHead);
        }
        if (!StringUtils.isEmpty(uName)) {
            tvNickname.setText(uName);
        }

        //--------------------点击事件-----------
        helper.getView(R.id.ll_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //----------取消关注------------
                String solicitudeId = item.getSolicitudeId();
                String userId = SPUtils.getInstance().getString("userId");
                mPresenter.requestCancleFollow(userId, solicitudeId);
                int position = helper.getLayoutPosition();
                getData().remove(position);
                notifyDataSetChanged();
            }
        });

        helper.getView(R.id.iv_news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwipeLayout.Status openStatus = swipeLayout.getOpenStatus();
                switch (openStatus) {
                    case Open: //打开状态
                        swipeLayout.close(true);
                        break;
                    case Close://关闭状态
                        swipeLayout.open(true);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void setPresenter(FollowPresenter presenter) {
        this.mPresenter = presenter;
    }
}
