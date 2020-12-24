package com.example.dawnmvvm.ui

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dawnmvvm.R
import com.example.dawnmvvm.util.*
import com.example.dawnmvvm.view.PaintView


class ActionActivity : AppCompatActivity() {
    private var paintView:PaintView?=null;
    private var showImg:ImageView?=null;
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

    }

    fun paintBig(view: View) {
        paintView?.setStrokeWidth(20f.dp)
    }

    fun clear(view: View) {
        paintView?.setPaintColor(Color.WHITE,20f.dp)
    }

    fun selectColor(view: View) {
        showColorDialog()
    }
    private fun showColorDialog() {
        if (mColorDialog == null) {
            mColorDialog = AlertDialog.Builder(this)
                    .setTitle("选择颜色")
                    .setSingleChoiceItems(arrayOf("黑色", "绿色", "蓝色","橙色","紫色","红色"), 0
                    ) { dialog, which ->
                        when (which) {
                            0 -> paintView?.setPaintColor(Color.parseColor("#283E53"))
                            1 ->paintView?.setPaintColor(Color.GREEN)
                            2 ->paintView?.setPaintColor(Color.parseColor("#4FA3C7"))
                            3 ->paintView?.setPaintColor(Color.parseColor("#FF5F01"))
                            4 ->paintView?.setPaintColor(Color.parseColor("#7252FF"))
                            5 ->paintView?.setPaintColor(Color.parseColor("#F7101A"))
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
        val long1=SimilarPhoto.getFingerPrint(bm);
        val long2=SimilarPhoto.getFingerPrint(captureView(paintView!!));
        LogUtil.e("==diffNum1===>${SimilarPhoto.hamDist(long1, long2)}")
        when (SimilarPhoto.hamDist(long1, long2)){
            in 20..25 -> {
                Toast.makeText(this, "3星", Toast.LENGTH_SHORT).show()
            }
            in 26..30 -> {
                Toast.makeText(this, "2星", Toast.LENGTH_SHORT).show()
            }
            else->{
                Toast.makeText(this, "1星", Toast.LENGTH_SHORT).show()
            }
        }
//        LogUtil.e("==diffNum1===>${SimilarPhoto.hamDist(long1, long2)}")
//        LogUtil.e("==diffNum2===>${SimilarPicture.diff(bm, captureView(paintView!!))}")
    }
}