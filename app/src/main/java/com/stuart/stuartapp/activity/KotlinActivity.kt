package com.stuart.stuartapp.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.stuart.stuartapp.R
import com.stuart.stuartapp.kotlin.adapter.MainKotlinAdapter

class KotlinActivity : AppCompatActivity() {

   // var rv: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        val toolbar: Toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton

       val rv = findViewById(R.id.rv) as RecyclerView
        fab.setOnClickListener { view ->
             Snackbar.make(view, "跳转界面？", Snackbar.LENGTH_LONG)
                     .setAction("是的", View.OnClickListener { startActivity(Intent("com.stuart.pull.kotlin"))}).show()
        }

        val list = Array(100, { i -> i.toString() })
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = MainKotlinAdapter(this, list)


        test()

    }


    fun test() {
        var x: Int = 1


        val xx: Array<Int> = arrayOf(1, 2, 3)
        for (c in xx) {
            xx.set(c - 1, c * c)
        }
        for (cc in xx) {
            println(cc)
        }

        val a = "xxx"
        val aa = when (a) {
            is String -> a
            else -> false
        }
        println("aa = $aa")

        when {
            a.length > 3 -> println("a.length > 3")
            else -> println("a.length is not > 3")
        }

        val aaa = """
                | ONE_6
                | TWO_7
                | THREE—
""".trimMargin()
        println("aaa = $aaa")
    }
}
