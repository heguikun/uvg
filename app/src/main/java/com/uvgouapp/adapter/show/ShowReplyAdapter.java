package com.uvgouapp.adapter.show;

import android.widget.TextView;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.uvgouapp.R;
import com.uvgouapp.model.show.ShowCommentReplyBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * - @Author:  ying
 * - @Time:  2019/1/15
 * - @Description:  秀场评论回复
 */
public class ShowReplyAdapter extends BaseQuickAdapter<ShowCommentReplyBean.DataBean.RecordsBeanComment.ReplyResultListBean.RecordsBeanReply, BaseViewHolder> {

    public ShowReplyAdapter(@Nullable List<ShowCommentReplyBean.DataBean.RecordsBeanComment.ReplyResultListBean.RecordsBeanReply> data) {
        super(R.layout.item_show_reply, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShowCommentReplyBean.DataBean.RecordsBeanComment.ReplyResultListBean.RecordsBeanReply item) {

        String thisName = item.getUserName();//当前评论用户名
        String thisContent = item.getContent();//当前评论内容

        TextView tvNickname = helper.getView(R.id.tv_nickname);//用户名

        TextView tvReply = helper.getView(R.id.tv_reply);//回复评论

        if (!StringUtils.isEmpty(thisName)) {
            tvNickname.setText(thisName);
        }

        if (!StringUtils.isEmpty(thisContent)) {
            tvReply.setText(EncodeUtils.urlDecode(thisContent, "UTF-8"));
        }

    }
}
