package com.example.dawnmvvm.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import androidx.annotation.Nullable;

import com.example.dawnmvvm.R;
import com.example.dawnmvvm.util.LogUtil;

public class MyValueAnimaView2 extends View {
    Paint paint;
    int height;
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int count=0;
    ValueAnimator valueAnimator;
    RectF rectF;
    private boolean isComplete;
    private Rect minRect = new Rect();

    public MyValueAnimaView2(Context context) {
        super(context);
        init();
    }

    public MyValueAnimaView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyValueAnimaView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

    public void start() {
        valueAnimator = ValueAnimator.ofInt(count * height, (count + 1) * height);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.setDuration(1000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                count++;
                invalidate();
            }
        });
//        valueAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                count++;
//                if (count >= 8) {
//                    return;
//                }
//                valueAnimator.setIntValues(count * height, (count + 1) * height);
//                valueAnimator.start();
//            }
//        });
        valueAnimator.start();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.e("==animation==onDraw===>");
        if (count > 3) {
            return;
        }
//        canvas.save();

        canvas.translate(0, (float) (getHeight() * count));
//        int left = -getHeight() * count;
//        rectF.set(0, left, getWidth(), left + getHeight());
//        drawCenterText(canvas, arr[count] + "", rectF);
//        canvas.restore();
        for (int i = 0; i <= count; i++) {
            int left = -getHeight() * i;
            rectF.set(0, left, getWidth(), left + getHeight());
            drawCenterText(canvas, arr[i] + "", rectF);
        }
//
//        count++;

//        rectF.set(0, 0, getWidth(), getHeight()*1);
//        drawCenterText(canvas,   "12345", rectF);
//        rectF.set(0, getHeight(), getWidth(), getHeight()*2);
//        drawCenterText(canvas,   "0000", rectF);

//        for (int i = 0; i < arr.length; i++) {
//            int left = -getHeight() * i;
//            rectF.set(0, left, getWidth(), left + getHeight());
//            drawCenterText(canvas, arr[i] + "", rectF);
//        }
//        if (!isComplete) {
//            start();
//            isComplete = true;
//        }


    }

    private void drawCenterText(Canvas canvas, String text, RectF rectF) {
        //计算baseline
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        float baseline = rectF.centerY() + distance;
        canvas.drawText(text, rectF.centerX(), baseline, paint);
    }

}
