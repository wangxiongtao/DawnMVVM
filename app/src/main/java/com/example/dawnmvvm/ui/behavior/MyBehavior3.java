package com.example.dawnmvvm.ui.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.dawnmvvm.util.LogUtil;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

public class MyBehavior3<V extends View> extends CoordinatorLayout.Behavior<V> {
    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;
    int offsetTotal = 0;
    boolean isStart;
    public MyBehavior3() {
    }

    public MyBehavior3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull V child, int layoutDirection) {
        child.setTranslationY(525);
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        float transY =  child.getTranslationY() - dy;
        if(transY>0){
            child.setTranslationY(transY);
            consumed[1]=dy;
        }

        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed);
        LogUtil.e("=nest=onNestedScroll=start==");
        if (dyConsumed > 0 && dyUnconsumed == 0) {
            LogUtil.e("=nest=onNestedScroll==上滑中=");
        }
        if (dyConsumed == 0 && dyUnconsumed > 0) {
            LogUtil.e("=nest=onNestedScroll==到边界了还在上滑=");
        }
        if (dyConsumed < 0 && dyUnconsumed == 0) {
            LogUtil.e("=nest=onNestedScroll==下滑中=");
        }
        if (dyConsumed == 0 && dyUnconsumed < 0) {
            LogUtil.e("=nest=onNestedScroll==到边界了，还在下滑=");
        }


    }
}
