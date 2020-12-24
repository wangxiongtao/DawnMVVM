package com.example.dawnmvvm.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.dawnmvvm.R;
import com.example.dawnmvvm.util.LogUtil;

public class GameActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
//        findViewById(R.id.drag_view2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LogUtil.e("onTouchEvent==onClick=");
//            }
//        });
    }
}