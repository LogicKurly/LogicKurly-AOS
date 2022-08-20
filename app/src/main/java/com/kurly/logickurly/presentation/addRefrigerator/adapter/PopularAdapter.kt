package com.kurly.logickurly.presentation.addRefrigerator.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kurly.logickurly.R

class PopularAdapter(var context: Context, var popularList: ArrayList<String>, var selectedList : ArrayList<Int>): RecyclerView.Adapter<PopularAdapter.ViewHolder>(){


    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }
    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.popular_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it,position)
        }

        Log.i("selectedList",selectedList.toString())
        Log.i("selectedList",position.toString())
        if(selectedList[position]==1){
            holder.tvItem.setBackgroundResource(R.drawable.selected_purple_r20)
            holder.tvItem.setTextColor(Color.parseColor("#844fbd"))
        }
        else{
            holder.tvItem.setBackgroundResource(R.drawable.stroke_lightgray_r20)
            holder.tvItem.setTextColor(Color.parseColor("#828282"))
        }

        holder.tvItem.text = popularList[position]
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        private var view : View = v
        var tvItem = v.findViewById<TextView>(R.id.tvItem)
    }

    override fun getItemCount(): Int {
        return popularList.size
    }

}