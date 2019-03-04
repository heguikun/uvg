package com.uvgouapp.ui.user;


import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.uvgouapp.R;
import com.uvgouapp.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * - @Author:  ying
 * - @Time:  2019/2/28
 * - @Description:  用户修改信息
 */
public class UserModifyInfoActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;//标题
    @BindView(R.id.btn_ok)
    Button mBtnOk;//保存
    @BindView(R.id.et_user_name)
    EditText mEtUserName;//内容

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_info;
    }

    @Override
    protected void initView() {
        mBtnOk.setAlpha(0.5f);//设置透明
        mBtnOk.setEnabled(false);//不可点击
        Intent intent = getIntent();
        if (intent != null) {
            int code = intent.getIntExtra("code", 0);
            switch (code) {
                case ChangeUserInfoActivity.REQUEST_USER_NAME:
                    String username = intent.getStringExtra("username");
                    mTvTitle.setText("更改用户名");
                    mEtUserName.setText(username);
                    break;
                case ChangeUserInfoActivity.REQUEST_OCCUPATION:
                    String occupation = intent.getStringExtra("occupation");
                    mTvTitle.setText("更改职业");
                    mEtUserName.setText(occupation);
                    break;
            }
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mEtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBtnOk.setAlpha(1.0f);//设置不透明
                mBtnOk.setEnabled(true);//点击
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.btn_ok://保存
                String content = mEtUserName.getText().toString().trim();
                if (!StringUtils.isEmpty(content)) {
                    Intent intent = new Intent();
                    intent.putExtra("content", content);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    ToastUtils.showShort("请输入内容");
                }
                break;
            default:
                break;
        }
    }
}
