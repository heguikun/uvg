package com.uvgouapp.ui.user;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.uvgouapp.R;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.base.BaseFragment;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.contract.user.UserContract;
import com.uvgouapp.dialog.user.UserStoreDialog;
import com.uvgouapp.presenter.user.UserPresenter;
import com.uvgouapp.ui.other.WebViewActivity;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * - @Author:  ying
 * - @Time:  2018/12/23
 * - @Description:  我的
 */
public class UserFragment extends BaseFragment<UserPresenter> implements UserContract.View {

    @BindView(R.id.iv_head_portrait)
    ImageView ivHeadPortrait;//头像
    @BindView(R.id.tv_nickname)
    TextView tvNickname;//昵称
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;//店铺名

    @Override
    protected Object setLayout() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {
        mPresenter = new UserPresenter(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        //--------查询用户信息,查询店铺-----------
        String userId = SPUtils.getInstance().getString("userId");
        mPresenter.requestQueryUserInfo(userId);
        mPresenter.requestQueryShop(userId, false);
    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.rl_head_portrait, R.id.tv_my_admin, R.id.tv_order_management, R.id.tv_integral_management,
            R.id.tv_wallet_management, R.id.rl_store, R.id.rl_follow, R.id.rl_good_friend, R.id.rl_collection,
            R.id.rl_task, R.id.rl_invitation, R.id.rl_set_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_head_portrait://修改用户信息
                startActivity(new Intent(mBaseActivity, ChangeUserInfoActivity.class));
                break;
            case R.id.tv_my_admin://我的管理
                startActivity(new Intent(mBaseActivity, ChangeUserInfoActivity.class));
                break;
            case R.id.tv_order_management://订单管理
                Intent intentOrder = new Intent(mBaseActivity, WebViewActivity.class);
                intentOrder.putExtra("URL", String.format(Locale.ENGLISH, Api.ORDER_MANAGE, SPUtils.getInstance().getString("userId")));
                startActivity(intentOrder);
                break;
            case R.id.tv_integral_management://积分管理
                Intent intentIntegral = new Intent(mBaseActivity, WebViewActivity.class);
                intentIntegral.putExtra("URL", String.format(Locale.ENGLISH, Api.INTEGRAL_MANAGE, SPUtils.getInstance().getString("userId")));
                startActivity(intentIntegral);
                break;
            case R.id.tv_wallet_management://钱包管理
                Intent intentWallet = new Intent(mBaseActivity, WebViewActivity.class);
                intentWallet.putExtra("URL", String.format(Locale.ENGLISH, Api.WALLET_MANAGE_URL, SPUtils.getInstance().getString("userId")));
                startActivity(intentWallet);
                break;
            case R.id.rl_store://店铺
                String userId = SPUtils.getInstance().getString("userId");
                mPresenter.requestQueryShop(userId, true);
                break;
            case R.id.rl_follow://关注
                startActivity(new Intent(mBaseActivity, FollowActivity.class));
                break;
            case R.id.rl_good_friend://好友
                ToastUtils.showShort("好友");
                break;
            case R.id.rl_collection://收藏
                Intent intentCollection = new Intent(mBaseActivity, WebViewActivity.class);
                intentCollection.putExtra("URL", String.format(Locale.ENGLISH, Api.COLLECTION, SPUtils.getInstance().getString("userId")));
                startActivity(intentCollection);
                break;
            case R.id.rl_task://任务
                ToastUtils.showShort("任务");
                break;
            case R.id.rl_invitation://我的邀请
                startActivity(new Intent(mBaseActivity, MyInvitationActivity.class));
                break;
            case R.id.rl_set_up://设置
                startActivity(new Intent(mBaseActivity, SettingActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void applyStore() {
        UserStoreDialog userStoreDialog = new UserStoreDialog(mBaseActivity);
        userStoreDialog.showPopupWindow();
    }

    @Override
    public void showHeadImg(String headImg) {
        ImageLoaderUtil.into(mBaseActivity, headImg, ivHeadPortrait);
    }

    @Override
    public void showByname(String byname) {
        tvNickname.setText(byname);
    }

    @Override
    public void showShopName(String shopName) {
        tvShopName.setText(String.format(Locale.ENGLISH, "店铺名：%s", shopName));
    }

    @Override
    public void loadWebView() {
        Intent intent = new Intent(mBaseActivity, WebViewActivity.class);
        intent.putExtra("URL", Api.STORE_URL);
        startActivity(intent);
    }
}
