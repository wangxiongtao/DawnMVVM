package com.example.dawnmvvm.util;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class RXUtil {
    public static  ObservableTransformer<ResponseBody, ResponseBody> mainThread(){
        return  new ObservableTransformer<ResponseBody, ResponseBody>() {

            @Override
            public ObservableSource<ResponseBody> apply(Observable<ResponseBody> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
