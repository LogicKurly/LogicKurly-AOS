package com.kurly.logickurly.presentation.myRefrigerator.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kurly.logickurly.R

class ItemGridAdapter(var context: Context, var productList: ArrayList<String>, var productImage : ArrayList<String>,var selectedList : ArrayList<Int>): RecyclerView.Adapter<ItemGridAdapter.ViewHolder>(){

    class GridAdapter(val layout: View): RecyclerView.ViewHolder(layout)

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }
    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_grid_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemContainer.setOnClickListener{
            itemClickListener.onClick(it,position)
        }

        when (productList[position]) {
            "감자" -> {
                Glide.with(context)
                    .load(R.drawable.potato)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .fitCenter()
                    .circleCrop()
                    .into(holder.ivItem)
            }
            "고추" -> {
                Glide.with(context)
                    .load(R.drawable.pepper)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .fitCenter()
                    .circleCrop()
                    .into(holder.ivItem)
            }
            "굴비" -> {
                Glide.with(context)
                    .load(R.drawable.gulbi)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .fitCenter()
                    .circleCrop()
                    .into(holder.ivItem)
            }
            "단호박" -> {
                Glide.with(context)
                    .load(R.drawable.sweet_pumkin)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .fitCenter()
                    .circleCrop()
                    .into(holder.ivItem)
            }
            "당근" -> {
                Glide.with(context)
                    .load(R.drawable.carrot)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .fitCenter()
                    .circleCrop()
                    .into(holder.ivItem)
            }
            "두부" -> {
                Glide.with(context)
                    .load(R.drawable.dubu)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .fitCenter()
                    .circleCrop()
                    .into(holder.ivItem)
            }
            "배추" -> {
                Glide.with(context)
                    .load(R.drawable.cabbage)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .fitCenter()
                    .circleCrop()
                    .into(holder.ivItem)
            }
            "버섯" -> {
                Glide.with(context)
                    .load(R.drawable.mushroom)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .fitCenter()
                    .circleCrop()
                    .into(holder.ivItem)
            }
            "상추" -> {
                Glide.with(context)
                    .load(R.drawable.lettuce)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .fitCenter()
                    .circleCrop()
                    .into(holder.ivItem)
            }
            "시금치" -> {
                Glide.with(context)
                    .load(R.drawable.spinach)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .fitCenter()
                    .circleCrop()
                    .into(holder.ivItem)
            }
            "차돌박이" -> {
                Glide.with(context)
                    .load(R.drawable.chdol)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .fitCenter()
                    .circleCrop()
                    .into(holder.ivItem)
            }

            else -> {
                Glide.with(context)
                    .load(R.drawable.default_img)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .fitCenter()
                    .circleCrop()
                    .into(holder.ivItem)
            }
        }

        Log.i("!!!",selectedList.toString())
        Log.i("!!!",position.toString())

        if(selectedList[position]==1){
            holder.ivItem.setColorFilter(Color.parseColor("#809b51e0"))
            holder.ivCheck.visibility = View.VISIBLE
        }
        else{
            holder.ivItem.setColorFilter(Color.parseColor("#00000000"))
            holder.ivCheck.visibility = View.GONE
        }

        holder.tvItem.text = productList[position]
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        private var view : View = v
        var ivItem = v.findViewById<ImageView>(R.id.ivItem)
        var tvItem = v.findViewById<TextView>(R.id.tvItem)
        var itemContainer = v.findViewById<ConstraintLayout>(R.id.itemContainer)
        var ivCheck = v.findViewById<ImageView>(R.id.ivCheck)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}