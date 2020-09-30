package com.example.dawnmvvm.ui.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.example.dawnmvvm.util.LogUtil;

public class MyBehavior2<V extends View> extends CoordinatorLayout.Behavior<V> {
    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;
    public MyBehavior2() {
    }

    public MyBehavior2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**页面开始滑动。
     * onStartNestedScroll望文生义啊，
     * 开始嵌套滚动的时候被调用，那么这个方法有一个boolean的返回值，
     * 是需要我们告诉CoordinatorLayout我
     * 这个Behavior要监听的滑动方向，
     * 因为我们是上下滑动时显示/隐藏FAB，
     * 所以这里我们返回return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;。
     *
     */
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {

        LogUtil.e("=nest=onStartNestedScroll===");
        return  axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }


    // 页面正在滑动。
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed);
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

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type);
        LogUtil.e("=nest=onStopNestedScroll==停止=");
    }
}
