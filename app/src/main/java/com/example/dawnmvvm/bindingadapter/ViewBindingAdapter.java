package com.example.dawnmvvm.bindingadapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.model.GlideUrl;
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
        imageUrl="https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2839262297,1897381364&fm=26&gp=0.jpg";
        GlideApp.with(imageView.getContext()).load(new GlideUrl(imageUrl)).placeholder(imageView.getDrawable()).error(imageView.getDrawable()).into(imageView);

    }

}
