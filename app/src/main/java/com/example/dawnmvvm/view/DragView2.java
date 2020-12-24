package com.example.dawnmvvm.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dawnmvvm.util.LogUtil;

public class DragView2 extends View {

    public DragView2(Context context) {
        super(context);
        setEnabled(true);
        setClickable(true);
    }

    public DragView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setEnabled(true);
        setClickable(true);
    }

    public DragView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setEnabled(true);
        setClickable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.e("=onTouchEvent=getAction==>"+event.getAction());
        return super.onTouchEvent(event);
    }
}
