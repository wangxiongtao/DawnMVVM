package com.example.dawnmvvm.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyCustomerView extends View {
    private Paint textPaint;
    private Paint pathPaint;
    public MyCustomerView(Context context) {
        super(context);
        init();
    }

    public MyCustomerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCustomerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        textPaint=new Paint();
        textPaint.setColor(Color.parseColor("#FF6600"));
        textPaint.setTextSize(50);

        pathPaint=new Paint();
        pathPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawTextOnPath();
        Path path = new Path();
        //  绘制一个圆形的路径，文字会在该圆的上边缘外侧绘制
        path.addCircle(getWidth() >> 1, getWidth() >> 1, getWidth() >> 1, Path.Direction.CCW);
//        canvas.drawPath(path,pathPaint);
        canvas.drawTextOnPath("12345678945612378911565601234567894561237891156560123456789456123789115656012345678945612378911565601234567894561237891156560123456789456123789115656012345678945612378911565601234567894561237891156560",path,0,0,textPaint);
        path.reset();
//
//        path.moveTo(0,400);
//        path.lineTo(200,400);
//        path.lineTo(300,500);
//        canvas.drawPath(path,pathPaint);
//        canvas.drawTextOnPath("1234567894561237891156560",path,0,0,textPaint);


        path.reset();
        path.moveTo(0,100);
        path.quadTo(200,0,400,400);
        canvas.drawPath(path,pathPaint);
        canvas.drawTextOnPath("1234567894561237891156560",path,50,0,textPaint);


    }
}
