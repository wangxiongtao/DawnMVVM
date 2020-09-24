package com.example.dawnmvvm.base;

import androidx.lifecycle.MutableLiveData;

import com.example.dawnmvvm.util.LogUtil;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends LifeViewModel implements IDisposable,IViewModel {
    private CompositeDisposable disposable;
    public MutableLiveData<Boolean> loadingEvent=new MutableLiveData<>();



    @Override
    public void addDisposable(Disposable d) {
        if(disposable==null){
            disposable=new CompositeDisposable();
        }
        disposable.add(d);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        LogUtil.e("===OnLifecycleEvent==onCleared==>");
        if(disposable!=null){
            disposable.clear();
        }
    }

    @Override
    public void showLoading() {
        loadingEvent.postValue(true);

    }

    @Override
    public void closeLoading() {
        loadingEvent.postValue(false);
    }

}
