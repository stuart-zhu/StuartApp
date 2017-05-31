package com.stuart.stuartapp.kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.stuart.stuartapp.R

/**
 * Created by stuart on 2017/5/31.
 */

class MainKotlinAdapter(private val context: Context, private val list: Array<String>?) : Adapter<MainKotlinAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val ri = LayoutInflater.from(context)
        val x = ri.inflate(R.layout.main_kotlin_item_lyaout, parent, false)
        return MyViewHolder(x)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvName.text = list!![position]
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var tvName: TextView

        init {

            tvName = v.findViewById(R.id.tvName) as TextView
        }
    }
}
