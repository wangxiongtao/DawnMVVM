package com.example.dawnmvvm.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.example.dawnmvvm.R;
import com.example.dawnmvvm.util.LogUtil;

public class MyValueAnimaView extends View {
    Paint paint;
    int height;
    String[] arr;
    int count;
    ValueAnimator valueAnimator;
    RectF rectF;
    private boolean isComplete;
    private Rect minRect = new Rect();

    public MyValueAnimaView(Context context) {
        super(context);
        init();
    }

    public MyValueAnimaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyValueAnimaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        rectF = new RectF();
        int textSize = getResources().getDimensionPixelSize(R.dimen.font1);
        height = getResources().getDimensionPixelSize(R.dimen.font2);
        paint = new Paint();
        paint.setTextSize(textSize);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        paint.setTextAlign(Paint.Align.CENTER);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }


    public void setArr(String[] arr) {
        this.arr = arr;
        invalidate();
    }
    public void setNums(int num) {
        arr=new String[num+1];
        for (int i=0;i<=num;i++){
            arr[i]=i+"";
        }
        invalidate();
    }

    public void start() {
        valueAnimator = ValueAnimator.ofInt(count * height, (count + 1) * height);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setDuration(300);

        valueAnimator.addUpdateListener(animation -> scrollTo(0, -(Integer) animation.getAnimatedValue()));
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                count++;
                if (count >= arr.length-1) {
                    return;
                }
                valueAnimator.setIntValues(count * height, (count + 1) * height);
                valueAnimator.start();
            }
        });
        valueAnimator.start();

    }



    private void starValueAnimation(){
        if(valueAnimator==null){
            valueAnimator=new ValueAnimator();
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.setDuration(100);
            valueAnimator.addUpdateListener(animation -> scrollTo(0, -(Integer) animation.getAnimatedValue()));
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    count++;
                    if (count >= arr.length-1) {
                        return;
                    }
                    starValueAnimation();
                }
            });
        }
        valueAnimator.setIntValues(count * height, (count + 1) * height);
        valueAnimator.start();


    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (arr == null) {
            return;
        }
        LogUtil.e("==animation==onDraw===>");
        int size = count + 2;
        if (size >= arr.length) {
            size = arr.length;
        }
        for (int i = count; i < size; i++) {
            int left = -getHeight() * i;
            rectF.set(0, left, getWidth(), left + getHeight());
            drawCenterText(canvas, arr[i] + "", rectF);
        }
        if (!isComplete&&arr.length>1) {
            starValueAnimation();
            isComplete = true;
        }


    }

    private void drawCenterText(Canvas canvas, String text, RectF rectF) {
        //计算baseline
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        float baseline = rectF.centerY() + distance;
        canvas.drawText(text, rectF.centerX(), baseline, paint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        valueAnimator.cancel();
        valueAnimator.removeAllUpdateListeners();
        valueAnimator.removeAllListeners();
        valueAnimator=null;

    }
}
