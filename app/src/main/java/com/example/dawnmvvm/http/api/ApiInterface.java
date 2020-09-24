package com.example.dawnmvvm.http.api;

import androidx.lifecycle.MutableLiveData;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    /**
     * APP资源 / 获取更新资源
     * timestamp 本地资源版本时间戳 Example: 1544090711
     */
    @GET("/app/checkResource")
    Observable<ResponseBody> checkAppResource(@Query("timestamp") String timestamp);
    @GET("/app/checkResource")
    MutableLiveData<ResponseBody> checkAppResource1(@Query("timestamp") String timestamp);
    @GET("/app/checkResource")
    Integer checkAppResource2(@Query("timestamp") String timestamp);
}
