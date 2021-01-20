package com.example.dawnmvvm.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.dawnmvvm.BR;
import com.example.dawnmvvm.R;
import com.example.dawnmvvm.base.BaseActivity;
import com.example.dawnmvvm.databinding.ActivityGlideBinding;

public class GlideActivity extends BaseActivity<ActivityGlideBinding,GlideVm> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_glide;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        BitmapImageViewTarget target=new BitmapImageViewTarget(new ImageView(this));
        Glide.with(this)
                .asBitmap()
                .load("url")
                .into(new BitmapImageViewTarget(new ImageView(this)){
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                    }
                });

    }

    @Override
    public int initVariableId() {
        return BR.gVm;
    }
}