package com.example.dawnmvvm.ui.work;

import android.annotation.SuppressLint;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.PersistableBundle;

import androidx.annotation.RequiresApi;

import com.example.dawnmvvm.util.LogUtil;

import java.util.concurrent.TimeUnit;


@SuppressLint("SpecifyJobSchedulerIdRange")
public class MyJobMyService extends JobService {

    public static void start(Context context, PersistableBundle extras){
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(context, MyJobMyService.class));  //指定哪个JobService执行操作
//        builder.setMinimumLatency(TimeUnit.MILLISECONDS.toMillis(10)); //执行的最小延迟时间
//        builder.setOverrideDeadline(TimeUnit.MILLISECONDS.toMillis(15));  //执行的最长延时时间
//        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NOT_ROAMING);  //非漫游网络状态
//        builder.setBackoffCriteria(TimeUnit.MINUTES.toMillis(10), JobInfo.BACKOFF_POLICY_LINEAR);  //线性重试方案
//        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);//设置任何网络环境下都可以执行
//        builder.setRequiresCharging(false);//设置是否在只有插入充电器的时候执行
//        builder.setRequiresDeviceIdle(false);//设置手机系统处于空闲状态下执行
        builder.setExtras(extras);
        JobInfo jobInfo=builder.build();
        jobScheduler.schedule(jobInfo);
        LogUtil.e("==MyJobMyService=jobInfo1====>"+jobInfo.isRequireDeviceIdle());
        LogUtil.e("==MyJobMyService=jobInfo2====>"+jobInfo.isRequireCharging());
    }

    public MyJobMyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.e("==MyJobMyService=onCreate====>"+this);
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        LogUtil.e("==MyJobMyService=onStartJob====>"+this);
        jobFinished(params,false);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        LogUtil.e("==MyJobMyService=onStopJob====>"+this);
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e("==MyJobMyService=onDestroy====>"+this);
    }
}