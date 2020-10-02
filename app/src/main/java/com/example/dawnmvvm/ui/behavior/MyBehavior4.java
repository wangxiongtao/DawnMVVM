package com.example.dawnmvvm.ui.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.dawnmvvm.util.LogUtil;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyBehavior4<V extends View> extends CoordinatorLayout.Behavior<V> {
    boolean isStart;
    boolean isVerticalUp = true;
    boolean isVerticalDown = false;

    boolean isTop = true;
    boolean isScroll = false;

    public MyBehavior4() {
    }

    public MyBehavior4(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        isStart = true;
        return true;
    }


    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);

        if (isVerticalUp&&-child.getTranslationY() >= child.getHeight() && isStart) {
            return;
        }
        if (isVerticalDown&&!isTop) {
            return;
        }

        float y = child.getTranslationY() - dy;
        if (y >= 0) {
            return;
        }
        if (-y >= child.getHeight()) {
            y = -child.getHeight();
        }
        isVerticalUp= y ==-child.getHeight();
        child.setTranslationY(y);
        consumed[1] = dy;
//        isStart = false;

//        if(-y<=child.getHeight()){
//            child.setTranslationY(y);
//            consumed[1]=dy;
//            isDown=false;
////            isDown=true;
//        }else{//头完全移出去了，此时还在滑动的情况也不让RV接受事件
////            consumed[1]=dy;
//            LogUtil.e("==nest4==onNestedPreScroll==recyclerView==true=>");
//        }

        RecyclerView recyclerView = (RecyclerView) target;


        LogUtil.e("==nest4==onNestedPreScroll==y===>" + y);
        LogUtil.e("==nest4==onNestedPreScroll==dy===>" + dy);

    }


    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed);
        if (dyConsumed > 0 && dyUnconsumed == 0) {
            LogUtil.e("=nest=onNestedScroll==上滑中=");
            isVerticalUp = true;
            isVerticalDown = false;
        }
        if (dyConsumed == 0 && dyUnconsumed > 0) {
            LogUtil.e("=nest=onNestedScroll==到边界了还在上滑=");

        }
        if (dyConsumed < 0 && dyUnconsumed == 0) {
            LogUtil.e("=nest=onNestedScroll==下滑中=");
            isVerticalDown = true;
            isVerticalUp = false;
//            button.setText("下滑动");
        }
        isScroll = true;
        if (dyConsumed == 0 && dyUnconsumed < 0) {
            LogUtil.e("=nest4=onNestedScroll==到边界了，还在下滑=");
            isTop = true;
            isScroll = true;

        } else {
            isTop = false;
        }
    }

}
