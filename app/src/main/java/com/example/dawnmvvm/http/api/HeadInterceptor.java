package com.example.dawnmvvm.http.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by fw-soc on 2018/9/19.
 * head部分的出来
 */

public class HeadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return addHeader(chain);
    }
    private Response addHeader(Chain chain) throws IOException {

        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
//                .addHeader("USER_ROLE", "ENGINEER")
                .addHeader("PLATFORM","ANDROID");
//                .addHeader("SYSTEM_VERSION", ApkUtil.getSystemVersion());
//                .addHeader("VERSION", ApkUtil.getVersionName(EngineerApp.getInstance()))
//                .addHeader(Global.ACCESS_TOKEN, getAccessToken())
//                .header("Cookie","sym-ce="+getCommunityToken());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }


}
