package com.example.dawnmvvm.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ColorDotView : View {
    private var paintDot: Paint = Paint()
    var dotColor = "#000000"
        set(value) {
            field = value
            paintDot.apply {
                color = Color.parseColor(value)
                flags = Paint.ANTI_ALIAS_FLAG
            }
            invalidate()
        }

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {

    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
            context,
            attributeSet,
            defStyleAttr
    ) {

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val r = width * 0.5f;
        if (isSelected) {
            paintDot.alpha = 80;
            canvas?.drawCircle(r, r, r, paintDot)
            paintDot.alpha = 155;
            canvas?.drawCircle(r, r, r * 0.8f, paintDot)
            paintDot.alpha = 255;
            canvas?.drawCircle(r, r, r * 0.6f, paintDot)
        } else {
            paintDot.alpha = 255;
            canvas?.drawCircle(r, r, r * 0.6f, paintDot)
        }
    }

}