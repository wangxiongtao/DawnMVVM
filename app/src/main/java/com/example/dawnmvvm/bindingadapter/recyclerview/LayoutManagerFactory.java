package com.example.dawnmvvm.bindingadapter.recyclerview;


import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * A collection of factories to create RecyclerView LayoutManagers so that you can easily set them
 * in your layout.
 */
public class LayoutManagerFactory {

    public static LinearLayoutManager linear(Context context){
        return new LinearLayoutManager(context);
    }
    public static LinearLayoutManager linear(Context context,final int orientation, final boolean reverseLayout){
        return new LinearLayoutManager(context,orientation,reverseLayout);
    }
    public static LinearLayoutManager grid(Context context,final int spanCount){
        return new GridLayoutManager(context,spanCount);
    }



}

