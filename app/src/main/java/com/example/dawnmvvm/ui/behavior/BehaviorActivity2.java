package com.example.dawnmvvm.ui.behavior;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dawnmvvm.BR;
import com.example.dawnmvvm.R;
import com.example.dawnmvvm.base.BaseActivity;
import com.example.dawnmvvm.databinding.ActivityBehavior2Binding;
import com.example.dawnmvvm.ui.UIVm;
import com.example.dawnmvvm.util.LogUtil;
import com.example.dawnmvvm.view.FloatDragView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BehaviorActivity2 extends BaseActivity<ActivityBehavior2Binding, UIVm> {
    int d=-100;
    boolean g=true;
    @Override
    public int getLayoutId() {
        return R.layout.activity_behavior2;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
//        FloatDragView floatDragView=new FloatDragView();
//        floatDragView.setTargetView(getViewDataBinding().btn);
        Button view=getViewDataBinding().btn;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(g){
//                    v.offsetTopAndBottom(100);
//                    g=false;
//                }else {
//                    v.setTranslationY(d-=100);
//                }


                LogUtil.e("===getY==>"+v.getY());
                LogUtil.e("===getTranslationY==>"+v.getTranslationY());
            }
        });
        getViewDataBinding().rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LogUtil.e("==nest4==onScrolled==dy===>"+dy);
            }
        });



    }

    @Override
    public int initVariableId() {
        return BR.uiVm;
    }
}