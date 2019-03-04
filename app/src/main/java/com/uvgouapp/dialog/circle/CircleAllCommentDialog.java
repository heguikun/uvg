package com.uvgouapp.dialog.circle;


import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.EncodeUtils;
import com.uvgouapp.R;
import com.uvgouapp.model.circle.CommentConfig;
import com.uvgouapp.presenter.circle.CirclePresenter;

import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/1/28
 * - @Description:  圈的全部评论
 */
public class CircleAllCommentDialog extends BasePopupWindow {

    private CirclePresenter presenter;
    private CommentConfig commentConfig;

    public CircleAllCommentDialog(Context context, CirclePresenter presenter, CommentConfig commentConfig) {
        super(context);
        this.presenter = presenter;
        this.commentConfig = commentConfig;
        initView();
    }

    private void initView() {
        EditText etContent = findViewById(R.id.et_comment);

        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter != null) {
                    //发表评论
                    String content = etContent.getText().toString().trim();
                    presenter.replyComment(EncodeUtils.urlEncode(content, "UTF-8"), commentConfig);
                    dismiss();
                }
            }
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_circle_comment);
    }
}
