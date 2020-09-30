package com.example.dawnmvvm.ui;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;

import com.example.dawnmvvm.BR;
import com.example.dawnmvvm.R;
import com.example.dawnmvvm.adapter.ItemAdapter;
import com.example.dawnmvvm.base.BaseViewModel;
import com.example.dawnmvvm.util.LogUtil;

public class UIVm extends BaseViewModel {
   public MutableLiveData<Integer>liveData=new MutableLiveData<>();

   public ObservableInt fragmentPos=new ObservableInt();

   public ObservableArrayList<String>list=new ObservableArrayList<>();


   public ItemAdapter<String> itemAdapter=new ItemAdapter<String>() {
       @Override
       public int getLayoutId(int viewType) {
           return R.layout.item_sheet_layout;
       }

       @Override
       public void onBindViewHolder(ViewDataBinding binding, String item, int position) {
           binding.setVariable(BR.item,item);

       }
   };

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.e("===fragment====>onCreate");
        for (int i=0;i<100;i++){
            list.add("i"+i);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("===fragment====>onResume");
    }

    public void click(int flag){
       liveData.postValue(flag);
   }

}
