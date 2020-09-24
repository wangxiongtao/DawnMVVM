package com.example.dawnmvvm.http.api;

import androidx.lifecycle.LiveData;


import com.example.dawnmvvm.http.HttpManager;
import com.example.dawnmvvm.util.RXUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ApiRepository {

    public static Observable<ResponseBody> checkAppResource(String time) {
        return HttpManager.getInstance().getApiInterface().checkAppResource(time)
                .compose(RXUtil.mainThread());
    }

}
