package com.uvgouapp.dialog.circle;

import android.content.Context;
import android.view.View;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.uvgouapp.R;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.common.util.PermissionsUtil;

import io.reactivex.functions.Consumer;
import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/1/4
 * - @Description:  添加图片
 */
public class CircleAddPhotoDialog extends BasePopupWindow implements View.OnClickListener {

    private BaseActivity mBaseActivity;
    private int size;

    public CircleAddPhotoDialog(Context context, int size) {
        super(context);
        this.mBaseActivity = (BaseActivity) context;
        this.size = size;
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_take_photo).setOnClickListener(this);
        findViewById(R.id.tv_photo_selection).setOnClickListener(this);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_circle_add_photo);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_take_photo://拍照
                dismiss();
                PermissionsUtil.checkCameraPermissions(mBaseActivity, new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            PictureSelector.create(mBaseActivity)
                                    .openCamera(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll() 图片.ofImage() 视频.ofVideo() 音频.ofAudio()
                                    .isCamera(true)//是否显示拍照按钮 true or false
                                    .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                                    .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                                    //.enableCrop(true)// 是否裁剪 true or false
                                    .compress(true)// 是否压缩 true or false
                                    .cropCompressQuality(10)// 裁剪压缩质量 默认90 int
                                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        }
                    }
                });
                break;
            case R.id.tv_photo_selection://从相册选择
                dismiss();
                PermissionsUtil.checkFileReadWritePermissions(mBaseActivity, new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            PictureSelector.create(mBaseActivity)
                                    .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll() 图片.ofImage() 视频.ofVideo() 音频.ofAudio()
                                    .maxSelectNum(size)//最大图片选择数量
                                    .minimumCompressSize(1)//最小选择数量
                                    .imageSpanCount(4)//每行显示个数
                                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                                    .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                                    //.enableCrop(true)//是否裁剪
                                    .compress(true)//是否压缩 true or false
                                    .cropCompressQuality(10)// 裁剪压缩质量 默认90
                                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        }
                    }
                });
                break;
            default:
                break;
        }
    }
}
