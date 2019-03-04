package com.uvgouapp.dialog.circle;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import com.uvgouapp.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/1/3
 * - @Description:  收藏成功
 */
public class CircleAddCollectionDialog extends BasePopupWindow {

    private int type;

    public CircleAddCollectionDialog(Context context, int type) {
        super(context);
        this.type = type;
        initView();
    }

    private void initView() {
        TextView tvFavorite = findViewById(R.id.tv_favorite);
        TextView tvShopStock = findViewById(R.id.tv_shop_stock);

        switch (type) {
            case 0://收藏  商品库
                tvFavorite.setVisibility(View.VISIBLE);
                tvShopStock.setVisibility(View.VISIBLE);
                break;
            case 1://收藏
                tvFavorite.setVisibility(View.VISIBLE);
                tvShopStock.setVisibility(View.GONE);
                break;
            case 2://商品库
                tvFavorite.setVisibility(View.GONE);
                tvShopStock.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

        findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_circle_add_collection);
    }
}
