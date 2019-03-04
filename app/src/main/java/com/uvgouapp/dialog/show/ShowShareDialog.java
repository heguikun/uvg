package com.uvgouapp.dialog.show;

import android.content.Context;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.uvgouapp.R;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.common.util.WxShareAndLoginUtil;

import java.util.HashMap;
import java.util.Map;

import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/1/2
 * - @Description:  秀场分享
 */
public class ShowShareDialog extends BasePopupWindow implements View.OnClickListener {

    private int id;

    private int fromType;

    public ShowShareDialog(Context context, int id, int fromType) {
        super(context);
        this.id = id;
        this.fromType = fromType;
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_wechat).setOnClickListener(this);
        findViewById(R.id.tv_friends).setOnClickListener(this);
        // findViewById(R.id.tv_microblog).setOnClickListener(this);
        // findViewById(R.id.tv_qq).setOnClickListener(this);
        // findViewById(R.id.tv_qqzone).setOnClickListener(this);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_show_share);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_wechat://微信好友
                dismiss();
                WxShareAndLoginUtil.WxUrlShare(getContext(), "www.baidu.com", "分享人:" + SPUtils.getInstance().getString("name"), "这里有你想要的", WxShareAndLoginUtil.WECHAT_FRIEND);
                requestShare(1);
                break;
            case R.id.tv_friends://朋友圈
                dismiss();
                WxShareAndLoginUtil.WxUrlShare(getContext(), "www.baidu.com", "分享人:" + SPUtils.getInstance().getString("name"), "这里有你想要的", WxShareAndLoginUtil.WECHAT_MOMENT);
                requestShare(2);
                break;
            //            case R.id.tv_microblog://微博
            //                dismiss();
            //                ToastUtils.showShort("微博");
            //                break;
            //            case R.id.tv_qq://QQ
            //                dismiss();
            //                ToastUtils.showShort("QQ");
            //                break;
            //            case R.id.tv_qqzone://QQ空间
            //                dismiss();
            //                ToastUtils.showShort("QQ空间");
            //                break;
            case R.id.tv_cancel://取消
                dismiss();
                break;
            default:
                break;
        }
    }

    private void requestShare(int shareType) {

        Map<String, Object> map = new HashMap<>();
        map.put("sourceId", String.valueOf(id));
        map.put("fromType", fromType);//1：秀场 2：生活 3：商品
        map.put("shareType", shareType);//分享类型 1：微信 2：朋友圈 3：QQ 4：微博
        map.put("userId", SPUtils.getInstance().getString("userId"));
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.SHOW_SHARE)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }
                });
    }

}
