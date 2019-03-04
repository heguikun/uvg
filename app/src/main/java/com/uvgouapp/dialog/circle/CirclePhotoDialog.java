package com.uvgouapp.dialog.circle;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.chrisbanes.photoview.PhotoView;
import com.uvgouapp.R;
import com.uvgouapp.common.util.GlideApp;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2018/12/30
 * - @Description:  淘友圈图片查看
 */
public class CirclePhotoDialog extends BasePopupWindow {

    private ArrayList<View> mViews;

    private List<String> mList;

    private int position;

    public CirclePhotoDialog(Context context, int width, int height, List<String> list, int position) {
        super(context, width, height);
        mViews = new ArrayList<>();
        mList = new ArrayList<>();
        mList = list;
        this.position = position;
        initView();
    }

    private void initView() {
        ViewPager viewPager = findViewById(R.id.viewpager);
        LinearLayout linearLayout = findViewById(R.id.ll_circle_dot);

        CircleDialogPhotoAdapter photoAdapter = new CircleDialogPhotoAdapter(getContext());
        photoAdapter.setList(mList);
        viewPager.setAdapter(photoAdapter);

        viewPager.setCurrentItem(position);

        addGuideView(linearLayout, position, mList);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mViews.size(); i++) {
                    if (i == position) {
                        mViews.get(i).setSelected(true);
                    } else {
                        mViews.get(i).setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void addGuideView(LinearLayout linearLayout, int position, List<String> list) {
        if (list != null && list.size() > 0) {
            mViews.clear();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view = new View(getContext());
                view.setBackgroundResource(R.drawable.selector_bg_dot);
                if (i == position) {
                    view.setSelected(true);
                } else {
                    view.setSelected(false);
                }
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getContext().getResources().getDimensionPixelSize(R.dimen.dp_6), getContext().getResources().getDimensionPixelSize(R.dimen.dp_6));
                layoutParams.setMargins(20, 0, 0, 0);
                linearLayout.addView(view, layoutParams);
                mViews.add(view);
            }
        }
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_circle_photo);
    }

    public class CircleDialogPhotoAdapter extends PagerAdapter {

        private Context mContext;
        private LayoutInflater inflater;
        private List<String> mList;

        public CircleDialogPhotoAdapter(Context context) {
            mList = new ArrayList<>();
            this.mContext = context;
            this.inflater = LayoutInflater.from(context);
        }

        public void setList(List<String> list) {
            if (list != null)
                this.mList = list;
        }

        @Override
        public int getCount() {
            return mList != null ? mList.size() : 0;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = inflater.inflate(R.layout.dialog_pager_image, container, false);
            if (view != null) {
                FrameLayout frameLayout = view.findViewById(R.id.fl_root);
                PhotoView photoView = view.findViewById(R.id.photo_view);
                //点击事件
                frameLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CirclePhotoDialog.this.dismiss();
                    }
                });
                photoView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CirclePhotoDialog.this.dismiss();
                    }
                });

                if (mList != null) {
                    //预览imageView
                    PhotoView imageView = new PhotoView(mContext);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(imageView.getWidth(), imageView.getHeight());
                    layoutParams.gravity = Gravity.CENTER;
                    imageView.setLayoutParams(layoutParams);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    ((FrameLayout) view).addView(imageView);
                }

                //loading
                final ProgressBar loading = new ProgressBar(mContext);
                FrameLayout.LayoutParams loadingLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                loadingLayoutParams.gravity = Gravity.CENTER;
                loading.setLayoutParams(loadingLayoutParams);
                ((FrameLayout) view).addView(loading);

                String photoInfo = mList.get(position);

                GlideApp.with(mContext)
                        .load(photoInfo)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//缓存多个尺寸
                        .thumbnail(0.1f)//先显示缩略图  缩略图为原图的1/10
                        .format(DecodeFormat.PREFER_RGB_565)
                        .skipMemoryCache(true)
                        .error(R.mipmap.ic_launcher)
                        .priority(Priority.HIGH)//下载的优先级
                        .into(new DrawableImageViewTarget(photoView) {
                            @Override
                            public void onLoadStarted(Drawable placeholder) {
                                super.onLoadStarted(placeholder);
                                loading.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onLoadFailed(Drawable errorDrawable) {
                                super.onLoadFailed(errorDrawable);
                                loading.setVisibility(View.GONE);
                            }

                            @Override
                            public void onResourceReady(@NonNull Drawable resource, Transition<? super Drawable> transition) {
                                super.onResourceReady(resource, transition);
                                loading.setVisibility(View.GONE);
                            }
                        });

                container.addView(view, 0);
            }
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view.equals(object);
        }
    }

}
