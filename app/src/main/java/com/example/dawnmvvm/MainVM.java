package com.example.dawnmvvm;

import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.example.dawnmvvm.base.BaseViewModel;
import com.example.dawnmvvm.base.RxLifeObserver;
import com.example.dawnmvvm.http.api.ApiRepository;
import com.example.dawnmvvm.view.CountDownButton;

import java.io.IOException;

import okhttp3.ResponseBody;

public class MainVM extends BaseViewModel {
    public ObservableField<String>string=new ObservableField<>();
    public MutableLiveData<String>string2=new MutableLiveData<>();
    public MutableLiveData<Object>skip=new MutableLiveData<>();
    public MutableLiveData<Boolean>countDownStatus=new MutableLiveData<>(false);//通过数据驱动倒计时btn停止和启动
    public View.OnClickListener listener=(v)->{
        countDownStatus.postValue(!countDownStatus.getValue());
    };
    public void toRequest(){
        ApiRepository.checkAppResource("111").subscribe(new RxLifeObserver<ResponseBody>(this){
            @Override
            public void onNext(ResponseBody responseBody) {
                super.onNext(responseBody);
                try {
                    string2.postValue(responseBody.string());//也可以使用string赋值
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void skip(){
       skip.postValue(null);
    }

    public MainVM() {
        toRequest();
    }
}
