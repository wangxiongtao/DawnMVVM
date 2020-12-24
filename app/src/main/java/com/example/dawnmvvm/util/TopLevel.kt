package com.example.dawnmvvm.util

/**
 * kotlin顶层声明的一些属性和方法
 */
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.WindowManager


val Float.dp
    get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
    )

/**
 * 获得屏幕宽度
 *
 * @param context
 * @return
 */
fun getScreenWidth(context: Context): Int {
    val wm = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val outMetrics = DisplayMetrics()
    wm.defaultDisplay.getRealMetrics(outMetrics)
    return outMetrics.widthPixels
}

/**
 * 获得屏幕高度（包括状态栏和底部导航栏的高度）
 *
 * @param context
 * @return
 */
fun getScreenHeight(context: Context): Int {
    val wm = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val outMetrics = DisplayMetrics()
    wm.defaultDisplay.getRealMetrics(outMetrics)
    return outMetrics.heightPixels
}
fun getScreenshot(view: View): Bitmap? {
    view.buildDrawingCache()
    return view.drawingCache
}
fun  captureView(view: View): Bitmap? {
    view.isDrawingCacheEnabled = true
    view.buildDrawingCache()
    // 重新测量一遍View的宽高
    view.measure(View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(view.height, View.MeasureSpec.EXACTLY))
    // 确定View的位置
    view.layout(view.x.toInt(), view.y.toInt(), view.x.toInt() + view.measuredWidth,
            view.y.toInt() + view.measuredHeight)
    // 生成View宽高一样的Bitmap
    val bitmap = Bitmap.createBitmap(view.drawingCache, 0, 0, view.measuredWidth,
            view.measuredHeight)
    view.isDrawingCacheEnabled = false
    view.destroyDrawingCache()
    return bitmap
}

