package com.example.dawnmvvm.ui.list;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;
import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dawnmvvm.adapter.ItemAdapter;
import com.example.dawnmvvm.adapter.ItemAdapterFactory;
import com.example.dawnmvvm.adapter.MyAdapter;
import com.example.dawnmvvm.base.BaseViewModel;
import com.example.dawnmvvm.base.RxLifeObserver;
import com.example.dawnmvvm.bean.AppResBean;
import com.example.dawnmvvm.bean.BaseResponse;
import com.example.dawnmvvm.bean.CheckBean;
import com.example.dawnmvvm.bean.LoadAppResBean;
import com.example.dawnmvvm.http.api.ApiRepository;
import com.example.dawnmvvm.util.LogUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ListVM extends BaseViewModel {
    public ObservableArrayList<AppResBean> listObservableField=new ObservableArrayList<>();
    public MutableLiveData<Boolean> refreshing=new MutableLiveData<>();


    public ItemAdapter<AppResBean> iBindingAdapter= ItemAdapterFactory.getAppResAdapter(this);
    public ItemAdapter<CheckBean> iBindingAdapter2= new MyAdapter(this);


    public SwipeRefreshLayout.OnRefreshListener onRefreshListener=new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
//            refreshing.set(true);
            LogUtil.e("==onRefresh====>");
            Observable.timer(2, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RxLifeObserver<Long>(ListVM.this){
                        @Override
                        public void onNext(Long aLong) {
                            super.onNext(aLong);
//                            AppResBean appResBean=new AppResBean();
//                            appResBean.value="1111";
//                            listObservableField.add(appResBean);
                            listObservableField.remove(0);
                            refreshing.postValue(false);

                            LogUtil.e("==onRefresh===end=>"+Thread.currentThread().getName());
                        }
                    }.setShowLoading(false));

        }
    };



    public ObservableList<CheckBean> checkBeans=new ObservableArrayList<>();

    public ObservableInt selectIndex=new ObservableInt(-1);








    @Override
    public void onCreate() {
        super.onCreate();
        r();
        for (int i=0;i<100;i++){
            CheckBean bean=new CheckBean("title==="+i,false,"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3922290090,3177876335&fm=26&gp=0.jpg");
            checkBeans.add(bean);
        }



    }

    private void r(){
        ApiRepository.checkAppResource4("111").subscribe(new RxLifeObserver<BaseResponse<LoadAppResBean>>(this){
            @Override
            public void onNext(BaseResponse<LoadAppResBean> res) {
                super.onNext(res);
                listObservableField.addAll(res.body.list);
                refreshing.postValue(false);
            }
        });
    }
    public void clickItem(AppResBean item,int index){
        LogUtil.e("===>====>item===>"+item.value);
        LogUtil.e("===>====>index===>"+index);
        selectIndex.set(index);

    }
    public void clickItem(CheckBean item,int index){
        LogUtil.e("===>=String===>item===>"+item.title);
        item.check=!item.check;
        checkBeans.set(index,item);


    }

    public void headRefresh(){
        checkBeans.clear();
        for (int i=0;i<100;i++){
            CheckBean bean=new CheckBean("title=22222=="+i,false,"https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2839262297,1897381364&fm=26&gp=0.jpg");
            checkBeans.add(bean);
        }

    }



}
