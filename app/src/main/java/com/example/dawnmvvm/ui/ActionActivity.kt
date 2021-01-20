package com.example.dawnmvvm.ui

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.example.dawnmvvm.R
import com.example.dawnmvvm.util.*
import com.example.dawnmvvm.view.PaintLayout
import com.example.dawnmvvm.view.PaintView
import yxr.com.library.SimilarityCallBack
import yxr.com.library.SimilarityHelper


class ActionActivity : AppCompatActivity() {
    private var paintView:PaintView?=null;
    private var showImg:ImageView?=null;
    private var colorLL:LinearLayout?=null;
    private var mColorDialog:AlertDialog?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action)
//        var iv=findViewById<ImageView>(R.id.test);
//        iv.setOnClickListener(){
//            var r=Rect()
//            iv.getGlobalVisibleRect(r)
//            LogUtil.e("==Rect1==>${r}")
//            iv.getLocalVisibleRect(r)
//            LogUtil.e("==Rect2==>${r}")
//        }

        paintView=findViewById<PaintView>(R.id.paint_img)
         showImg=findViewById<ImageView>(R.id.show_img)
         colorLL=findViewById<LinearLayout>(R.id.color_ll)
        findViewById<PaintLayout>(R.id.paint_ll).setShowUrl("https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/b812c8fcc3cec3fdba231619d788d43f879427b8.jpg")
//        findViewById<PaintLayout>(R.id.paint_ll).setShowUrl("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.ewebweb.com%2Fuploads%2F20190929%2F23%2F1569772431-AkDnGjVEsr.jpg&refer=http%3A%2F%2Fimg.ewebweb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1611991882&t=25a5c87b173f5eab5a5b5012902cd962")
//        findViewById<PaintLayout>(R.id.paint_ll).setShowUrl("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2128476796,2953309141&fm=26&gp=0.jpg")

    }

    fun paintBig(view: View) {

    }

    fun clear(view: View) {
        paintView?.setPaintConfig(Color.WHITE, 20f.dp)
    }

    fun selectColor(view: View) {
        showColorDialog()
    }
    private fun showColorDialog() {
        if (mColorDialog == null) {
            mColorDialog = AlertDialog.Builder(this)
                    .setTitle("选择颜色")
                    .setSingleChoiceItems(arrayOf("黑色", "绿色", "蓝色", "橙色", "紫色", "红色"), 0
                    ) { dialog, which ->
                        when (which) {
                            0 -> paintView?.setPaintConfig(Color.parseColor("#283E53"))
                            1 -> paintView?.setPaintConfig(Color.GREEN)
                            2 -> paintView?.setPaintConfig(Color.parseColor("#4FA3C7"))
                            3 -> paintView?.setPaintConfig(Color.parseColor("#FF5F01"))
                            4 -> paintView?.setPaintConfig(Color.parseColor("#7252FF"))
                            5 -> paintView?.setPaintConfig(Color.parseColor("#F7101A"))
                            else -> {
                            }
                        }
                        dialog.dismiss()
                    }.create()
        }
        mColorDialog?.show()
    }

    fun score(view: View) {
        val bd = showImg?.drawable as BitmapDrawable
        val bm: Bitmap =  bd.bitmap;
        paletteColor(bm)
//        val long1=SimilarPhoto.getFingerPrint(bm);
//        val long2=SimilarPhoto.getFingerPrint(captureView(paintView!!));
//        LogUtil.e("==diffNum1===>${SimilarPhoto.hamDist(long1, long2)}")
//        LogUtil.e("==diffNum2===>${SimilarPicture.diff(bm, captureView(paintView!!))}")



//        SimilarityHelper.instance().similarity(bm, captureView(paintView!!), object : SimilarityCallBack {
//
//            override fun onSimilarityStart() {
//                LogUtil.e("diffNum=====>start" )
//            }
//
//            override fun onSimilaritySuccess(similarity: Int, different: Int) {
//                LogUtil.e("diffNum=====>匹配度为 ： " + similarity.toFloat() / (similarity + different) * 100 + " %")
//            }
//
//            override fun onSimilarityError(reason: String) {
//                LogUtil.e("diffNum=====>error ： $reason" )
//
//            }
//        })







//        when (SimilarPhoto.hamDist(long1, long2)){
//            in 0..23 -> {
//                Toast.makeText(this, "3星", Toast.LENGTH_SHORT).show()
//            }
//            in 24..28 -> {
//                Toast.makeText(this, "2星", Toast.LENGTH_SHORT).show()
//            }
//            else->{
//                Toast.makeText(this, "1星", Toast.LENGTH_SHORT).show()
//            }
//        }
//        LogUtil.e("==diffNum1===>${SimilarPhoto.hamDist(long1, long2)}")
//        LogUtil.e("==diffNum2===>${SimilarPicture.diff(bm, captureView(paintView!!))}")














    }

    /**
     * 从bitmap获取主色调及权重
     */
    fun paletteColor(bitmap: Bitmap) {
        val builder = Palette.from(bitmap)
        builder.maximumColorCount(8)
        builder.generate { palette ->
            palette?.let {
                val swatches = palette.swatches
                swatches.forEach {
                    Log.e("Palette", "$it ${it.population}")
                    val view=TextView(this);
                    view.text= "${it.population}  #${Integer.toHexString(it.rgb)}"
//                    view.text= "#"+Integer.toHexString(it.rgb)
                    view.setBackgroundColor(it.rgb);
                    view.setPadding(15,15,15,15);
                    colorLL!!.addView(view);

                }
                //这里取权重靠前的五个颜色
//                val take = swatches.toMutableList().sortedByDescending { it.population }.take(5)
//                val sum = take.sumBy { it.population }
//                val result = ArrayList<ColorInfo>(take.size)
//                take.forEach {
//                    result.add(ColorInfo(it.rgb, it.population.toFloat() / sum))
//                }
//                iColor?.result(result)
            }

        }
    }
}