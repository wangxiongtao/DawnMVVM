package com.example.dawnmvvm.ui.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.example.dawnmvvm.util.LogUtil;

public class MyBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;
    public MyBehavior() {
    }

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull V child, @NonNull View dependency) {
        return dependency instanceof Button;
    }



    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        LogUtil.e("onDependentViewChanged==1=="+ dependency.getY());
//        if (deltaY == 0) {
//            deltaY = dependency.getY() - child.getHeight();
//        }
//
//        float dy = dependency.getY() - child.getHeight();
//        dy = dy < 0 ? 0 : dy;
//        float y = -(dy / deltaY) * child.getHeight();
//        LogUtil.e("onDependentViewChanged===="+y);
//        CoordinatorLayout.LayoutParams layoutParams= (CoordinatorLayout.LayoutParams) child.getLayoutParams();
//
////        if(dependency.getY()<child.getHeight()){
////            layoutParams.height=200;
////        }else {
////            layoutParams.height= (int) dependency.getY();
//////            child.setTranslationY(dependency.getY()-child.getHeight());
//////        }
//        layoutParams.topMargin= 200;
//        int d= (int) dependency.getY();
//        child.setPadding(0,d,0,d);
        ViewCompat.animate(child)
                .scaleX(dependency.getY()/100)
                .scaleY(dependency.getY()/100)
                .alpha(1.0f)
                .setDuration(800)
                .start();


        return true;
    }
}
