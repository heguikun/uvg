package com.uvgouapp.dialog.user;


import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.uvgouapp.R;
import com.uvgouapp.adapter.user.HeightAdapter;
import com.uvgouapp.presenter.user.ChangeUserInfoPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/2/28
 * - @Description:  身高
 */
public class UserHeightDialog extends BasePopupWindow {

    private ChangeUserInfoPresenter mPresenter;

    public UserHeightDialog(Context context, ChangeUserInfoPresenter presenter) {
        super(context);
        this.mPresenter = presenter;
        initView();
    }

    private void initView() {
        List<String> list = new ArrayList<>();

        list.add("150cm以下");
        list.add("150-154cm");
        list.add("155-159cm");
        list.add("160-164cm");
        list.add("165-169cm");
        list.add("170-174cm");
        list.add("175-179cm");
        list.add("180cm以上");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        HeightAdapter heightAdapter = new HeightAdapter(R.layout.item_choose_height);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        heightAdapter.addData(list);
        recyclerView.setAdapter(heightAdapter);

        heightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String height = heightAdapter.getData().get(position);
                mPresenter.uploadHeight(height);
                dismiss();
            }
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_choose_height);
    }
}
