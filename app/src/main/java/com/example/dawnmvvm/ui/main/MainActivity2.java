package com.example.dawnmvvm.ui.main;

import android.os.Bundle;

import com.example.dawnmvvm.BR;
import com.example.dawnmvvm.R;
import com.example.dawnmvvm.base.BaseActivity;
import com.example.dawnmvvm.databinding.MainActivityBinding;
import com.example.dawnmvvm.util.LogUtil;

public class MainActivity2 extends BaseActivity<MainActivityBinding,Main2VM> {



    @Override
    public int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

    }

    @Override
    public int initVariableId() {
        return BR.main2VM;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e("==life==activity====>onDestroy");
    }
}