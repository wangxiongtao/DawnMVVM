package com.example.dawnmvvm.adapter;

import android.app.Activity;
import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.example.dawnmvvm.BR;
import com.example.dawnmvvm.R;
import com.example.dawnmvvm.bean.CheckBean;
import com.example.dawnmvvm.databinding.ItemLayout2Binding;
import com.example.dawnmvvm.ui.ItemShareAnimActivity;
import com.example.dawnmvvm.ui.list.ListVM;
import com.example.dawnmvvm.util.LogUtil;

public class MyAdapter extends ItemAdapter<CheckBean> {
    private ListVM listVM;

    public MyAdapter(ListVM listVM) {
        this.listVM = listVM;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount()+1;
    }

    @Override
    public int getItemViewType(int position) {
        int size = getItemCount();
        if (position == 0) {
            return 0;
        } else if (position == size - 1) {
            return -1;
        } else {
            return 2;
        }

    }

    @Override
    public int getLayoutId(int viewType) {
        LogUtil.e("==getLayoutId====>" + viewType);
        switch (viewType) {
            case 0:
                return R.layout.item_header;
            case -1:
                return R.layout.item_footer;
            default:
                return R.layout.item_layout2;
        }
    }

    @Override
    public void onBindViewHolder(ViewDataBinding binding, CheckBean item, int position) {
        switch (getItemViewType(position)) {
            case 0:
                binding.setVariable(BR.listVm, "122344");
                binding.setVariable(BR.listVm1, listVM);
                break;
            case -1:
                binding.setVariable(BR.listVm, "foot");
                break;
            default:
                binding.setVariable(BR.checkBean, item);
                binding.setVariable(BR.index, position);
                binding.setVariable(BR.listVm, listVM);
                ItemLayout2Binding binding1= (ItemLayout2Binding) binding;
                break;
        }
    }
}
