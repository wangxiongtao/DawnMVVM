package com.example.dawnmvvm.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.dawnmvvm.util.dp


class PaintView : View {
    private var mPaint: Paint = Paint()
    private var mPathEffect: PathEffect = CornerPathEffect(200f)
    private var mPath: Path = Path()
    var drawBitmap: Bitmap? = null;
    private var drawCanvas: Canvas? = null;

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
        setBackgroundColor(Color.WHITE)
        mPaint.apply {
            strokeWidth = 5f.dp;
            style = Paint.Style.FILL;
            color = Color.parseColor("#F7101A")
            pathEffect = mPathEffect
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            flags = Paint.ANTI_ALIAS_FLAG
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                mPath.moveTo(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                mPath.lineTo(event.x, event.y)
                postInvalidate()

            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                mPath.lineTo(event.x, event.y)
                postInvalidate()
            }
        }

        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (drawBitmap == null) {
            drawBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            drawCanvas = Canvas(drawBitmap!!);
        }
        drawCanvas!!.drawCircle(width*0.5f, width*0.5f,width*0.3f,mPaint)
//        drawCanvas!!.drawPath(mPath, mPaint)
        canvas.drawBitmap(drawBitmap!!, 0f, 0f, mPaint)
    }

    fun setPaintConfig(color: String, w: Float = 2F.dp) {
        mPath.reset()
        mPaint.color = Color.parseColor(color)
        mPaint.strokeWidth = w;

    }

    fun setPaintConfig(color: Int, w: Float = 2F.dp) {
        mPath.reset()
        mPaint.color = color
        mPaint.strokeWidth = w;

    }

}