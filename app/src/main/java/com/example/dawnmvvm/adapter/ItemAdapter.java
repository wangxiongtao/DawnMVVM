package com.example.dawnmvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dawnmvvm.util.LogUtil;

import java.util.List;

public abstract class ItemAdapter<T> extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<T> dataList;

    public void setList(List<T> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogUtil.e("onCreateViewHolder===>");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, getLayoutId(viewType), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
        if (binding == null) {
            return;
        }
        T data = null;
        if (position < dataList.size()) {
            data = dataList.get(position);
        }
        onBindViewHolder(binding, data, position);
        binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public abstract int getLayoutId(int viewType);

    public abstract void onBindViewHolder(ViewDataBinding binding, T item, int position);
}
