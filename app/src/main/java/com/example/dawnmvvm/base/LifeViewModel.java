package com.example.dawnmvvm.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.example.dawnmvvm.util.LogUtil;

public class LifeViewModel extends ViewModel implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        LogUtil.e("===OnLifecycleEvent==onCreate==>");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        LogUtil.e("===OnLifecycleEvent==onStart==>");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        LogUtil.e("===OnLifecycleEvent==onResume==>");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        LogUtil.e("===OnLifecycleEvent==onPause==>");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        LogUtil.e("===OnLifecycleEvent==onStop==>");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        LogUtil.e("===OnLifecycleEvent==onDestroy==>");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny() {
        LogUtil.e("===OnLifecycleEvent==onAny==>");
    }
}
