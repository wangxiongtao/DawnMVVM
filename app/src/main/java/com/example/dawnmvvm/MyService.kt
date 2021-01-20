package com.example.dawnmvvm

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import com.example.dawnmvvm.util.LogUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyService : Service() {
    var a=0;
    var job:Job?=null;
    private val handler=Handler(Looper.getMainLooper());

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        job?.cancel()
        job=GlobalScope.launch {
            while (true){
                delay(1000);
                a++
                LogUtil.e("MyService=====>$a")
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null;

    }
    companion object {
            fun start(context: Context) {
                context.startService(Intent(context, MyService::class.java));
            }
        }


}