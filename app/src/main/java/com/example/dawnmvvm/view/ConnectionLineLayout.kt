package com.example.dawnmvvm.view

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.LinearLayout.VERTICAL
import android.widget.RelativeLayout
import android.widget.Toast
import com.example.dawnmvvm.bean.ActionItemBean
import com.example.dawnmvvm.util.LogUtil
import com.example.dawnmvvm.util.dp

class ConnectionLineLayout : LinearLayout {
    private var paintAnswerLine: Paint? = null;
    private var paintErrorLine: Paint? = null;
    private var paintCircle: Paint? = null;
    private var showItemList = arrayListOf<ActionItemBean>()
    private var answerItemList = arrayListOf<ActionItemBean>()
    private var leftItemLl: LinearLayout? = null;
    private var rightItemLl: LinearLayout? = null;
    private var startView: ConnectionLineView? = null;//连线的起点view，也就是选择的题目view

    private var pathLine = Path();
    private var pathErrorLine = Path();
    private var startPointF = PointF();
    private var endPointF = PointF();


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
        initList()
        addView()
        setWillNotDraw(false);
        paintAnswerLine = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            strokeWidth = 2f.dp
            pathEffect = CornerPathEffect(300f);
            strokeCap = Paint.Cap.ROUND
            color = Color.GREEN
            style = Paint.Style.STROKE

        }
        paintErrorLine = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            strokeWidth = 2f.dp
            pathEffect = CornerPathEffect(300f);
            strokeCap = Paint.Cap.ROUND
            color = Color.RED
            style = Paint.Style.STROKE

        }
        paintCircle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            strokeWidth = 2f.dp
            pathEffect = CornerPathEffect(300f);
            strokeCap = Paint.Cap.ROUND
            color = Color.parseColor("#F71628")

        }
    }

    private fun initList() {
        showItemList.add(ActionItemBean().apply {
            answerIndex = arrayOf(2);
            color = "#FF6600"
        })
        showItemList.add(ActionItemBean().apply {
            answerIndex = arrayOf(1);
            color = "#00FF00"
        })
        showItemList.add(ActionItemBean().apply {
            answerIndex = arrayOf(0);
            color = "#AA1100"
        })



        answerItemList.add(ActionItemBean().apply {
            color = "#AA1100"
            answerIndex = arrayOf(2);
        })
        answerItemList.add(ActionItemBean().apply {
            color = "#00FF00"
            answerIndex = arrayOf(1);
        })
        answerItemList.add(ActionItemBean().apply {
            color = "#FF6600"
            answerIndex = arrayOf(0);
        })
    }

    private fun addView() {
        leftItemLl = LinearLayout(context);
        leftItemLl?.orientation = VERTICAL;
        rightItemLl = LinearLayout(context);
        rightItemLl?.orientation = VERTICAL;

        //答案
        answerItemList.forEach {
            val v = ConnectionLineView(context);
            val lp = LayoutParams(75f.dp.toInt(), 75f.dp.toInt())
            lp.topMargin = 10f.dp.toInt();
            v.layoutParams = lp;
            v.setBackgroundColor(Color.parseColor(it.color))
            leftItemLl?.addView(v)
        }
        addView(leftItemLl, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            rightMargin = 150f.dp.toInt()
        })

        //题目
        showItemList.forEach {
            val v = ConnectionLineView(context);
            val lp = LayoutParams(75f.dp.toInt(), 75f.dp.toInt())
            lp.topMargin = 10f.dp.toInt();
            v.layoutParams = lp;
            v.setBackgroundColor(Color.parseColor(it.color))
            v.tag = leftItemLl?.findAnswerViewByIndex(it.answerIndex)
            rightItemLl?.addView(v)
        }
        addView(rightItemLl, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
        for (a in 0 until showItemList.size) {
            val view = leftItemLl?.getChildAt(a);
            val item = showItemList[a];
            view?.let {
                it.tag = rightItemLl?.findAnswerViewByIndex(item.answerIndex)
            }
        }


    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawCircle(width/2f,height/2f,height/3f,paintCircle!!)

        canvas?.drawPath(pathLine, paintAnswerLine!!)
        canvas?.drawPath(pathErrorLine, paintErrorLine!!)
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return true;
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (startView == null) {
                    startView = findViewByPosition(event);
                    startView?.let {
                        val rect = Rect();
                        it.getGlobalVisibleRect(rect)
                        startPointF = findLinePositionByView(it);
                        it.isSelected = true;
                    }
                }
            }
            MotionEvent.ACTION_MOVE -> {
                check(event)
            }

            MotionEvent.ACTION_UP -> {
                check(event)


            }
        }

        return true
    }

    private fun check(event: MotionEvent) {
        if (startView == null) {
            return;
        }
        val endView = findViewByPosition(event);
        endView?.let {
            if (it == startView) {//点击是同一个view
                return
            }
            if (it.isLeft == startView!!.isLeft) {//点击是同一排的view 例如点击的都是左边竖排内的view
                return
            }
            val rect = Rect();
            it.getGlobalVisibleRect(rect)
            endPointF = findLinePositionByView(it);
            val tag = startView!!.tag;//tag存放的是startView对应的答案的view
            tag?.apply {
                val list = this as ArrayList<View>?;
                list?.apply {
                    if (list.contains(it)) {
                        if (it.answerView == null) {
                            invalidatePath(1)
                            it.answerView = startView
                            startView!!.answerView = it;
                            startView!!.isSelected = false;
                            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()

                        }
                        startView = null;//重置
                    } else {
                        Toast.makeText(context, "error--->答案错误", Toast.LENGTH_SHORT).show()
                        invalidatePath(-1)
                        invalidate()
                        handler.removeCallbacksAndMessages(null)
                        handler.postDelayed({
                            invalidatePath(0)
                        }, 2000)
                    }
                }
            }
        }
    }
    private fun invalidatePath(flag:Int){
        pathErrorLine.reset()//错误的连线每次都清除掉，再接着绘制
        val dp=if(startPointF.x<endPointF.x)5f.dp else (-5f).dp
        when(flag){
            1->{//正确的连线
                pathLine.moveTo(startPointF.x+dp, startPointF.y)
                pathLine.lineTo(endPointF.x-dp, endPointF.y)
            }
            -1->{//错误的连线
                pathErrorLine.moveTo(startPointF.x+dp, startPointF.y)
                pathErrorLine.lineTo(endPointF.x-dp, endPointF.y)
            }
        }
        invalidate()
    }

    /**
     * 通过点击的坐标找出点击的view
     * @param event MotionEvent
     */
    private fun findViewByPosition(event: MotionEvent): ConnectionLineView? {
        val view: ConnectionLineView? = if (event.x <= width / 2) {
            leftItemLl?.findViewByPosition(event.rawX, event.rawY)?.apply {
                isLeft = true
            };
        } else {
            rightItemLl?.findViewByPosition(event.x, event.y)?.apply {
                isLeft = false
            };
        }

        if (startView != null) {//已经选了一个View了,则当前点击的view是第二个view
            if (view?.answerView == startView) {//第二个view有答案并且就是第一个view，则说明他们已经配对，则返回null
                return null;
            }

        } else {
            if (view?.answerView != null) {//当前点击的view是第一个view，如果已经有答案了，说明已经是配对过了，返回null
                return null;
            }
        }

        return view;
    }

    private fun findLinePositionByView(view: ConnectionLineView): PointF {
        return PointF().apply {
            val rect = Rect();
            view.getGlobalVisibleRect(rect)
            x = if (view.isLeft) {
                (leftItemLl?.right ?: 0).toFloat()
            } else {
                (rightItemLl?.left ?: 0).toFloat()
            }
            y = (rect.bottom - (view.height / 2)).toFloat();
        };
    }


}

private fun LinearLayout.findViewByPosition(x: Float, y: Float): ConnectionLineView? {
    for (a in 0 until (childCount ?: 0)) {
        val view = getChildAt(a);
        val rect = Rect();
        view?.getGlobalVisibleRect(rect)
        if (rect.contains(x.toInt(), y.toInt())) {
            return view as ConnectionLineView?;
        }
    }
    return null;
}

private fun LinearLayout.findAnswerViewByIndex(answer: Array<Int>): ArrayList<View> {
    val answerArray = ArrayList<View>()
    answer.forEach {
        apply {
            if (it < childCount) {
                val view = getChildAt(it);
                view?.apply {
                    answerArray.add(this)
                }

            }
        }


    }
    return answerArray;

}
//private fun LinearLayout.resetViewAnswer() {
// for (a in 0 until childCount){
//     val view=getChildAt(a) as ConnectionLineView
//     view.isAnswer=false;
// }
//
//}



