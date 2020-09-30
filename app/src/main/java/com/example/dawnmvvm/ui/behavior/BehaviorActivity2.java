package com.example.dawnmvvm.ui.behavior;

import android.os.Bundle;

import com.example.dawnmvvm.BR;
import com.example.dawnmvvm.R;
import com.example.dawnmvvm.base.BaseActivity;
import com.example.dawnmvvm.databinding.ActivityBehavior2Binding;
import com.example.dawnmvvm.ui.UIVm;
import com.example.dawnmvvm.view.FloatDragView;

public class BehaviorActivity2 extends BaseActivity<ActivityBehavior2Binding, UIVm> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_behavior2;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        FloatDragView floatDragView=new FloatDragView();
        floatDragView.setTargetView(getViewDataBinding().btn);


    }

    @Override
    public int initVariableId() {
        return BR.uiVm;
    }
}