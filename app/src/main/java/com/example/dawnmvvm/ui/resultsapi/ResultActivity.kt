package com.example.dawnmvvm.ui.resultsapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.dawnmvvm.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        findViewById<TextView>(R.id.result_text).text=intent.getStringExtra("name");

    }

    override fun onBackPressed() {
        setResult(RESULT_OK, Intent().apply {
            putExtra("data","ddddd")
        })
        super.onBackPressed()
    }
}