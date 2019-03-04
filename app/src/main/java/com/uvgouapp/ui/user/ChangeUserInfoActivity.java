package com.uvgouapp.ui.user;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.uvgouapp.R;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.contract.user.ChangeUserInfoContract;
import com.uvgouapp.dialog.user.UserConstellationDialog;
import com.uvgouapp.dialog.user.UserEmotionDialog;
import com.uvgouapp.dialog.user.UserGenderDialog;
import com.uvgouapp.dialog.user.UserHeightDialog;
import com.uvgouapp.dialog.user.UserWeightDialog;
import com.uvgouapp.model.other.CityBean;
import com.uvgouapp.presenter.user.ChangeUserInfoPresenter;
import com.uvgouapp.ui.other.BindingPhoneNumberActivity;
import com.uvgouapp.ui.other.WebViewActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

public class ChangeUserInfoActivity extends BaseActivity<ChangeUserInfoPresenter> implements ChangeUserInfoContract.View {

    @BindView(R.id.iv_head)
    ImageView mIvHead;//头像
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;//用户名
    @BindView(R.id.tv_sex)
    TextView mTvSex;//性别
    @BindView(R.id.tv_address)
    TextView mTvAddress;//地址
    @BindView(R.id.tv_date_of_birth)
    TextView mTvDateOfBirth;//出生年月
    @BindView(R.id.tv_occupation)
    TextView mTvOccupation;//职业
    @BindView(R.id.tv_constellation)
    TextView mTvConstellation;//星座
    @BindView(R.id.tv_height)
    TextView mTvHeight;//身高
    @BindView(R.id.tv_weight)
    TextView mTvWeight;//体重
    @BindView(R.id.tv_emotional_state)
    TextView mTvEmotionalState;//情感状态
    @BindView(R.id.tv_real_name)
    TextView mTvRealName;//真实姓名
    @BindView(R.id.tv_id_card)
    TextView mTvIdCard;//身份证
    @BindView(R.id.tv_phone)
    TextView mTvPhone;//手机
    @BindView(R.id.tv_o2)
    TextView mTvO2;//O2

