package com.example.dawnmvvm.ui.main;

import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dawnmvvm.base.BaseViewModel;
import com.example.dawnmvvm.base.RxLifeObserver;
import com.example.dawnmvvm.http.api.ApiRepository;
import com.example.dawnmvvm.util.LogUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;


public class FragmentVm extends BaseViewModel {
    public ObservableField<String>string=new ObservableField<>();
    public MutableLiveData<String>toast=new MutableLiveData<>("TOAST");

    public View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),"dddd",Toast.LENGTH_LONG).show();
        }
    };
    public View.OnClickListener listener2=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toRequest();
        }
    };
    public void toRequest(){
        ApiRepository.checkAppResource("111").subscribe(new RxLifeObserver<ResponseBody>(this){
            @Override
            public void onNext(ResponseBody responseBody) {
                super.onNext(responseBody);
                toast.postValue("顺便通过LiveData方式把Toast改变一下");
                try {
                    string.set("!!!!!!!!自己请求数据！！！！！！！"+responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Observable.interval(1,TimeUnit.SECONDS).map(new Function<Long, Long>() {

            @Override
            public Long apply(Long aLong) throws Exception {
                LogUtil.e("==life==fragment==map==>"+aLong);
                return aLong;
            }
        }).subscribe(new RxLifeObserver<Long>(this){
            @Override
            public void onNext(Long aLong) {
                super.onNext(aLong);//退出计时自动停止
                LogUtil.e("==life==fragment==next==>"+aLong);
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        LogUtil.e("==life==fragment====>onCleared");
    }
}
