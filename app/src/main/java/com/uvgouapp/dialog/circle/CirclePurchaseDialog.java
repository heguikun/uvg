package com.uvgouapp.dialog.circle;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.uvgouapp.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/1/7
 * - @Description:  购买
 */
public class CirclePurchaseDialog extends BasePopupWindow implements View.OnClickListener {

    public CirclePurchaseDialog(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        TextView tvName = findViewById(R.id.tv_name);//收件人姓名
        TextView tvPhone = findViewById(R.id.tv_phone);//联系方式
        TextView tvAddress = findViewById(R.id.tv_address);//收获地址
        EditText etNumber = findViewById(R.id.et_number);//输入数量
        TextView tvShopPrice = findViewById(R.id.tv_shop_price);//商品价格
        TextView tvFreight = findViewById(R.id.tv_freight);//运费价格
        TextView tvTotalPrice = findViewById(R.id.tv_total_price);//总计价格

        //---------------------点击事件---------------------------
        findViewById(R.id.tv_modify_address).setOnClickListener(this);//修改收获地址
        findViewById(R.id.iv_add).setOnClickListener(this);//加号
        findViewById(R.id.iv_reduce).setOnClickListener(this);//减号
        findViewById(R.id.tv_cancel).setOnClickListener(this);//取消
        findViewById(R.id.tv_ok).setOnClickListener(this);//购买
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_circle_purchase);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_modify_address://修改收获地址
                ToastUtils.showShort("修改收获地址");
                break;
            case R.id.iv_add://加号
                ToastUtils.showShort("加号");
                break;
            case R.id.iv_reduce://减号
                ToastUtils.showShort("减号");
                break;
            case R.id.tv_cancel://取消
                dismiss();
                break;
            case R.id.tv_ok://购买
                ToastUtils.showShort("购买");
                break;
            default:
                break;
        }
    }
}
