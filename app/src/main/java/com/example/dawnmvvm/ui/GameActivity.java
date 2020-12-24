package com.example.dawnmvvm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.dawnmvvm.R;
import com.example.dawnmvvm.ui.work.MyJobMyService;
import com.example.dawnmvvm.ui.work.MyWork;
import com.example.dawnmvvm.util.LogUtil;
import com.example.dawnmvvm.view.MatchGameLayout;

public class GameActivity extends AppCompatActivity {
private int a=100;
private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        FrameLayout layout=findViewById(R.id.fl);
        MatchGameLayout layout1=new MatchGameLayout(this);
        layout1.init(5);
        layout.addView(layout1);
//        recyclerView=findViewById(R.id.show_rv);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
//        recyclerView.setAdapter(new MatchGameAdapter());
//
//        DragView dragView=findViewById(R.id.drag_view);
//        dragView.setQuestionRv(recyclerView,3);
//        View view1=findViewById(R.id.drag_view2);
//        view1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                View view=recyclerView.getLayoutManager().findViewByPosition(3);
//                LogUtil.e("=onTouchEvent==View===>"+view);
//                LogUtil.e("=onTouchEvent==View1===>"+(view.getLeft()+recyclerView.getLeft()));
//                LogUtil.e("=onTouchEvent==View2===>"+recyclerView.getTop());
//            }
//        });
////        dragView.setTargetView(view);
//        dragView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                dragView.setTranslationX(a);
////                dragView.setTranslationY(a);
////                a++;
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        doWork();


    }

    public void doWork(){
        Data inputData = new Data.Builder().putString("input_data", "==MyWork==Hello World!").build();
        Constraints constraints = new Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .setRequiresStorageNotLow(true)
                .build();
        OneTimeWorkRequest request=new OneTimeWorkRequest.Builder(MyWork.class)
                .setConstraints(constraints)
                .addTag("tag_MyWork")
                .setInputData(inputData)
                .build();
       WorkManager.getInstance(this).enqueue(request);
       WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId()).observe(this, workInfo -> {
           LogUtil.e("==MyWork=workInfo==>"+workInfo);

       });
    }
}














