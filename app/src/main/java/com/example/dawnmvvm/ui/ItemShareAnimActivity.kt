package com.example.dawnmvvm.ui

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.dawnmvvm.R

class ItemShareAnimActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_share_anim)
        val title=intent.getStringExtra("title");
        var text=findViewById<TextView>(R.id.anim_text);
        text.text=title;
    }
    companion object {

        fun start(activity: Activity, title: String, shareView: View) {
            activity.startActivity(Intent(activity, ItemShareAnimActivity::class.java).apply {
                putExtra("title", title)
            }, ActivityOptions.makeSceneTransitionAnimation(
                    activity,
                    shareView,
                    "animTitle").toBundle());
        }
    }
}