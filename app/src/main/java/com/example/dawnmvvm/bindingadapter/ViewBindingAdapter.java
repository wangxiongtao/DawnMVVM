package com.example.dawnmvvm.bindingadapter;

import androidx.databinding.BindingAdapter;

import com.example.dawnmvvm.view.CountDownButton;

public class ViewBindingAdapter {
    @BindingAdapter("android:countDownStatus")
    public static void setText(CountDownButton view, boolean status) {
        if(status){
            view.start();
        }else {
            view.stop();
        }
    }

}
