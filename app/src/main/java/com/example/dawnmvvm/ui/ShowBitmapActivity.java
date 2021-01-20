package com.example.dawnmvvm.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.dawnmvvm.R;

public class ShowBitmapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bitmap);
        Bitmap bitmap=getIntent().getParcelableExtra("bitmap");
        ImageView imageView=findViewById(R.id.bitmap_view);
        imageView.setImageBitmap(bitmap);
    }
    public static void start(Context context,Bitmap bitmap) {
        Intent starter = new Intent(context, ShowBitmapActivity.class);
        starter.putExtra("bitmap",bitmap);
        context.startActivity(starter);
    }
}