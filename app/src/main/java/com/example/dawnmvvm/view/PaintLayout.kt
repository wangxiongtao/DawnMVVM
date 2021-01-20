package com.example.dawnmvvm.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.dawnmvvm.R
import com.example.dawnmvvm.util.*


class PaintLayout : RelativeLayout {
    private var paintAreaLl: LinearLayout? = null;
    private var dotRv: RecyclerView? = null;
    private var paintView: PaintView? = null;
    private var colorList = mutableListOf<String>()
    private var drawImg: ImageView? = null;
    private var penBoldView: View? = null;
    private var penSmallView: View? = null;
    private var eraserView: View? = null;
    private var doneView: View? = null;
    private var bgView: ImageView? = null;
    private var config = -1;//0:粗体，1：正常 2：橡皮
    private var paintColor: String = "#000000"
    private var strokeWidth = 2f.dp
    private var showBitmap: Bitmap? = null;
    private var boldStrokeWidth = 15f.dp
    private var eraserStrokeWidth = 20f.dp

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
        LayoutInflater.from(context).inflate(R.layout.action_paint_layout, this)
        dotRv = findViewById(R.id.dot_rv);
        drawImg = findViewById(R.id.draw_bmp);
        penBoldView = findViewById(R.id.bold_pen_iv);
        penSmallView = findViewById(R.id.small_pen_iv);
        eraserView = findViewById(R.id.eraser_iv);
        paintView = findViewById(R.id.paint_view);
        doneView = findViewById(R.id.done_view);
        bgView = findViewById(R.id.paint_bg);
        paintView?.setPaintConfig(Color.parseColor(paintColor))
        initListener()
        Glide.with(this)
                .load("http://beta.media.treector.com/a5c2f829c9a8aea412ca7bf94741bd34.png")
                .transform(BlurTransformation(context))
                .into(bgView!!)
    }

    private fun initRv(showUrl: String) {
        dotRv?.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = MyAdapter(colorList).apply {
                selectColor = {
                    LogUtil.e("BitmapUtil===color===>$it")
                    paintColor = it;
                    updatePaintConfig()
                    eraserView?.check(false)
                }
            };
        }
        Glide.with(this)
                .asBitmap()
                .load(showUrl)
                .into(object : BitmapImageViewTarget(drawImg) {
                    override fun setResource(resource: Bitmap?) {
                        super.setResource(resource)
                        showBitmap = resource;
                        resource?.apply {
                            getPaletteColor(this) {
                                LogUtil.e("getPaletteColor0===>$it")
                                colorList.clear()
                                colorList.add("#000000")
                                colorList.addAll(it);
                                dotRv?.adapter?.notifyDataSetChanged()
                            }
                        }
                        LogUtil.e("==resource===$resource")
                    }
                })
    }

    private fun initListener() {
        penBoldView?.setOnClickListener {
            if (config == 0) {
                return@setOnClickListener
            }
            config = 0
            it.check(true)
            penSmallView?.check(false)
            eraserView?.check(false)
            strokeWidth = boldStrokeWidth
            updatePaintConfig()

        }
        penSmallView?.setOnClickListener {
            if (config == 1) {
                return@setOnClickListener
            }
            config = 1
            it.check(true)
            penBoldView?.check(false)
            eraserView?.check(false)
            strokeWidth = 2f.dp
            updatePaintConfig()

        }
        eraserView?.setOnClickListener {
            if (config == 2) {
                return@setOnClickListener
            }
            config = 2
            it.check(true)
            updatePaintConfig("#FFFFFF", eraserStrokeWidth)

        }
        doneView?.setOnClickListener {
//            BitmapUtil.similarity(showBitmap!!)
//            BitmapUtil.similarity(context,paintView!!.drawBitmap)
            BitmapUtil.similarity(context,showBitmap)
            BitmapUtil.similarity(context,paintView!!.drawBitmap)

//            drawImg!!.setImageBitmap( paintView!!.drawBitmap)
//            showBitmap?.apply {
//                SimilarityHelper.instance().similarity(captureView(drawImg!!), captureView(paintView!!), object : SimilarityCallBack {
//                    override fun onSimilarityStart() {
//                        LogUtil.e("diffNum=====>start")
//                    }
//
//                    override fun onSimilaritySuccess(similarity: Int, different: Int) {
//                        LogUtil.e("diffNum===similarity==>匹配度为 ： $similarity")
//                        LogUtil.e("diffNum===different==>匹配度为 ： $different")
//                        LogUtil.e("diffNum===11111==>匹配度为 ： " + similarity.toFloat() / (similarity + different) * 100 + " %")
//                    }
//
//                    override fun onSimilarityError(reason: String) {
//                        LogUtil.e("diffNum=====>error ： $reason")
//
//                    }
//                })
//
//
//                SimilarPicture.diff(paintView!!.drawBitmap, captureView(paintView!!))

//                getPaletteColor(captureView(paintView!!)!!){
//                    LogUtil.e("getPaletteColor1===>$it")
//                }
//            }
//                val long1 = SimilarPhoto.getFingerPrint(captureView(drawImg!!));
//                val long2 = SimilarPhoto.getFingerPrint(captureView(paintView!!));
//                LogUtil.e("diffNum==hamDist====="+SimilarPhoto.hamDist(long1, long2))
//                when (SimilarPhoto.hamDist(long1, long2)) {
//                    in 0..19 -> {
//                        Toast.makeText(context, "3星", Toast.LENGTH_SHORT).show()
//                    }
//                    in 20..26 -> {
//                        Toast.makeText(context, "2星", Toast.LENGTH_SHORT).show()
//                    }
//                    else -> {
//                        Toast.makeText(context, "1星", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
        }
    }


    private fun updatePaintConfig(color: String = paintColor, sWidth: Float = strokeWidth) {
        paintView?.setPaintConfig(color, sWidth)
    }

    private fun getPaletteColor(bitmap: Bitmap, call: (colors: List<String>) -> Unit) {
        val builder = Palette.from(bitmap)
        builder.maximumColorCount(8)
        builder.generate { palette ->
            palette?.let {
                val swatches = palette.swatches
                call(swatches.map {
                    "#${Integer.toHexString(it.rgb)}"
                })
            }

        }
    }

    public fun setShowUrl(showUrl: String) {
        initRv(showUrl)
    }


    class MyAdapter(colorList: List<String>) : RecyclerView.Adapter<MyAdapter.MyHolder>() {
        private var colorList = listOf<String>();
        private var selectPos = 0;
        var selectColor: ((color: String) -> Unit)? = null;

        init {
            this.colorList = colorList;
        }


        override fun getItemCount(): Int {
            return colorList.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
            return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_color_dot_layout, parent, false))

        }

        override fun onBindViewHolder(holder: MyHolder, position: Int) {
            val dotView = holder.itemView as ColorDotView;
            dotView.dotColor = colorList[position]
            dotView.isSelected = position == selectPos;
            holder.itemView.setOnClickListener {
                this.selectPos = position;
                notifyDataSetChanged()
                selectColor?.invoke(colorList[position]);
            }

        }

        class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            init {

            }

        }
    }


    private fun View.check(check: Boolean) {
        pivotX = 0f
        pivotY = height * 0.8f;
        scaleY = if (check) {
            1.2f
        } else {
            1f
        }
    }

}