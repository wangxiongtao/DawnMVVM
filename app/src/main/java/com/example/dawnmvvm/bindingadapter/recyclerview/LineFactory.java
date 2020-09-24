package com.example.dawnmvvm.bindingadapter.recyclerview;


import android.content.Context;

/**
 * Created by goldze on 2017/6/16.
 */
public class LineFactory {
    public static DividerLine both(Context context) {
        return  new DividerLine(context, DividerLine.LineDrawMode.BOTH);
    }
    public static DividerLine horizontal(Context context) {
        return new DividerLine(context, DividerLine.LineDrawMode.HORIZONTAL);
    }
    public static DividerLine vertical(Context context) {
        return new DividerLine(context, DividerLine.LineDrawMode.VERTICAL);
    }
}
