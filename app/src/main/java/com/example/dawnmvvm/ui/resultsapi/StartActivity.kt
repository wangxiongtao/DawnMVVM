package com.example.dawnmvvm.ui.resultsapi

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dawnmvvm.R
import java.security.Permission
import java.util.jar.Manifest

class StartActivity : AppCompatActivity() {
    private val myActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

        Toast.makeText(this, "回传数据：${result.data?.getStringExtra("data")}", Toast.LENGTH_SHORT).show()
    }
    private val myActivityLauncher2 = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->

        Toast.makeText(this, "$result", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

    }

    fun skipSecond(view: View) {

        myActivityLauncher.launch(Intent(this, ResultActivity::class.java).apply {
            putExtra("name", "bbbbbbb")
        })
        myActivityLauncher2.launch(android.Manifest.permission.BLUETOOTH)


    }

}

