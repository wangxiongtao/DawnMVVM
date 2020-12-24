package com.example.dawnmvvm.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dawnmvvm.R;
import com.example.dawnmvvm.adapter.MatchGameAdapter;
import com.example.dawnmvvm.bindingadapter.recyclerview.DividerLine;
import com.example.dawnmvvm.util.LogUtil;

public class MatchGameLayout extends RelativeLayout {
    private RecyclerView recyclerView;

    public MatchGameLayout(Context context) {
        super(context);

    }

    public MatchGameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MatchGameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    public void init(int c){
        addQuestionRv(c);

    }
    private void addQuestionRv(int c){
        float dp1=getResources().getDimension(R.dimen.dp1);
        int leftMargin = (int) (10 * dp1);
        int itemMargin = (int) (20 * dp1);
        int w=getResources().getDisplayMetrics().widthPixels;
        int itemW= (int) ((w-(c-1)* itemMargin)/c)+10;
        recyclerView=new RecyclerView(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),c));
        recyclerView.addItemDecoration(new DividerLine(getContext(), itemMargin, DividerLine.LineDrawMode.VERTICAL));
        recyclerView.setAdapter(new MatchGameAdapter(c,itemW));
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin= leftMargin;
        layoutParams.rightMargin= leftMargin;
        layoutParams.topMargin= leftMargin *5;
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        addView(recyclerView,layoutParams);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                View view=recyclerView.getLayoutManager().findViewByPosition(0);
                if(view==null){
                    return;
                }
                LogUtil.e("=====view=getWidth=>"+view.getWidth());
                LogUtil.e("=====view=getHeight=>"+view.getHeight());
                addDragView(view.getWidth());
            }
        });

    }
    private void addDragView(int w){
//        int width = (getResources().getDisplayMetrics().widthPixels - Dimension.dp2px(mContext , 100))/ showItemList.size();
        DragView dragView=new DragView(getContext());
        dragView.setBackgroundColor(Color.parseColor("#000000"));
         RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(w, w);
         layoutParams.topMargin= w;
         dragView.setQuestionRv(recyclerView,3);
         addView(dragView,layoutParams);
    }

}
