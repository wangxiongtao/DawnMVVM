package com.example.dawnmvvm.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;


import com.example.dawnmvvm.util.LogUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxLifeObserver<T> implements Observer<T>, LifecycleObserver, IViewModel {
    private IDisposable iDisposable;
    private Disposable disposable;
    private LifecycleOwner owner;
    private BaseViewModel baseViewModel;


    protected RxLifeObserver(IDisposable iDisposable) {
        this.iDisposable = iDisposable;
        if (iDisposable instanceof BaseViewModel) {
            baseViewModel = (BaseViewModel) iDisposable;
        }
    }

    protected RxLifeObserver(LifecycleOwner owner) {
        this.owner = owner;
    }

    @Override
    public final void onSubscribe(Disposable d) {//防止子类重写
        this.disposable = d;
        if (this.iDisposable != null) {
            this.iDisposable.addDisposable(d);
        }
        if (this.owner != null) {
            this.owner.getLifecycle().addObserver(this);
        }
        if (this.baseViewModel != null) {
            this.baseViewModel.showLoading();
        }
        showLoading();

        onSubscribe();
    }

    public void onSubscribe() {

    }


    @Override
    public void onNext(T t) {
        closeLoading();
    }

    @Override
    public void onError(Throwable e) {
        closeLoading();
    }


    @Override
    public void onComplete() {
        LogUtil.e("==onComplete====>");

    }

    @Override
    public void showLoading() {
        if(baseViewModel==null){
            return;
        }
        baseViewModel.showLoading();
    }

    @Override
    public void closeLoading() {
        if(baseViewModel==null){
            return;
        }
        baseViewModel.closeLoading();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        disposable.dispose();
    }
}
