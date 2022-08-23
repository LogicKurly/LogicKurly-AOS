package com.kurly.logickurly.presentation.recommendRecipe.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kurly.logickurly.R
import com.kurly.logickurly.presentation.addRefrigerator.adapter.PopularAdapter

class IngredientAdapter(var context: Context, var ingredientList: ArrayList<String>, var itemImageList: MutableList<String>, var itemDeliverList: MutableList<MutableList<String>>, var itemProductList: MutableList<MutableList<String>>, var itemValueList: MutableList<MutableList<Int>>): RecyclerView.Adapter<IngredientAdapter.ViewHolder>(){

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }
    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.require_ingredient_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") parentPosition: Int) {

        holder.tvIngredient.text = ingredientList[parentPosition]

        holder.item1.setOnClickListener{
            itemClickListener.onClick(it,parentPosition)
        }
        holder.item2.setOnClickListener{
            itemClickListener.onClick(it,parentPosition)
        }
        holder.item3.setOnClickListener{
            itemClickListener.onClick(it,parentPosition)
        }
        holder.item4.setOnClickListener{
            itemClickListener.onClick(it,parentPosition)
        }
        holder.item5.setOnClickListener{
            itemClickListener.onClick(it,parentPosition)
        }

        if(ingredientList[parentPosition].contains("배추")) {

            Glide.with(context)
                .load(R.drawable.cabbage1) //itemImageList[parentPosition][0]
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.iv_image_item1)

            Glide.with(context)
                .load(R.drawable.cabbage2) //itemImageList[parentPosition][1]
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.iv_image_item2)

            Glide.with(context)
                .load(R.drawable.cabbage3) //itemImageList[parentPosition][2]
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.iv_image_item3)

            Glide.with(context)
                .load(R.drawable.cabbage4) //itemImageList[parentPosition][3]
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.iv_image_item4)

            Glide.with(context)
                .load(R.drawable.cabbage5) //itemImageList[parentPosition][4]
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.iv_image_item5)
        }
        else if(ingredientList[parentPosition].contains("고춧가루")) {

            Glide.with(context)
                .load(R.drawable.spicy1) //itemImageList[parentPosition][0]
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.iv_image_item1)

            Glide.with(context)
                .load(R.drawable.spicy2) //itemImageList[parentPosition][1]
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.iv_image_item2)

            Glide.with(context)
                .load(R.drawable.spicy3) //itemImageList[parentPosition][2]
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.iv_image_item3)

            Glide.with(context)
                .load(R.drawable.spicy4) //itemImageList[parentPosition][3]
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.iv_image_item4)

            Glide.with(context)
                .load(R.drawable.spicy5) //itemImageList[parentPosition][4]
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.iv_image_item5)
        }
        else {

            Glide.with(context)
                .load(R.drawable.default_img) //itemImageList[parentPosition][0]
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.iv_image_item1)

            Glide.with(context)
                .load(R.drawable.default_img) //itemImageList[parentPosition][1]
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.iv_image_item2)

            Glide.with(context)
                .load(R.drawable.default_img) //itemImageList[parentPosition][2]
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.iv_image_item3)

            Glide.with(context)
                .load(R.drawable.default_img) //itemImageList[parentPosition][3]
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.iv_image_item4)

            Glide.with(context)
                .load(R.drawable.default_img) //itemImageList[parentPosition][4]
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.iv_image_item5)
        }

        holder.tv_deliver_item1.text = itemDeliverList[parentPosition][0]
        holder.tv_deliver_item2.text = itemDeliverList[parentPosition][1]
        holder.tv_deliver_item3.text = itemDeliverList[parentPosition][2]
        holder.tv_deliver_item4.text = itemDeliverList[parentPosition][3]
        holder.tv_deliver_item5.text = itemDeliverList[parentPosition][4]

        holder.tv_product_item1.text = itemProductList[parentPosition][0]
        holder.tv_product_item2.text = itemProductList[parentPosition][1]
        holder.tv_product_item3.text = itemProductList[parentPosition][2]
        holder.tv_product_item4.text = itemProductList[parentPosition][3]
        holder.tv_product_item5.text = itemProductList[parentPosition][4]

        holder.tv_value_item1.text = "${itemValueList[parentPosition][0]}원"
        holder.tv_value_item2.text = "${itemValueList[parentPosition][1]}원"
        holder.tv_value_item3.text = "${itemValueList[parentPosition][2]}원"
        holder.tv_value_item4.text = "${itemValueList[parentPosition][3]}원"
        holder.tv_value_item5.text = "${itemValueList[parentPosition][4]}원"

    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        private var view : View = v

        var tvIngredient = v.findViewById<TextView>(R.id.tvIngredient)

        var item1 = v.findViewById<ConstraintLayout>(R.id.item1)
        var iv_image_item1 = v.findViewById<ImageView>(R.id.iv_image_item1)
        var tv_deliver_item1 = v.findViewById<TextView>(R.id.tv_deliver_item1)
        var tv_product_item1 = v.findViewById<TextView>(R.id.tv_product_item1)
        var tv_value_item1 = v.findViewById<TextView>(R.id.tv_value_item1)

        var item2 = v.findViewById<ConstraintLayout>(R.id.item2)
        var iv_image_item2 = v.findViewById<ImageView>(R.id.iv_image_item2)
        var tv_deliver_item2 = v.findViewById<TextView>(R.id.tv_deliver_item2)
        var tv_product_item2 = v.findViewById<TextView>(R.id.tv_product_item2)
        var tv_value_item2 = v.findViewById<TextView>(R.id.tv_value_item2)

        var item3 = v.findViewById<ConstraintLayout>(R.id.item3)
        var iv_image_item3 = v.findViewById<ImageView>(R.id.iv_image_item3)
        var tv_deliver_item3 = v.findViewById<TextView>(R.id.tv_deliver_item3)
        var tv_product_item3 = v.findViewById<TextView>(R.id.tv_product_item3)
        var tv_value_item3 = v.findViewById<TextView>(R.id.tv_value_item3)

        var item4 = v.findViewById<ConstraintLayout>(R.id.item4)
        var iv_image_item4 = v.findViewById<ImageView>(R.id.iv_image_item4)
        var tv_deliver_item4 = v.findViewById<TextView>(R.id.tv_deliver_item4)
        var tv_product_item4 = v.findViewById<TextView>(R.id.tv_product_item4)
        var tv_value_item4 = v.findViewById<TextView>(R.id.tv_value_item4)

        var item5 = v.findViewById<ConstraintLayout>(R.id.item5)
        var iv_image_item5 = v.findViewById<ImageView>(R.id.iv_image_item5)
        var tv_deliver_item5 = v.findViewById<TextView>(R.id.tv_deliver_item5)
        var tv_product_item5 = v.findViewById<TextView>(R.id.tv_product_item5)
        var tv_value_item5 = v.findViewById<TextView>(R.id.tv_value_item5)

    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

}