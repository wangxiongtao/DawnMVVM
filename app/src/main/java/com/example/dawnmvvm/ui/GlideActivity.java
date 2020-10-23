package com.example.dawnmvvm.ui;

import android.os.Bundle;

import com.example.dawnmvvm.BR;
import com.example.dawnmvvm.R;
import com.example.dawnmvvm.base.BaseActivity;
import com.example.dawnmvvm.databinding.ActivityGlideBinding;

public class GlideActivity extends BaseActivity<ActivityGlideBinding,GlideVm> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_glide;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int initVariableId() {
        return BR.gVm;
    }
}