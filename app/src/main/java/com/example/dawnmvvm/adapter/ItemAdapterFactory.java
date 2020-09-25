package com.example.dawnmvvm.adapter;

import androidx.databinding.ViewDataBinding;

import com.example.dawnmvvm.BR;
import com.example.dawnmvvm.R;
import com.example.dawnmvvm.bean.AppResBean;
import com.example.dawnmvvm.bean.CheckBean;
import com.example.dawnmvvm.ui.list.ListVM;
import com.example.dawnmvvm.util.LogUtil;

public class ItemAdapterFactory {

    public static ItemAdapter<CheckBean> getHead(ListVM o) {


        return new ItemAdapter<CheckBean>() {



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
                        break;
                    case -1:
                        binding.setVariable(BR.listVm, "foot");
                        break;
                    default:
                        binding.setVariable(BR.checkBean, item);
                        binding.setVariable(BR.index, position);
                        binding.setVariable(BR.listVm, o);
                        break;
                }
            }
        };
    }

    public static ItemAdapter<AppResBean> getAppResAdapter(ListVM o) {
        return new ItemAdapter<AppResBean>() {
            @Override
            public int getLayoutId(int position) {
                LogUtil.e("==getLayoutId====>" + position);
                return R.layout.item_layout;
            }

            @Override
            public void onBindViewHolder(ViewDataBinding binding, AppResBean item, int position) {
                binding.setVariable(BR.resItem, item);
                binding.setVariable(BR.index, position);
                binding.setVariable(BR.listVm, o);
            }
        };
    }


}
