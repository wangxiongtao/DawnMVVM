package com.example.dawnmvvm;

import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.example.dawnmvvm.base.BaseViewModel;
import com.example.dawnmvvm.base.RxLifeObserver;
import com.example.dawnmvvm.bean.BaseResponse;
import com.example.dawnmvvm.bean.LoadAppResBean;
import com.example.dawnmvvm.http.api.ApiRepository;
import com.example.dawnmvvm.ui.list.DataListActivity;
import com.example.dawnmvvm.ui.list.MultipleListActivity;
import com.example.dawnmvvm.ui.list.MyListActivity;
import com.example.dawnmvvm.ui.main.MainActivity2;

import java.io.IOException;

import okhttp3.ResponseBody;

public class MainVM extends BaseViewModel {
    public ObservableField<String> string = new ObservableField<>();
    public MutableLiveData<String> string2 = new MutableLiveData<>();
    public MutableLiveData<Class<?>> skip = new MutableLiveData<>();
    public MutableLiveData<Boolean> countDownStatus = new MutableLiveData<>(false);//通过数据驱动倒计时btn停止和启动
    public View.OnClickListener listener = (v) -> {
        countDownStatus.postValue(!countDownStatus.getValue());
        ApiRepository.checkAppResource4("111")
                .subscribe(new RxLifeObserver<BaseResponse<LoadAppResBean>>(this) {
                    @Override
                    public void onNext(BaseResponse<LoadAppResBean> loadAppResBeanBaseResponse) {
                        super.onNext(loadAppResBeanBaseResponse);
                        Toast.makeText(v.getContext(), loadAppResBeanBaseResponse.body.timestamp, Toast.LENGTH_LONG).show();
                    }
                });
    };

    public void toRequest() {
        ApiRepository.checkAppResource("111").subscribe(new RxLifeObserver<ResponseBody>(this) {
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

    public void skip() {
        skip.postValue(MainActivity2.class);
    }

    public void skipList() {
        skip.postValue(DataListActivity.class);
    }

    public void skipMultipleList() {
        skip.postValue(MultipleListActivity.class);
    }
    public void skipCommonList() {
        skip.postValue(MyListActivity.class);
    }

    public MainVM() {
        toRequest();
    }
}
