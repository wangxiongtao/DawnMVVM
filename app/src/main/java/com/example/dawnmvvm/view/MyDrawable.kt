package com.example.dawnmvvm.view

import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Log
import com.example.dawnmvvm.util.dp


class MyDrawable:Drawable() {
    private var rectF:RectF= RectF();
    private var paint:Paint=Paint();
    init {
        paint.apply {
            color=Color.GRAY
            style=Paint.Style.STROKE
            strokeWidth=8f.dp;
            flags=Paint.ANTI_ALIAS_FLAG
            strokeJoin=Paint.Join.ROUND

        }
    }
    override fun draw(canvas: Canvas) {
//        canvas.drawColor(Color.parseColor("#FF6600"))
//        canvas.drawRoundRect(rectF,20f.dp,20f.dp,paint)
        val width = bounds.width()
        val height = bounds.height()
        rectF= RectF(bounds.left.toFloat(),bounds.top.toFloat(),bounds.right.toFloat(),bounds.bottom.toFloat(),)
        canvas.drawRoundRect(rectF,20f.dp,20f.dp,paint)
    }

    override fun setAlpha(alpha: Int) {
        TODO("Not yet implemented")
        paint.alpha=alpha;
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        TODO("Not yet implemented")
        paint.colorFilter=colorFilter;
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT;
    }
    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)
        rectF = RectF(100f, 100f, 300f, 200f)
//        rectF = RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())
    }
}