package com.example.dawnmvvm.view

import android.R.attr.path
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnticipateInterpolator
import android.view.animation.DecelerateInterpolator
import com.example.dawnmvvm.util.dp


class HookView : View {
    private var paintHook = Paint()
    private var progress: Float = 0f;
    private var progress2: Float = -1.0f;
    private var pathSrc: Path = Path();
    private var pathDist: Path = Path();
    private var pathMeasure: PathMeasure = PathMeasure();


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
            context,
            attributeSet,
            defStyleAttr
    ) {
        init()
    }

    private fun init() {
        paintHook.apply {
            style = Paint.Style.STROKE
            color = Color.parseColor("#FF9600")
            strokeWidth = 1f.dp
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            flags = Paint.ANTI_ALIAS_FLAG
        }


        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 1000
            interpolator=AccelerateInterpolator()
            addUpdateListener {
                progress = it.animatedValue as Float;
                invalidate()
            }
        }.start();

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (pathMeasure.length == 0f) {
            pathSrc.moveTo(2f.dp, height *0.5f);
            pathSrc.lineTo(width*0.4f, height*0.95f);
            pathSrc.lineTo(width * 0.9f,height*0.2f)
            pathMeasure.setPath(pathSrc, false)
        }
        pathMeasure.getSegment(0f, pathMeasure.length * progress, pathDist, true)
        canvas?.drawPath(pathDist, paintHook)
    }
}