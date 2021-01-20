package com.example.dawnmvvm.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;

import com.example.dawnmvvm.ui.ShowBitmapActivity;

public class BitmapUtil {
    public static String similarity (Context context,Bitmap b) {
        NotificationSetUtil.OpenNotificationSetting(context, new NotificationSetUtil.OnNextLitener() {
            @Override
            public void onNext() {
                LogUtil.e("==通知打开了===>");
            }
        });


//        int value= Color.parseColor("#FF00FF");
//        int red = (value & 0xff0000) >> 16;
//            int green = (value & 0x00ff00) >> 8;
//            int blue = (value & 0x0000ff);
//            LogUtil.e("==BitmapUtil===color===>" + (red+","+green+","+blue));

        //把图片转换为Bitmap

        Bitmap bm_one = b;
        float scale_width, scale_height;
        scale_width = 8.0f / bm_one.getWidth();
        scale_height = 8.0f / bm_one.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scale_width, scale_height);
//        Bitmap scaledBitmap = Bitmap.createBitmap(bm_one, 0, 0, bm_one.getWidth(), bm_one.getHeight(), matrix, false);
        Bitmap scaledBitmap = b;

//        ShowBitmapActivity.start(context,scaledBitmap);





        int num=0;
        //保存图片所有像素个数的数组，图片宽×高
        int[] pixels_one = new int[scaledBitmap.getWidth()*scaledBitmap.getHeight()];
        //获取每个像素的RGB值
        scaledBitmap.getPixels(pixels_one,0,scaledBitmap.getWidth(),0,0,scaledBitmap.getWidth(),scaledBitmap.getHeight());
        for (int i=0;i<pixels_one.length;i++) {
            int value=pixels_one[i];
            num+=value;
//            int red = (value & 0xff0000) >> 16;
//            int green = (value & 0x00ff00) >> 8;
//            int blue = (value & 0x0000ff);
//            LogUtil.e("==BitmapUtil===color===>" + (red+","+green+","+blue)+"========i====>"+i);
        }
        int value=num/pixels_one.length;
        int red = (value & 0xff0000) >> 16;
            int green = (value & 0x00ff00) >> 8;
            int blue = (value & 0x0000ff);
            LogUtil.e("==BitmapUtil===color===>" + (red+","+green+","+blue));
        int grey = (int) ((float) red * 0.3 + (float) green * 0.59 + (float) blue * 0.11);
        LogUtil.e("==BitmapUtil===grey===>" + grey);
        return "相似度为：";

    }

}
