package com.example.dawnmvvm.bindingadapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dawnmvvm.adapter.ItemAdapter;
import com.example.dawnmvvm.bindingadapter.recyclerview.DividerLine;
import com.example.dawnmvvm.view.CountDownButton;

import java.util.List;

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
    @BindingAdapter({"dataList","adapter"})
    public static <T> void setDataList(RecyclerView recyclerView, List<T> list, ItemAdapter<T> adapter){
       ItemAdapter<T>  itemAdapter= (ItemAdapter<T>) recyclerView.getAdapter();
        if(itemAdapter==null){
            itemAdapter=adapter;
            adapter.setList(list);
            recyclerView.setAdapter(adapter);
        }
        itemAdapter.notifyDataSetChanged();

    }
    @BindingAdapter("imageUrl")
    public static <T> void setImageUrl(ImageView imageView, String imageUrl){
        GlideApp.with(imageView.getContext()).load(imageUrl).placeholder(imageView.getDrawable()).error(imageView.getDrawable()).into(imageView);

    }

}
