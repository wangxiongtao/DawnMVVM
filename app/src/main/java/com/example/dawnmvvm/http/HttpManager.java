package com.example.dawnmvvm.http;




import com.example.dawnmvvm.BuildConfig;
import com.example.dawnmvvm.http.api.ApiInterface;
import com.example.dawnmvvm.http.api.HttpLoggingInterceptor1;
import com.example.dawnmvvm.util.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

/**
 * Created by fw-soc on 2018/9/19.
 */

public class HttpManager {
    private Retrofit retrofit;
    private static String sAPIBASEURL="https://api.stage.shenzhoubb.com/";
    private static Interceptor sHEADINTERCEPTOR;
    private ApiInterface apiInterface;


    private static class SingletonHolder {
        private static HttpManager instance = new HttpManager();
    }

    public static HttpManager getInstance() {
        return SingletonHolder.instance;
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(30, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(30, TimeUnit.SECONDS);//设置连接超时时间

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(BODY));
            builder.addInterceptor(new HttpLoggingInterceptor1().setLevel(HttpLoggingInterceptor1.Level.BODY));
        }
        return builder.build();

    }

    private HttpManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(sAPIBASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
               .build();
        apiInterface=retrofit.create(ApiInterface.class);
        LogUtil.e("==API_BASE_URL====>" + sAPIBASEURL);
    }


    public static void initApiBaseUrl(String url) {
        sAPIBASEURL = url;
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }

    public static void addHeadInterceptor(Interceptor headInterceptor) {
        sHEADINTERCEPTOR = headInterceptor;
    }

}
