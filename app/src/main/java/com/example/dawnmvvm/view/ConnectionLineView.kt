package com.example.dawnmvvm.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.example.dawnmvvm.util.LogUtil

class ConnectionLineView: View {
    var isLeft:Boolean=false//当前view是否在屏幕左边区域
    var answerView:ConnectionLineView?=null//
    var oriBackgroundColor: Int?=null
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

    override fun setBackgroundColor(color: Int) {
        super.setBackgroundColor(color)
        if(oriBackgroundColor==null){
            oriBackgroundColor=color
        }
    }


    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        if(selected){
            setBackgroundColor(Color.parseColor("#000000"))
        }else{
            setBackgroundColor(oriBackgroundColor!!)
        }
    }


}