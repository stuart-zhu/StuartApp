package com.stuart.stuartapp.kotlin

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.stuart.stuartapp.R
import com.stuart.stuartapp.kotlin.adapter.MainKotlinAdapter

@Suppress("NAME_SHADOWING")
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

        val recV = findViewById(R.id.recyclerView) as RecyclerView

        val list = Array(100, { i -> i.toString() })

        val adapter = MainKotlinAdapter(this, list)
        recV.adapter = adapter;
        recV.layoutManager = LinearLayoutManager(this)

    }

}