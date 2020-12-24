package com.example.dawnmvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dawnmvvm.R;
import com.example.dawnmvvm.util.LogUtil;

public class MatchGameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE_IMG=1;
    private final int TYPE_BLANK=2;
    private int c;
    private int itemW;

    public MatchGameAdapter(int c,int itemW) {
        this.c = c;
        this.itemW=itemW;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MBlankViewHolder(layoutInflater.inflate(R.layout.item_match_game_layout, parent, false),itemW);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


    }


    @Override
    public int getItemCount() {
        return c;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==3){
            return TYPE_BLANK;
        }
        return TYPE_IMG;
    }

    static class MBlankViewHolder extends RecyclerView.ViewHolder{

        public MBlankViewHolder(@NonNull View itemView,int w) {
            super(itemView);
//            GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) itemView.getLayoutParams();
//            layoutParams.width=w;
//            layoutParams.height=w;
//            LogUtil.e("==leftMargin==="+layoutParams.leftMargin);
//            layoutParams.leftMargin=300;
////        layoutParams.leftMargin= (int) (10*2.625);
////        ImageView imageView=holder.itemView.findViewById(R.id.img);
//            itemView.setLayoutParams(layoutParams);
        }
    }
    static class MImageViewHolder extends RecyclerView.ViewHolder{


        public MImageViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
