package com.example.dawnmvvm.ui.list;

import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.example.dawnmvvm.BR;
import com.example.dawnmvvm.R;
import com.example.dawnmvvm.base.BaseActivity;
import com.example.dawnmvvm.databinding.ActivityMultipleListBinding;

public class MultipleListActivity extends BaseActivity<ActivityMultipleListBinding,ListVM> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_multiple_list;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getViewModel().skip.observe(this, new Observer<Class<?>>() {
            @Override
            public void onChanged(Class<?> aClass) {

            }
        });

    }

    @Override
    public int initVariableId() {
        return BR.listVm;
    }
}