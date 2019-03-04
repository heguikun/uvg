package com.uvgouapp.dialog.circle;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.uvgouapp.R;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.common.base.BaseFragment;
import com.uvgouapp.common.util.PermissionsUtil;

import io.reactivex.functions.Consumer;
import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/1/23
 * - @Description:  更换背景图片
 */
public class CircleReplaceImageDialog extends BasePopupWindow {


    private int type;//1.更换背景 2更换头像

    private BaseActivity mBaseActivity;
    private BaseFragment mBaseFragment;

    public CircleReplaceImageDialog(Context context, BaseFragment baseFragment, int type) {
        super(context);
        this.mBaseActivity = (BaseActivity) context;
        this.mBaseFragment = baseFragment;
        this.type = type;
        initView();
    }

    private void initView() {

        TextView tvShow = findViewById(R.id.tv_show);
        switch (type) {
            case 1:
                tvShow.setText("更换相册封面");
                break;
            case 2:
                tvShow.setText("更换相册头像");
                break;
            default:
                break;
        }

        findViewById(R.id.ll_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                PermissionsUtil.checkFileReadWritePermissions(mBaseActivity, new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            PictureSelector.create(mBaseFragment)//影响回调到哪个地方的onActivityResult()
                                    .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll() 图片.ofImage() 视频.ofVideo() 音频.ofAudio()
                                    .maxSelectNum(1)//最大图片选择数量
                                    .minimumCompressSize(1)//最小选择数量
                                    .imageSpanCount(4)//每行显示个数
                                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                                    .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                                    //.enableCrop(true)//是否裁剪
                                    .compress(true)//是否压缩 true or false
                                    .cropCompressQuality(10)// 裁剪压缩质量 默认90
                                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                                    .forResult(type);//1.更换背景 2更换头像
                        }
                    }
                });
            }
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_circle_replace_bg);
    }
}
