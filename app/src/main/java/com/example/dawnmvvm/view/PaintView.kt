package com.example.dawnmvvm.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.dawnmvvm.util.dp


class PaintView : View {
    private var mPaint: Paint = Paint()
    private var mPaint2: Paint = Paint()
    private var mPathEffect: PathEffect = CornerPathEffect(200f)
    private var mPath: Path = Path()
    private var matrix1 = Matrix()
     var drawBitmap:Bitmap?=null;
    private var drawCanvas:Canvas?=null;
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
            style = Paint.Style.STROKE;
            color = Color.parseColor("#F7101A")
            pathEffect = mPathEffect
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
        mPaint2.apply {
            strokeWidth = 5f.dp;
            style = Paint.Style.FILL;
            color = Color.WHITE
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
            drawCanvas= Canvas(drawBitmap!!);
        }
        drawCanvas!!.drawPath(mPath,mPaint)
//        drawCanvas!!.drawCircle(width/2f,height/2f,120f.dp,mPaint)
//        drawCanvas!!.drawRect(50f.dp,180f.dp,250f.dp,200f.dp,mPaint2)
        canvas.drawBitmap(drawBitmap!!,0f,0f,mPaint)


//        canvas.drawCircle(width/2f,height/2f,120f.dp,mPaint)
//        canvas!!.drawRect(50f.dp,180f.dp,250f.dp,200f.dp,mPaint2)
    }

    fun setStrokeWidth(w: Float){
        mPath.reset()
        mPaint.strokeWidth=w;

    }
    fun setPaintColor(color: Int,w: Float=2F.dp){
        mPath.reset()
        mPaint.color=color
        mPaint.strokeWidth=w;

    }

}