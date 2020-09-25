package com.example.dawnmvvm.ui.list;

import android.os.Bundle;

import com.example.dawnmvvm.BR;
import com.example.dawnmvvm.R;
import com.example.dawnmvvm.base.BaseActivity;
import com.example.dawnmvvm.databinding.ActivitySecondShareBinding;

public class SecondShareActivity extends BaseActivity<ActivitySecondShareBinding,ShareVm> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_second_share;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int initVariableId() {
        return BR.shareVm;
    }
}