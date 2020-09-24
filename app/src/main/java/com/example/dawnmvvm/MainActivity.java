package com.example.dawnmvvm;


import android.content.Intent;
import android.os.Bundle;

import com.example.dawnmvvm.base.BaseActivity;
import com.example.dawnmvvm.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainVM> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getViewModel().skip.observe(this,(o)->{
                Intent intent=new Intent(this,o);
                startActivity(intent);
        });

    }

    @Override
    public int initVariableId() {
        return BR.mainVm;
    }
}