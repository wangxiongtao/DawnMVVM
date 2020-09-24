package com.example.dawnmvvm.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dawnmvvm.base.IDisposable;
import com.example.dawnmvvm.util.LogUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class CountDownButton extends androidx.appcompat.widget.AppCompatButton  {

    private Observable<Long>observable;
    private long totalTime=60;
    private Disposable disposable;
    private boolean isStart=false;
    public CountDownButton(@NonNull Context context) {
        super(context);
        init();
    }

    public CountDownButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CountDownButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        observable=Observable.interval(1, TimeUnit.SECONDS).take(totalTime+1);
    }
    public void start(){
        if(isStart){
            return;
        }
        isStart=true;
        disposable=observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                LogUtil.e("==down==>"+aLong);
                aLong=totalTime-aLong;
                if(aLong==0){
                    setText("重新开始");
                    stop();
                }else {
                    setText(aLong+"s");
                }

            }
        });
    }

    public void stop(){
        if(disposable!=null){
            disposable.dispose();
        }
        disposable=null;
        isStart=false;
    }



    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
       stop();
    }
}
