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
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.Nullable;

import com.example.dawnmvvm.R;
import com.example.dawnmvvm.util.LogUtil;

public class MyValueAnimaView extends View {
    Paint paint;
    int height;
    int []arr={1,2,3,4,5};
    int count;
    ValueAnimator valueAnimator;
    RectF rectF;
    private boolean isComplete;
    private Rect minRect=new Rect();
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
    private void init(){
        rectF=new RectF();
        int textSize = getResources().getDimensionPixelSize(R.dimen.font1);
        height=getResources().getDimensionPixelSize(R.dimen.font2);
        paint=new Paint();
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
    public void start(){
        valueAnimator=ValueAnimator.ofInt(count * height, (count + 1) * height);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(200);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scrollTo(0, -(Integer) animation.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                count++;
                if(count>=4){
                    return;
                }
                valueAnimator.setIntValues(count * height, (count + 1) * height);
                valueAnimator.start();
            }
        });
        valueAnimator.start();

    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

            LogUtil.e("==animation==onDraw===>");
            int size=count+2;
            if(size>=5){
                size=5;
            }
            for (int i=count;i<size;i++){
                int left=-getHeight()*i;
                rectF.set(0,left,getWidth(),left+getHeight());
                drawCenterText(canvas,arr[i]+"",rectF);
            }
           if(!isComplete){
               start();
               isComplete=true;
           }


    }
    private void drawCenterText(Canvas canvas,String text,RectF rectF){
        //计算baseline
        Paint.FontMetrics fontMetrics=paint.getFontMetrics();
        float distance=(fontMetrics.bottom - fontMetrics.top)/2 - fontMetrics.bottom;
        float baseline=rectF.centerY()+distance;
        canvas.drawText(text, rectF.centerX(), baseline, paint);
    }

}
