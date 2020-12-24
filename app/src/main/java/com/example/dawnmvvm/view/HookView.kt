package com.example.dawnmvvm.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.example.dawnmvvm.util.dp

class HookView : View {
    private var pathHook:Path?=null;
    private var paintHook = Paint()
    private var progress:Float=0.1f;

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
            style=Paint.Style.STROKE
            color=Color.BLUE
            strokeWidth=10f.dp
            strokeCap = Paint.Cap.ROUND
           strokeJoin=Paint.Join.ROUND
        }
        var anim=ValueAnimator.ofFloat(0.1f,1f);
        anim.addUpdateListener {
            progress=it.animatedValue as Float;
            invalidate()
        }
        anim.duration=1000;
        anim.start()
        setOnClickListener {
            progress+=0.1f;
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if(pathHook==null){
            pathHook= Path();
            pathHook!!.moveTo(10f.dp,50f.dp);
        }
//        pathHook!!.lineTo(50f.dp,50f.dp);
//
//        canvas?.drawPath(pathHook!!,paintHook)


        pathHook!!.lineTo(10f.dp*progress+10f.dp,10f.dp*progress+50f.dp);
        canvas?.drawPath(pathHook!!,paintHook)

//        progress?.let {
//            if(pathHook==null){
//                pathHook= Path();
//                pathHook!!.moveTo(10f.dp,10f.dp);
//            }
//            paintHook.color=Color.RED;
//            pathHook!!.lineTo(40f.dp*it,80f.dp*it)
////        pathHook.lineTo(80f.dp,10f.dp)
//            canvas?.drawPath(pathHook!!,paintHook)
//        }

    }
}