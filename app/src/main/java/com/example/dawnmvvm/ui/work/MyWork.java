package com.example.dawnmvvm.ui.work;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.dawnmvvm.util.LogUtil;

public class MyWork extends Worker {
    public MyWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        //接收外面传递进来的数据
        String inputData = getInputData().getString("input_data");
        LogUtil.e("==MyWork=inputData==>"+inputData+"==this===>"+this);
        LogUtil.e("==MyWork=doWork==>"+Thread.currentThread().getName());
        return Result.success();
    }
}
