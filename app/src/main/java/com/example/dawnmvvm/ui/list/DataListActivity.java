package com.example.dawnmvvm.ui.list;

import android.os.Bundle;

import com.example.dawnmvvm.BR;
import com.example.dawnmvvm.R;
import com.example.dawnmvvm.base.BaseActivity;
import com.example.dawnmvvm.databinding.ActivityDataListBinding;

public class DataListActivity extends BaseActivity<ActivityDataListBinding,ListVM> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_data_list;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int initVariableId() {
        return BR.listVm;
    }
}