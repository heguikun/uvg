package com.uvgouapp.adapter.show;

import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.uvgouapp.R;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.model.other.InfoBean;
import com.uvgouapp.model.show.ShowCommentReplyBean;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * - @Author:  ying
 * - @Time:  2019/1/15
 * - @Description:秀场评论
 */
public class ShowCommentAdapter extends BaseQuickAdapter<ShowCommentReplyBean.DataBean.RecordsBeanComment, BaseViewHolder> {

    public ShowCommentAdapter(int layoutResId) {
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
    protected void convert(BaseViewHolder helper, ShowCommentReplyBean.DataBean.RecordsBeanComment item) {
        //------------------------评论---------------------------------
        CircleImageView ivHead = helper.getView(R.id.iv_head);
        TextView tvGive = helper.getView(R.id.tv_give);
        TextView tvNickname = helper.getView(R.id.tv_nickname);
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvContent = helper.getView(R.id.tv_content);

        //---------------用户头像----------------
        String headImg = item.getHeadImg();
        if (!StringUtils.isEmpty(headImg)) {
            ImageLoaderUtil.into(mContext, headImg, ivHead);
        }

        Object thumbs = item.getThumbs();//是否点赞
        int thumbsCount = item.getThumbsCount();//点赞数量
        if (thumbs != null) {//是
            tvGive.setSelected(true);
        } else {//否
            tvGive.setSelected(false);
        }
        if (thumbsCount > 10000) {
            float count = thumbsCount / 10000.f;
            tvGive.setText(String.format(Locale.ENGLISH, "%.1fw", count));
        } else {
            tvGive.setText(String.format(Locale.ENGLISH, "%d", thumbsCount));
        }

        tvGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //-----------判断用户是否点赞  判空-----------------
                Object thumbs = item.getThumbs();
                if (thumbs != null) {//有
                    tvGive.setSelected(true);
                    ToastUtils.showShort("已经点赞成功");
                } else {//否
                    tvGive.setSelected(true);
                    String userId = SPUtils.getInstance().getString("userId");
                    requestGive(helper.getLayoutPosition(), String.valueOf(item.getCommentId()), Constants.COMMENT, userId);
                }
            }
        });

        String thisName = item.getUserName();//评论用户名称
        String thisContent = item.getContent();//评论内容
        long createTime = item.getCreateTime();//评论创建时间

        if (!StringUtils.isEmpty(thisName)) {
            tvNickname.setText(thisName);
        }
        if (!StringUtils.isEmpty(thisContent)) {
            tvContent.setText(EncodeUtils.urlDecode(thisContent, "UTF-8"));
        }
        if (createTime > 0) {
            String date = TimeUtils.millis2String(createTime, new SimpleDateFormat("MM-dd", Locale.ENGLISH));
            tvTime.setText(date);
        }

        //-----------------------------------回复------------------------------
        ShowCommentReplyBean.DataBean.RecordsBeanComment.ReplyResultListBean replyResultList = item.getReplyResultList();
        if (replyResultList != null) {
            List<ShowCommentReplyBean.DataBean.RecordsBeanComment.ReplyResultListBean.RecordsBeanReply> list = replyResultList.getRecords();
            if (list != null) {
                int size = list.size();
                if (size > 0) {
                    RecyclerView recyclerView = helper.getView(R.id.rv_reply);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                    ShowReplyAdapter showReplyAdapter = new ShowReplyAdapter(list);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(showReplyAdapter);
                    showReplyAdapter.addData(list);
                    showReplyAdapter.notifyDataSetChanged();
                }
            }
        }

    }


    /**
     * @param position   当前位置
     * @param isThumbsId 被点赞信息id
     * @param thumbsType 评论  4
     * @param userId     用户id
     */
    private void requestGive(int position, String isThumbsId, int thumbsType, String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("isThumbsId", isThumbsId);
        map.put("thumbsType", thumbsType);
        map.put("userId", userId);
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.SHOW_GIVE_THUMBS_UP)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                InfoBean infoBean = GsonUtil.GsonToBean(body, InfoBean.class);
                                if (infoBean != null) {
                                    int statusCode = infoBean.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        ShowCommentReplyBean.DataBean.RecordsBeanComment recordsBeanComment = getData().get(position);
                                        recordsBeanComment.setThumbs(true);
                                        notifyItemChanged(position);
                                    }
                                }
                            }
                        }

                    }
                });
    }

}
