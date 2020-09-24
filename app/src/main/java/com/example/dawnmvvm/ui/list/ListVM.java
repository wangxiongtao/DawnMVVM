package com.example.dawnmvvm.ui.list;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;

import com.example.dawnmvvm.BR;
import com.example.dawnmvvm.R;
import com.example.dawnmvvm.base.BaseViewModel;
import com.example.dawnmvvm.base.RxLifeObserver;
import com.example.dawnmvvm.bean.AppResBean;
import com.example.dawnmvvm.bean.BaseResponse;
import com.example.dawnmvvm.bean.LoadAppResBean;
import com.example.dawnmvvm.http.api.ApiRepository;
import com.example.dawnmvvm.util.LogUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

public class ListVM extends BaseViewModel {
    public ObservableArrayList<AppResBean> listObservableField=new ObservableArrayList<>();

    public ItemBinding<AppResBean>itemBinding= ItemBinding.<AppResBean>of(BR.resItem, R.layout.item_layout)
            .bindExtra(BR.listVm,this);


    public ObservableField<List<String>>listString=new ObservableField<>();








    public ItemBinding<String> muitemBinding = ItemBinding.of(new OnItemBind<String>() {
        @Override
        public void onItemBind(@NotNull ItemBinding itemBinding, int position, String item) {
            switch (position){
                case 0:
                    itemBinding.set(ItemBinding.VAR_NONE, R.layout.item_header );
                    break;
                default:
                    itemBinding.set(BR.resItem, R.layout.item_layout2 ).bindExtra(BR.listVm,ListVM.this);
                    break;

            }


        }
    });




    @Override
    public void onCreate() {
        super.onCreate();
        ApiRepository.checkAppResource4("111").subscribe(new RxLifeObserver<BaseResponse<LoadAppResBean>>(this){
            @Override
            public void onNext(BaseResponse<LoadAppResBean> res) {
                super.onNext(res);
                listObservableField.addAll(res.body.list);
            }
        });
        List<String>list=new ArrayList<>();
        for (int i=0;i<100;i++){
            list.add(i+"这是布局");
        }
        listString.set(list);



    }
    public void clickItem(AppResBean item){
        LogUtil.e("===>====>item===>"+item.value);
        AppResBean appResBean=new AppResBean();
        appResBean.value="1111";
        listObservableField.add(appResBean);

    }
    public void clickItem(String item){
        LogUtil.e("===>=String===>item===>"+item);

    }
}
