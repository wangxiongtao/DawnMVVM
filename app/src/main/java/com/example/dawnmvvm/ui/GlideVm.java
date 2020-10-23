package com.example.dawnmvvm.ui;

import android.view.View;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;

import com.example.dawnmvvm.BR;
import com.example.dawnmvvm.R;
import com.example.dawnmvvm.adapter.ItemAdapter;
import com.example.dawnmvvm.base.BaseViewModel;

public class GlideVm extends BaseViewModel {
    public ObservableList<String>list=new ObservableArrayList<>();
    public ItemAdapter<String>itemAdapter=new ItemAdapter<String>() {
        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_glide;
        }

        @Override
        public void onBindViewHolder(ViewDataBinding binding, String item, int position) {
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initList();
                    list.set(0,"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1796206616,3558957237&fm=26&gp=0.jpg");

                }
            });
            binding.setVariable(BR.url,item);

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        initList();


    }
    private void initList(){
        list.clear();
        list.add("https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3892521478,1695688217&fm=26&gp=0.jpg");
        list.add("https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=151472226,3497652000&fm=26&gp=0.jpg");
        list.add("https://dss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1689053532,4230915864&fm=26&gp=0.jpg");
        list.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1906469856,4113625838&fm=26&gp=0.jpg");
        list.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3262817768,278627444&fm=111&gp=0.jpg");
        list.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1429175118,2649084526&fm=111&gp=0.jpg");
        list.add("https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1709216491,2536617744&fm=26&gp=0.jpg");
        list.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=117297436,638569208&fm=26&gp=0.jpg");
        list.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2037546633,2061708947&fm=26&gp=0.jpg");
        list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1630793220,1034685898&fm=26&gp=0.jpg");
        list.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1847776403,3965152368&fm=15&gp=0.jpg");
    }
}
