package com.example.dawnmvvm.bindingadapter;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dawnmvvm.bindingadapter.recyclerview.DividerLine;
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
    @BindingAdapter("lineManager")
    public static void setLineManager(RecyclerView recyclerView,DividerLine.LineDrawMode type) {
        recyclerView.addItemDecoration(new DividerLine(recyclerView.getContext(), type));
    }



}
