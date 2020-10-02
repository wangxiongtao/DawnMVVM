package com.example.dawnmvvm.ui.behavior;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.example.dawnmvvm.util.LogUtil;

public class MyBehavior2<V extends View> extends CoordinatorLayout.Behavior<V> {
    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;
    int offsetTotal = 0;
    boolean isStart;
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
    /**
     *  处理 子View 的滚动事件
     */
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,  @NonNull V child, @NonNull View target, int dx, int dy, int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
//        offset(child, dy);
    }

    // 页面正在滑动。
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed);
        //        Button button= (Button) child;
        if (dyConsumed > 0 && dyUnconsumed == 0) {
            LogUtil.e("=nest=onNestedScroll==上滑中=");
//            button.setText("上滑动");
        }
        if (dyConsumed == 0 && dyUnconsumed > 0) {
            LogUtil.e("=nest=onNestedScroll==到边界了还在上滑=");
        }
        if (dyConsumed < 0 && dyUnconsumed == 0) {
            LogUtil.e("=nest=onNestedScroll==下滑中=");
//            button.setText("下滑动");
        }
        if (dyConsumed == 0 && dyUnconsumed < 0) {
            LogUtil.e("=nest=onNestedScroll==到边界了，还在下滑=");
        }
//        child.offsetTopAndBottom(100);
        if(isStart){
            return;
        }
        isStart=true;
        ViewCompat.animate(child)
                .translationX(child.getWidth())
                .setDuration(800)
                .start();


    }
    public void offset(View child, int dy) {
        // 上次保存的位置
//        int old = offsetTotal;
//        // 当前的位置
//        int curr = offsetTotal - dy;
//        // 保证子控件的位置一直在 0-控件高度之间
//        curr = Math.max(curr, -child.getHeight());
//        curr = Math.min(curr, 0);
//        offsetTotal = curr;
//        if (old == offsetTotal) {
//            return;
//        }
//        // 原来的位置 - 当前的位置 = 要移动的位置
//        int delta = old - offsetTotal;
        child.offsetTopAndBottom(-10);
    }
    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type);
        LogUtil.e("=nest=onStopNestedScroll==停止=");
        ViewCompat.animate(child)
                .translationX(0)
                .setDuration(800)
                .start();
        isStart=false;
    }
}
