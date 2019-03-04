package com.uvgouapp.dialog.user;


import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.uvgouapp.R;
import com.uvgouapp.adapter.user.WeightAdapter;
import com.uvgouapp.presenter.user.ChangeUserInfoPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/2/28
 * - @Description:  体重
 */
public class UserWeightDialog extends BasePopupWindow {

    private ChangeUserInfoPresenter mPresenter;

    public UserWeightDialog(Context context, ChangeUserInfoPresenter presenter) {
        super(context);
        this.mPresenter = presenter;
        initView();
    }

    private void initView() {

        List<String> list = new ArrayList<>();
        list.add("40kg以下");
        list.add("40-44kg");
        list.add("45-49kg");
        list.add("50-54kg");
        list.add("55-59kg");
        list.add("60-64kg");
        list.add("65-69kg");
        list.add("70-74kg");
        list.add("75-79kg");
        list.add("80-84kg");
        list.add("85-89kg");
        list.add("90-94kg");
        list.add("95-99kg");
        list.add("100kg以上");

        WeightAdapter weightAdapter = new WeightAdapter(R.layout.item_choose_weight);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        weightAdapter.addData(list);
        recyclerView.setAdapter(weightAdapter);

        weightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String weight = weightAdapter.getData().get(position);
                mPresenter.uploadWeight(weight);
                dismiss();
            }
        });

    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_choose_weight);
    }
}
