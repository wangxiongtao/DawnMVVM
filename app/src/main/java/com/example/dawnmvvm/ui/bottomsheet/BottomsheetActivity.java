package com.example.dawnmvvm.ui.bottomsheet;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.example.dawnmvvm.BR;
import com.example.dawnmvvm.R;
import com.example.dawnmvvm.adapter.MyViewPageAdapter;
import com.example.dawnmvvm.base.BaseActivity;
import com.example.dawnmvvm.databinding.ActivityBottomsheetBinding;
import com.example.dawnmvvm.databinding.DialogBottomSheetLayoutBinding;
import com.example.dawnmvvm.ui.UIVm;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BottomsheetActivity extends BaseActivity<ActivityBottomsheetBinding, UIVm> {


  boolean top=true;
    @Override
    public int getLayoutId() {
        return R.layout.activity_bottomsheet;
    }

    @Override
    public void initData(Bundle savedInstanceState) {


        LinearLayout linearLayout=getViewDataBinding().dataLl;
//        BottomSheetBehavior bottomSheetBehavior= BottomSheetBehavior.from(linearLayout);
        TranslateAnimation translateAnimation=new TranslateAnimation(0,0,0,170);
        ObjectAnimator translationY =ObjectAnimator.ofFloat(linearLayout,"translationY",0,170);

        getViewDataBinding().vp.setAdapter(new MyViewPageAdapter(this));




        getViewModel().liveData.observe(this,(data)->{
            Toast.makeText(this,data+"",Toast.LENGTH_LONG).show();
            show();


//            if(top){
//                translationY.start();
//                top=false;
//            }else {
//                translationY.reverse();
//                top=true;
//            }









        });

    }

    @Override
    public int initVariableId() {
        return BR.uiVm;
    }

    private void show(){
        View view = View.inflate(this, R.layout.dialog_bottom_sheet_layout, null);


        DialogBottomSheetLayoutBinding bind = DataBindingUtil.bind(view);


        BottomSheetDialog dialog=new BottomSheetDialog(this,R.style.trans_Dialog);
        dialog.setContentView(bind.getRoot());


        dialog.getWindow().setWindowAnimations(R.style.anim_bottom_dialog);
        BottomSheetBehavior mDialogBehavior = BottomSheetBehavior.from((View)view.getParent());
            mDialogBehavior.setPeekHeight(1013);//dialog的高度
//            mDialogBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        dialog.show();

        UIVm uiVm=new UIVm();
        bind.setUiVm(uiVm);
        uiVm.onCreate();




    }
}