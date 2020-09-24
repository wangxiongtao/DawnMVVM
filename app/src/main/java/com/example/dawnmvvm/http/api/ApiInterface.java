package com.example.dawnmvvm.http.api;

import androidx.lifecycle.MutableLiveData;

import com.example.dawnmvvm.bean.BaseResponse;
import com.example.dawnmvvm.bean.LoadAppResBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/app/checkResource")
    Observable<ResponseBody> checkAppResource(@Query("timestamp") String timestamp);


    @GET("/app/checkResource")
    MutableLiveData<ResponseBody> checkAppResource1(@Query("timestamp") String timestamp);


    @GET("/app/checkResource")
    Integer checkAppResource2(@Query("timestamp") String timestamp);


    @GET("/app/checkResource")
    Observable<BaseResponse<LoadAppResBean>> checkAppResource4(@Query("timestamp") String timestamp);
}
