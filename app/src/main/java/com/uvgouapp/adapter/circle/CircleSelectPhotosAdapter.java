package com.uvgouapp.adapter.circle;

import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.luck.picture.lib.entity.LocalMedia;
import com.uvgouapp.R;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.dialog.circle.CircleAddPhotoDialog;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * - @Author:  ying
 * - @Time:  2019/1/5
 * - @Description:  选择照片
 */
public class CircleSelectPhotosAdapter extends BaseQuickAdapter<LocalMedia, BaseViewHolder> {

    public static final int TYPE_CAMERA = 1;
    public static final int TYPE_PICTURE = 2;

    public CircleSelectPhotosAdapter(List<LocalMedia> data) {
        super(R.layout.item_shop_live_photo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LocalMedia item) {

        int position = helper.getAdapterPosition();
        ImageView ivDel = helper.getView(R.id.iv_del);
        ImageView imageView = helper.getView(R.id.iv_photo);

        if (getItemViewType(position) == TYPE_CAMERA) {
            imageView.setBackgroundResource(R.drawable.circle_fb_add);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //添加图片  最多9张图片
                    CircleAddPhotoDialog circleAddPhotoDialog = new CircleAddPhotoDialog(mContext, 9 - getData().size());
                    circleAddPhotoDialog.showPopupWindow();
                }
            });
            ivDel.setVisibility(View.INVISIBLE);
        } else {
            ivDel.setVisibility(View.VISIBLE);
            ivDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != RecyclerView.NO_POSITION) {
                        getData().remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, getData().size());
                    }
                }
            });

            String path;
            if (item.isCut() && !item.isCompressed()) {
                // 裁剪过
                path = item.getCutPath();
            } else if (item.isCompressed() || (item.isCut() && item.isCompressed())) {
                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                path = item.getCompressPath();
            } else {
                // 原图
                path = item.getPath();
            }
            if (!StringUtils.isEmpty(path)) {
                ImageLoaderUtil.into(mContext, path, imageView);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (getData().size() < 9) {
            return getData().size() + 1;
        } else {
            return getData().size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowAddItem(position)) {
            return TYPE_CAMERA;
        } else {
            return TYPE_PICTURE;
        }
    }

    private boolean isShowAddItem(int position) {
        int size = getData().size() == 0 ? 0 : getData().size();
        return position == size;
    }
}
