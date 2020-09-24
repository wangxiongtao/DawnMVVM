package com.example.dawnmvvm.ui.main;

import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dawnmvvm.base.BaseViewModel;
import com.example.dawnmvvm.base.RxLifeObserver;
import com.example.dawnmvvm.http.api.ApiRepository;
import com.example.dawnmvvm.util.LogUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

public class Main2VM extends BaseViewModel {
    public MutableLiveData<String> string2=new MutableLiveData<>();

    public View.OnClickListener listener=(v)->{
      toRequest();
    };


    public void toRequest(){
        ApiRepository.checkAppResource("111").subscribe(new RxLifeObserver<ResponseBody>(this){
            @Override
            public void onNext(ResponseBody responseBody) {
                super.onNext(responseBody);
                try {
                    string2.postValue(responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Observable.interval(1, TimeUnit.SECONDS)
                .map(new Function<Long, Long>() {

                    @Override
                    public Long apply(Long aLong) throws Exception {
                        LogUtil.e("==life==activity=map====>"+aLong);
                        return aLong;
                    }
                })
                .subscribe(new RxLifeObserver<Long>(this){
            @Override
            public void onNext(Long aLong) {
                super.onNext(aLong);//退出计时自动停止
                LogUtil.e("==life==activity==next==>"+aLong);
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        LogUtil.e("==life==activity====>onCleared");
    }
}