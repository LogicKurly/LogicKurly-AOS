package com.kurly.logickurly.presentation.addRefrigerator.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kurly.logickurly.R
import com.kurly.logickurly.data.model.Preferences

class searchListAdapter(
    var context: Context,
    var ingredientList: ArrayList<String>,
    var selectedList: ArrayList<Int>
) : RecyclerView.Adapter<searchListAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.search_list_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder,
        @SuppressLint("RecyclerView") parentPosition: Int
    ) {


        holder.tvIngredient.text = ingredientList[parentPosition]

        if (selectedList[parentPosition] == 1) {
            holder.tvNoCart.visibility = View.GONE
            holder.tvInCart.visibility = View.VISIBLE
        } else {
            holder.tvNoCart.visibility = View.VISIBLE
            holder.tvInCart.visibility = View.GONE
        }

        holder.tvNoCart.setOnClickListener {
            itemClickListener.onClick(it, parentPosition)
        }


    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        var tvIngredient = v.findViewById<TextView>(R.id.tvIngredient)
        var tvNoCart = v.findViewById<TextView>(R.id.tvNoCart)
        var tvInCart = v.findViewById<TextView>(R.id.tvInCart)

    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

}