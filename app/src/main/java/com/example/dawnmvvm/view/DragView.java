package com.example.dawnmvvm.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dawnmvvm.R;
import com.example.dawnmvvm.util.LogUtil;

public class DragView extends View {
    private int downX;
    private int downY;
    private int targetLeft=-1;
    private int targetTop=-1;
    private int oriLeft=-1;
    private int oriTop=-1;
    private RecyclerView questionRv;
    private int targetPosition;
    public DragView(Context context) {
        super(context);
        setEnabled(true);
        setClickable(true);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setEnabled(true);
        setClickable(true);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setEnabled(true);
        setClickable(true);
    }


    public void setQuestionRv(RecyclerView questionRv,int targetPosition) {
        this.questionRv = questionRv;
        this.targetPosition=targetPosition;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                initLeftAndTop();
                LogUtil.e("=onTouchEvent=targetTop==>"+targetTop);
                LogUtil.e("=onTouchEvent=targetLeft==>"+targetLeft);
                downX=x;
                downY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - downX;
                int offsetY = y - downY;
                offsetTopAndBottom(offsetY);
                offsetLeftAndRight(offsetX);

                break;
            case MotionEvent.ACTION_UP:
                if(isSuccess()){
                    offsetTopAndBottom(targetTop-getTop());
                    offsetLeftAndRight(targetLeft-getLeft());
                }else {
                    offsetTopAndBottom(oriTop-getTop());
                    offsetLeftAndRight(oriLeft-getLeft());
                }
                break;
        }
        return super.onTouchEvent(event);
    }


    private boolean isSuccess(){
        boolean topSuccess=getTop()>=targetTop&&getTop()<=targetTop+getHeight()*0.8;
        boolean leftSuccess1=getLeft()>=(targetLeft-getWidth()*0.5)&&getLeft()<=(targetLeft+getWidth()*0.8);
        return  topSuccess&&leftSuccess1;
    }





    private void initLeftAndTop(){
        if(oriTop<0){
            oriTop=getTop();
        }
        if(oriLeft<0){
            oriLeft=getLeft();
        }
        View targetView=questionRv.getLayoutManager().findViewByPosition(targetPosition);
        if(targetView==null){
            return;
        }
        if(targetTop<0){
            targetTop=targetView.getTop()+questionRv.getTop();
        }
        if(targetLeft<0){
            targetLeft=targetView.getLeft()+questionRv.getLeft();
        }
    }
}
