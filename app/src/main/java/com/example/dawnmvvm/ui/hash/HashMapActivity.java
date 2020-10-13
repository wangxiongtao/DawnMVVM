package com.example.dawnmvvm.ui.hash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dawnmvvm.R;
import com.example.dawnmvvm.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class HashMapActivity extends AppCompatActivity {
    HashMap<String,String> hashMap;
    LinkedList linkedList;
    ArrayList arrayList;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            LogUtil.e("===handler=====>");
            handler.sendEmptyMessageDelayed(0,5000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hash_map);
        handler.sendEmptyMessage(0);

        arrayList=new ArrayList(10);

        hashMap=new HashMap<>(0);
        hashMap.put(null,"1");
        hashMap.put("1",null);



        MyNodeList<String>myNodeList=new MyNodeList<>();
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

        myNodeList.insertIndex(1,"1");
        myNodeList.getNext(0);
        myNodeList.getNext(1);




    }
}