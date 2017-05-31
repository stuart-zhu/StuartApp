package com.stuart.stuartapp.kotlin

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import com.stuart.stuartapp.R

/**
 * Created by lenovo on 2017/5/31.
 */

class PullActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull)
        val ct = findViewById(R.id.ct) as CollapsingToolbarLayout
        ct.title = "66666666666"
        ct.setExpandedTitleColor(Color.YELLOW)
ct.setCollapsedTitleTextColor(Color.WHITE)
    }
}