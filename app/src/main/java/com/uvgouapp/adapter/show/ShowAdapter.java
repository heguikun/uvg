package com.uvgouapp.adapter.show;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.uvgouapp.R;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.dialog.show.ShowAddFriendsDialog;
import com.uvgouapp.dialog.show.ShowCommentDialog;
import com.uvgouapp.dialog.show.ShowShareDialog;
import com.uvgouapp.model.show.ShowBean;
import com.uvgouapp.presenter.show.ShowPresenter;
import com.uvgouapp.ui.circle.CircleInformationActivity;
import com.uvgouapp.ui.show.ShowOnlineActivity;
import com.uvgouapp.view.PagingScrollHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * - @Author:  ying
 * - @Time:  2019/1/2
 * - @Description: 秀场
 */
public class ShowAdapter extends BaseQuickAdapter<ShowBean.MapListBean, BaseViewHolder> implements View.OnClickListener {

    private ShowPresenter mShowPresenter = null;

    public ShowAdapter(int layoutResId) {
        super(layoutResId);
    }

    public void setShowPresenter(ShowPresenter showPresenter) {
        this.mShowPresenter = showPresenter;
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        CircleImageView headIv = holder.getView(R.id.iv_head_portrait);
        if (headIv != null) {
            ImageLoaderUtil.clearImageView(mContext, headIv);
            ImageLoaderUtil.GuideClearMemory(mContext);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, ShowBean.MapListBean item) {
        //-------------------秀场图片----------------------
        RecyclerView recyclerView = helper.getView(R.id.recyclerview);
        PagingScrollHelper pagingScrollHelper = new PagingScrollHelper();
        ShowPhotoAdapter showPhotoAdapter = new ShowPhotoAdapter(R.layout.item_show_photo);
        LinearLayoutManager layoutManagerH = new LinearLayoutManager(mContext);
        layoutManagerH.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManagerH);
        recyclerView.setAdapter(showPhotoAdapter);
        pagingScrollHelper.setUpRecycleView(recyclerView);
        pagingScrollHelper.updateLayoutManger();
        pagingScrollHelper.scrollToPosition(0);
        recyclerView.setHorizontalScrollBarEnabled(true);

        String imgUrl = item.getImgUrl();
        if (!StringUtils.isEmpty(imgUrl)) {
            String[] split = imgUrl.split(",");
            List<String> list = Arrays.asList(split);
            showPhotoAdapter.addData(list);
            showPhotoAdapter.notifyDataSetChanged();
        }

        //---------------------头像,用户名,内容,距离---------------------
        CircleImageView headIv = helper.getView(R.id.iv_head_portrait);
        ImageView ivAdd = helper.getView(R.id.iv_add);
        TextView tvNickname = helper.getView(R.id.tv_nickname);
        TextView tvDetails = helper.getView(R.id.tv_details);
        TextView tvAddress = helper.getView(R.id.tv_address);

        String uHeadImg = item.getUHeadImg();
        String uName = item.getUName();
        String content = item.getContent();
        String dist = item.getDist();
        String positionName = item.getPositionName();

        if (!StringUtils.isEmpty(uHeadImg)) {
            ImageLoaderUtil.into(mContext, uHeadImg, headIv);
        }
        if (!StringUtils.isEmpty(uName)) {
            tvNickname.setText(uName);
        }
        if (!StringUtils.isEmpty(content)) {
            tvDetails.setText(EncodeUtils.urlDecode(content, "UTF-8"));
        }
        if (!StringUtils.isEmpty(positionName)) {
            tvAddress.setVisibility(View.VISIBLE);
            int index = positionName.indexOf("区");
            String substring = positionName.substring(0, index + 1);
            tvAddress.setText(String.format(Locale.ENGLISH, "%s%skm", substring, dist));
        } else {
            tvAddress.setVisibility(View.GONE);
        }

        //-------------------分享数----------------------------
        //TextView tvShare = helper.getView(R.id.tv_share);
        //int uweShareCount = item.getUweShareCount();
        //if (uweShareCount > 10000) {
        //float shareCount = uweShareCount / 10000.0f;
        //tvShare.setText(String.format(Locale.ENGLISH, "%.1fw", shareCount));
        //} else {
        //tvShare.setText(String.format(Locale.ENGLISH, "%d", uweShareCount));
        //}

        //-------------------点赞数---------------------
        TextView tvGive = helper.getView(R.id.tv_give);
        int thumbsCount = item.getThumbsCount();
        if (thumbsCount > 10000) {
            float count = thumbsCount / 10000.0f;
            tvGive.setText(String.format(Locale.ENGLISH, "%.1fw", count));
        } else {
            tvGive.setText(String.format(Locale.ENGLISH, "%d", thumbsCount));
        }

        //------------------判断用户是否关注-------------------
        if (item.getsId() != null) {
            ivAdd.setVisibility(View.GONE);
        } else {
            ivAdd.setVisibility(View.VISIBLE);
        }

        //-----------判断用户是否点赞  判空-----------------
        if (item.getTId() != null) {//有
            tvGive.setSelected(true);
        } else {//没有
            tvGive.setSelected(false);
        }

        //--------------------评论数--------------------------
        TextView tvComment = helper.getView(R.id.tv_comment);
        int messageCount = item.getMessageCount();//评论数
        if (messageCount > 10000) {
            float count = messageCount / 10000.0f;
            tvComment.setText(String.format(Locale.ENGLISH, "%.1fw", count));
        } else {
            tvComment.setText(String.format(Locale.ENGLISH, "%d", messageCount));
        }

        //-----------------------------关注--------------------------
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = helper.getLayoutPosition();
                ShowBean.MapListBean mapListBean = getData().get(position);
                String userId = mapListBean.getUserId();//关注用户人id
                if (userId.equals(SPUtils.getInstance().getString("userId"))) {
                    ToastUtils.showShort("不能关注自己");
                } else {
                    Object sId = mapListBean.getsId();
                    if (sId != null) {//判断是否关注
                        ToastUtils.showShort("已经关注成功");
                        ivAdd.setVisibility(View.GONE);
                    } else {
                        ivAdd.setVisibility(View.VISIBLE);
                        if (mShowPresenter != null)
                            mShowPresenter.requestFollow(helper.getLayoutPosition(), mapListBean.getUserId(), SPUtils.getInstance().getString("userId"));
                    }
                }
            }
        });
        //---------------------------头像------------------------
        headIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = helper.getLayoutPosition();
                ShowBean.MapListBean mapListBean = getData().get(position);
                String userId = mapListBean.getUserId();//用户人id
                Intent intent = new Intent(mContext, CircleInformationActivity.class);
                intent.putExtra("userId", userId);
                mContext.startActivity(intent);
            }
        });
        helper.getView(R.id.tv_online).setOnClickListener(this);
        //-------------------------点赞------------------------
        tvGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //-----------判断用户是否点赞  判空-----------------
                int position = helper.getLayoutPosition();
                ShowBean.MapListBean mapListBean = getData().get(position);
                if (mapListBean.getTId() != null) {//有
                    tvGive.setSelected(true);
                    ToastUtils.showShort("已经点赞成功");
                } else {//没有
                    tvGive.setSelected(true);
                    if (mShowPresenter != null) {
                        int id = mapListBean.getId();
                        String userId = SPUtils.getInstance().getString("userId");
                        mShowPresenter.requestGive(position, String.valueOf(id), Constants.SHOW_GROUND, userId);
                    }
                }
            }
        });
        //------------------------分享---------------------
        helper.getView(R.id.tv_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = item.getId();
                ShowShareDialog showShareDialog = new ShowShareDialog(mContext, id, 1);
                showShareDialog.showPopupWindow();
            }
        });
        helper.getView(R.id.tv_add_friends).setOnClickListener(this);
        helper.getView(R.id.tv_details).setOnClickListener(this);
        helper.getView(R.id.tv_purchase).setOnClickListener(this);
        //-----------------------评论点击事件----------------
        tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int messageCount = item.getMessageCount();
                String id = String.valueOf(item.getId());
                ShowCommentDialog showCommentDialog = new ShowCommentDialog(mContext, messageCount, id);
                showCommentDialog.showPopupWindow();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_online://在线
                mContext.startActivity(new Intent(mContext, ShowOnlineActivity.class));
                break;
            case R.id.tv_add_friends://加好友
                ShowAddFriendsDialog showAddFriendsDialog = new ShowAddFriendsDialog(mContext);
                showAddFriendsDialog.showPopupWindow();
                break;
            case R.id.tv_details://详情
                ToastUtils.showShort("详情");
                break;
            case R.id.tv_purchase://购买
                ToastUtils.showShort("购买");
                break;
            default:
                break;
        }
    }

}
