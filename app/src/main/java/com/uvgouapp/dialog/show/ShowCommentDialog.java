package com.uvgouapp.dialog.show;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.uvgouapp.R;
import com.uvgouapp.adapter.show.ShowCommentAdapter;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.model.other.InfoBean;
import com.uvgouapp.model.show.ShowCommentReplyBean;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/1/9
 * - @Description:  秀场评论
 */
public class ShowCommentDialog extends BasePopupWindow implements View.OnClickListener {

    private TextView mTvNoComment = null;
    private Context mContext;
    private int messageCount;//评论数量
    private String id;
    private ShowCommentAdapter mShowCommentAdapter = null;
    private RecyclerView mRecyclerView = null;
    private EditText mEtComment = null;//评论

    public ShowCommentDialog(Context context, int messageCount, String id) {
        super(context);
        this.mContext = context;
        this.messageCount = messageCount;
        this.id = id;

        initView();
        initData();
    }

    private void initView() {
        mTvNoComment = findViewById(R.id.tv_no_comment);
        mEtComment = findViewById(R.id.et_comment);
        TextView tvCommentNumber = findViewById(R.id.tv_comment_number);
        mRecyclerView = findViewById(R.id.recyclerView);
        mShowCommentAdapter = new ShowCommentAdapter(R.layout.item_show_comment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mShowCommentAdapter);

        tvCommentNumber.setText(String.format(Locale.ENGLISH, "%d条评论", messageCount));
        findViewById(R.id.btn_send).setOnClickListener(this);
        findViewById(R.id.iv_close).setOnClickListener(this);
    }

    private void initData() {
        String userId = SPUtils.getInstance().getString("userId");
        //-----------------------------秀场评论----------------------------
        OkGo.<String>get(Api.SHOW_COMMENT_REPLY)
                .params("commentType", Constants.SHOW_GROUND)
                .params("userId", userId)
                .params("entityId", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        ShowCommentReplyBean showCommentReplyBean = GsonUtil.GsonToBean(body, ShowCommentReplyBean.class);
                        if (showCommentReplyBean != null) {
                            int statusCode = showCommentReplyBean.getStatusCode();
                            if (statusCode == Constants.SUCCESS_CODE) {
                                ShowCommentReplyBean.DataBean data = showCommentReplyBean.getData();
                                if (data != null) {
                                    List<ShowCommentReplyBean.DataBean.RecordsBeanComment> records = data.getRecords();
                                    if (records != null) {
                                        int size = records.size();
                                        if (size > 0) {
                                            mShowCommentAdapter.addData(records);
                                            mShowCommentAdapter.notifyDataSetChanged();
                                            mRecyclerView.setVisibility(View.VISIBLE);
                                            mTvNoComment.setVisibility(View.GONE);
                                        } else {
                                            mRecyclerView.setVisibility(View.GONE);
                                            mTvNoComment.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            } else {
                                ToastUtils.showShort("请求失败");
                            }
                        }
                    }
                });

    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_show_comment);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send://发送评论
                String content = mEtComment.getText().toString().trim();
                if (!StringUtils.isEmpty(content)) {
                    dismiss();
                    sendAddComment(EncodeUtils.urlEncode(content, "UTF-8"));
                } else {
                    ToastUtils.showShort("评论内容不能为空...");
                }
                break;
            case R.id.iv_close://关闭
                dismiss();
                break;
            default:
                break;
        }
    }

    /**
     * 发送评论内容
     *
     * @param content 内容`
     */
    private void sendAddComment(String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        map.put("entityId", id);
        map.put("replyType", Constants.SHOW_GROUND);
        map.put("userId", SPUtils.getInstance().getString("userId"));
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.ADD_COMMENT_INFO)
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
                                        List<ShowCommentReplyBean.DataBean.RecordsBeanComment> data = mShowCommentAdapter.getData();
                                        ShowCommentReplyBean.DataBean.RecordsBeanComment recordsBeanComment = new ShowCommentReplyBean.DataBean.RecordsBeanComment();
                                        //----------------用户头像,内容,用户名,时间日期--------------
                                        recordsBeanComment.setHeadImg(SPUtils.getInstance().getString("headImg"));
                                        recordsBeanComment.setContent(content);
                                        recordsBeanComment.setUserName(SPUtils.getInstance().getString("name"));
                                        recordsBeanComment.setCreateTime(TimeUtils.getNowMills());
                                        data.add(recordsBeanComment);
                                        mShowCommentAdapter.notifyDataSetChanged();
                                    }
                                }
                            }
                        }
                    }
                });
    }
}
