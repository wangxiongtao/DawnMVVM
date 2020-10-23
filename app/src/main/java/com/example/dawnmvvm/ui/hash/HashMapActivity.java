package com.example.dawnmvvm.ui.hash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.dawnmvvm.R;
import com.example.dawnmvvm.base.RxLifeObserver;
import com.example.dawnmvvm.util.LogUtil;
import com.example.dawnmvvm.view.MyValueAnimaView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HashMapActivity extends AppCompatActivity {
    HashMap<String, String> hashMap;
    LinkedList linkedList;
    ArrayList arrayList;
    public String s1 = "1111";
    MutableLiveData<Object>startAct;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            LogUtil.e("===handler=====>");
            handler.sendEmptyMessageDelayed(0, 5000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hash_map);
        //编译命令 gradlew compileDebugJavaWithJavac
        //获取手机的cpu架构 adb shell getprop ro.product.cpu.abi

        MyValueAnimaView valueAnimaView=findViewById(R.id.value_view);
        valueAnimaView.setNums(9);
        MyValueAnimaView valueAnimaView1=findViewById(R.id.value_view1);
        valueAnimaView1.setNums(0);
        MyValueAnimaView valueAnimaView2=findViewById(R.id.value_view2);
        valueAnimaView2.setNums(1);
        MyValueAnimaView valueAnimaView3=findViewById(R.id.value_view3);
        valueAnimaView3.setNums(7);
        handler.sendEmptyMessage(0);
        Message.obtain(handler);


        arrayList = new ArrayList(10);

        hashMap = new HashMap<>(0);
        hashMap.put(null, "1");
        hashMap.put("1", null);


        MyNodeList<String> myNodeList = new MyNodeList<>();
//        myNodeList.add("0");
//        myNodeList.add("1");
//        myNodeList.add("2");
//        myNodeList.add("3");
//        myNodeList.add("4");
//        myNodeList.add("5");
//        myNodeList.get(3);


        myNodeList.addNext("0");
        myNodeList.addNext("1");
        myNodeList.addNext("2");

        myNodeList.getNext(0);
        myNodeList.getNext(1);
        myNodeList.getNext(2);

        myNodeList.removeIndex(1);
        myNodeList.getNext(0);
        myNodeList.getNext(1);

        myNodeList.insertIndex(1, "1");
        myNodeList.getNext(0);
        myNodeList.getNext(1);


        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Runnable> runQueue = new LinkedBlockingQueue<>();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    while (true){
//                        String s = queue.take();
//                        Thread.sleep(1000);
//                        LogUtil.e("==queue1==s===>" + s);
//                    }
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    LogUtil.e("==queue==InterruptedException===>" + e.getLocalizedMessage());
//                }
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    while (true){
//                        String s = queue.take();
//                        Thread.sleep(5000);
//                        LogUtil.e("==queue2==s===>" + s);
//                    }
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    LogUtil.e("==queue==InterruptedException===>" + e.getLocalizedMessage());
//                }
//            }
//        }).start();


        OkHttpClient client = new OkHttpClient();


        Request request2 = new Request.Builder()
                .get()
                .url("https://api.stage.shenzhoubb.com/app/checkResource")
                .build();
        Call call2 = client.newCall(request2);
        call2.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LogUtil.e("==onResponse==>http2==>" + s1 + "--thread--->" + Thread.currentThread().toString());
            }
        });

        Request request = new Request.Builder()
                .get()
                .url("https:www.baidu.com")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                s1 = "success";
                LogUtil.e("==onResponse==>http1==>" + "--thread--->" + Thread.currentThread().toString());

            }
        });

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String s = null;
                    try {
                        Runnable runnable = runQueue.take();
                        executorService.execute(runnable);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        for (int i = 0; i < 100; i++) {
            try {
                queue.put("queue" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        for (int i = 0; i < 100; i++) {
            try {
                int finalI = i;
                runQueue.put(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            LogUtil.e("====runQueue" + finalI + "====---name--->" + Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        ExecutorService executorService2 = Executors.newCachedThreadPool();
        executorService2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    LogUtil.e("=0cache===runQueue--A"+ "====---name--->" + Thread.currentThread().getName());
                    Thread.sleep(5000);
                    LogUtil.e("=1cache===runQueue--A" + "====---name--->" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    LogUtil.e("=0cache===runQueue--B"+ "====---name--->" + Thread.currentThread().getName());
                    Thread.sleep(1000);
                    LogUtil.e("=1cache===runQueue--B" + "====---name--->" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Observable.timer(70, TimeUnit.SECONDS).subscribe(new RxLifeObserver<Long>(this){
            @Override
            public void onNext(Long aLong) {
                super.onNext(aLong);
                executorService2.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            LogUtil.e("=0cache===runQueue--C"+ "====---name--->" + Thread.currentThread().getName());
                            Thread.sleep(3000);
                            LogUtil.e("=1cache===runQueue--C" + "====---name--->" + Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });



        //一共执行20个任务 ,核心线程数是4，最大核心线程数是10，目前加入的runnable20个(相当于20个任务），
        //20个任务需要执行，但是核心线程数只有4个，还有16个任务，由于LinkedBlockingQueue队列是最大存放的任务为9个，队列满了，则会创建新的线程去执行任务
        //这个时候最大线程是10，非核心线程数还有6个，这时候会开6个线程去执行，目前达到10个最大线程数，此时队列里面最大只能存放9个，
        //还有一个Runnable，此时就会报错RejectedExecutionException


//        for (int i = 0; i < 100; i++) {
//            int finalI = i;
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            executorService2.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        LogUtil.e("=0cache===runQueue"+ finalI +"====---name--->"+Thread.currentThread().getName());
//                        Thread.sleep(5000);
//                        LogUtil.e("=1cache===runQueue"+ finalI +"====---name--->"+Thread.currentThread().getName());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//        }

    }
}