package com.example.dawnmvvm;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.dawnmvvm.base.BaseActivity;
import com.example.dawnmvvm.databinding.ActivityMainBinding;
import com.example.dawnmvvm.ui.list.SecondShareActivity;

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
        getViewDataBinding().img.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondShareActivity.class);

                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,getViewDataBinding().img,"share").toBundle());
            }
        });

    }

    @Override
    public int initVariableId() {
        return BR.mainVm;
    }
}