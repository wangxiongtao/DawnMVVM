package com.example.dawnmvvm.view;


import android.view.MotionEvent;
import android.view.View;

public class FloatDragView implements View.OnTouchListener {
    private View targetView;
    private int downX;
    private int downY;

    public FloatDragView setTargetView(View targetView) {
        this.targetView = targetView;
        this.targetView.setClickable(true);
        this.targetView.setOnTouchListener(this);
        return this;
    }
    public FloatDragView setOffset(int offsetY,int offsetX) {
        targetView.post(new Runnable() {
            @Override
            public void run() {
                targetView.offsetTopAndBottom(offsetY);
                targetView.offsetLeftAndRight(offsetX);
            }
        });

        return this;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX=x;
                downY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - downX;
                int offsetY = y - downY;
                targetView.offsetTopAndBottom(offsetY);
                targetView.offsetLeftAndRight(offsetX);

                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }
}