    public static final int REQUEST_USER_NAME = 0x02;//用户名
    public static final int REQUEST_OCCUPATION = 0x06;//职业
    public static final int REQUEST_PHONE_CODE = 0x12;//手机(验证)

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_user_info;
    }

    @Override
    protected void initView() {
        mPresenter = new ChangeUserInfoPresenter(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        mPresenter.requestQueryUserInfo(SPUtils.getInstance().getString("userId"));
    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.iv_back, R.id.rl_head, R.id.rl_user_name, R.id.rl_sex, R.id.rl_address, R.id.rl_date_of_birth,
            R.id.rl_occupation, R.id.rl_constellation, R.id.rl_height, R.id.rl_weight, R.id.rl_emotional_state,
            R.id.rl_real_name, R.id.rl_id_card, R.id.rl_phone, R.id.rl_o2, R.id.rl_receiving_address,
            R.id.rl_unsubscribe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.rl_head://头像
                mPresenter.replaceUserHeadImg();
                break;
            case R.id.rl_user_name://用户名
                Intent intentUserName = new Intent(this, UserModifyInfoActivity.class);
                intentUserName.putExtra("code", REQUEST_USER_NAME);
                intentUserName.putExtra("username", mTvUserName.getText().toString().trim());
                startActivityForResult(intentUserName, REQUEST_USER_NAME);
                break;
            case R.id.rl_sex://性别
                UserGenderDialog userGenderDialog = new UserGenderDialog(this, mPresenter);
                userGenderDialog.showPopupWindow();
                break;
            case R.id.rl_address://常住地
                mPresenter.parseData(this);
                break;
            case R.id.rl_date_of_birth://出生年月
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mTvDateOfBirth.setText(TimeUtils.date2String(date, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)));
                        mPresenter.uploadDateOfBirth(mTvDateOfBirth.getText().toString().trim());
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.rl_occupation://职业
                Intent intentOccupation = new Intent(this, UserModifyInfoActivity.class);
                intentOccupation.putExtra("code", REQUEST_OCCUPATION);
                intentOccupation.putExtra("occupation", mTvOccupation.getText().toString().trim());
                startActivityForResult(intentOccupation, REQUEST_OCCUPATION);
                break;
            case R.id.rl_constellation://星座
                UserConstellationDialog userConstellationDialog = new UserConstellationDialog(this, mPresenter);
                userConstellationDialog.showPopupWindow();
                break;
            case R.id.rl_height://身高
                UserHeightDialog userHeightDialog = new UserHeightDialog(this, mPresenter);
                userHeightDialog.showPopupWindow();
                break;
            case R.id.rl_weight://体重
                UserWeightDialog userWeightDialog = new UserWeightDialog(this, mPresenter);
                userWeightDialog.showPopupWindow();
                break;
            case R.id.rl_emotional_state://情感状态
                UserEmotionDialog userEmotionDialog = new UserEmotionDialog(this, mPresenter);
                userEmotionDialog.showPopupWindow();
                break;
            case R.id.rl_real_name://真实姓名
                break;
            case R.id.rl_id_card://身份证
                break;
            case R.id.rl_phone://手机
                Intent intentPhone = new Intent(this, BindingPhoneNumberActivity.class);
                startActivityForResult(intentPhone, REQUEST_PHONE_CODE);
                break;
            case R.id.rl_o2://O2
                break;
            case R.id.rl_receiving_address://收货地址
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("URL", String.format(Locale.ENGLISH, Api.RECEIVING_ADDRESS_URL, SPUtils.getInstance().getString("userId")));
                startActivity(intent);
                break;
            case R.id.rl_unsubscribe://注销账户
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST://头像
                    List<LocalMedia> list = PictureSelector.obtainMultipleResult(data);
                    LocalMedia localMedia = list.get(0);//获取第一张图片
                    if (localMedia != null)
                        mPresenter.uploadPictures(localMedia);
                    else
                        ToastUtils.showShort("请重新选择照片");
                    break;
                case REQUEST_USER_NAME://用户名
                    if (data != null) {
                        String result = data.getStringExtra("content");
                        mTvUserName.setText(result);
                        mPresenter.uploadName(result);
                    }
                    break;
                case REQUEST_OCCUPATION://职业
                    if (data != null) {
                        String result = data.getStringExtra("content");
                        mTvOccupation.setText(result);
                        mPresenter.uploadOccupation(result);
                    }
                    break;
                case REQUEST_PHONE_CODE://手机(验证)
                    if (data != null) {
                        String result = data.getStringExtra("phone");
                        mTvPhone.setText(result);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void showHeadImg(String headImg) {
        ImageLoaderUtil.into(this, headImg, mIvHead);
    }

    @Override
    public void showName(String name) {
        mTvUserName.setText(name);
    }

    @Override
    public void showMobile(String mobile) {
        mTvPhone.setText(mobile);
    }

    @Override
    public void showO2(String O2) {
        mTvO2.setText(O2);
    }

    @Override
    public void showSex(boolean sex) {
        if (sex) {
            mTvSex.setText("男");
        } else {
            mTvSex.setText("女");
        }
    }

    @Override
    public void showOccupation(String occupation) {
        mTvOccupation.setText(occupation);
    }

    @Override
    public void showAddress(String obode) {
        mTvAddress.setText(obode);
    }

    @Override
    public void showDateOfBirth(String dateOfBirth) {
        mTvDateOfBirth.setText(dateOfBirth);
    }

    @Override
    public void showConstellation(String constellation) {
        mTvConstellation.setText(constellation);
    }

    @Override
    public void showHeight(String height) {
        mTvHeight.setText(height);
    }

    @Override
    public void showWeight(String weight) {
        mTvWeight.setText(weight);
    }

    @Override
    public void showEmotion(String emotion) {
        mTvEmotionalState.setText(emotion);
    }

    @Override
    public void setListData(List<CityBean> provinceList, List<List<String>> cityList, List<List<List<String>>> countyList) {
        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = provinceList.size() > 0 ?
                        provinceList.get(options1).getPickerViewText() : "";

                String opt2tx = cityList.size() > 0
                        && cityList.get(options1).size() > 0 ?
                        cityList.get(options1).get(options2) : "";

                String opt3tx = cityList.size() > 0
                        && countyList.get(options1).size() > 0
                        && countyList.get(options1).get(options2).size() > 0 ?
                        countyList.get(options1).get(options2).get(options3) : "";

                String tx = opt1tx + opt2tx + opt3tx;

                mTvAddress.setText(tx);//常住地
                mPresenter.uploadAddress(tx);
            }
        }).build();
        pvOptions.setPicker(provinceList, cityList, countyList);
        pvOptions.show();
    }
}
