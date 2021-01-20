package com.example.dawnmvvm.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.dawnmvvm.R;
import com.example.dawnmvvm.bean.Doodle;
import com.example.dawnmvvm.view.FlashStartView;


public class DoodleActivity extends AppCompatActivity{

	private Doodle mDoodle;

	private AlertDialog mColorDialog;
	private AlertDialog mPaintDialog;
	private AlertDialog mShapeDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doodle);
		FlashStartView view=findViewById(R.id.flashView0);
		FlashStartView view1=findViewById(R.id.flashView1);
		FlashStartView view2=findViewById(R.id.flashView2);
		FlashStartView view3=findViewById(R.id.flashView3);
		FlashStartView view4=findViewById(R.id.flashView4);
		ConstraintLayout layout=findViewById(R.id.start_Ll);
		AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
		alphaAnimation.setDuration(1000);
		layout.startAnimation(alphaAnimation);
		view.startFlash(500);
		view1.startFlash(400);
		view2.startFlash(200);
		view3.startFlash(300);
		view4.startFlash(100);
//		alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
//			@Override
//			public void onAnimationStart(Animation animation) {
//
//			}
//
//			@Override
//			public void onAnimationEnd(Animation animation) {
//
//			}
//
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//
//			}
//		});




	}



}