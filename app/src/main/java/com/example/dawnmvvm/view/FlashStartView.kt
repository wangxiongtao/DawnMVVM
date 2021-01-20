package com.example.dawnmvvm.view

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.example.dawnmvvm.R
import com.example.dawnmvvm.util.LogUtil

class FlashStartView : androidx.appcompat.widget.AppCompatImageView {
    private val animHandler: Handler = Handler(Looper.getMainLooper());
    private var color:String?=null;

    constructor(c: Context) : super(c) {
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
        setImageResource(R.mipmap.img_score_small_start)
        rotation = 45f
        setColorFilter(Color.parseColor("#FFFC34"))

    }

    public fun startFlash(delayMillis: Long = 0L) {
        animHandler.postDelayed({
            startAnimation(
                    ScaleAnimation(1f, 0.2f, 1f, 0.2f,0.5f,0.5f).apply {
                        duration=300
                        repeatCount = 3
                        fillAfter=true
                        repeatMode = Animation.REVERSE


                    });
        }, delayMillis)


//        animHandler.postDelayed(object : Runnable {
//            override fun run() {
//                if(color==null){
//                    color="#FFFC34"
//                }else if (color == "#FFFC34") {
//                    color="#88000000"
//
//                } else if ( color=="#88000000"){
//                    color=null
//                }
//                if(color==null){
//                    colorFilter = null
//                }else{
//                    setColorFilter(Color.parseColor(color))
//                }
//                animHandler.postDelayed(this, 100)
//            }
//        }, delayMillis)
    }


}