package com.kurly.logickurly.presentation.recommendRecipe.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kurly.logickurly.R
import com.kurly.logickurly.presentation.addRefrigerator.adapter.PopularAdapter

class RecommendRecipeAdapter (var context: Context, var foodList: ArrayList<String>, var foodImageList: ArrayList<String>, var requireIngredientList : ArrayList<ArrayList<String>>): RecyclerView.Adapter<RecommendRecipeAdapter.ViewHolder>(){

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }
    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recommend_recipe_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") parentPosition: Int) {

        holder.tvFoodTitle.text = foodList[parentPosition]

        Glide.with(context)
            .load(R.drawable.carrot)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .fitCenter()
            .into(holder.ivFood)

        var listManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        var selectedList = mutableListOf<ArrayList<Int>>()
        for(i in 0 until requireIngredientList.size){
            var tempSelectedList= mutableListOf<Int>()
            for(j in 0 until requireIngredientList[i].size){
                tempSelectedList.add(0)
            }
            selectedList.add(tempSelectedList as ArrayList<Int>)
        }
        var listAdapter = PopularAdapter(context, requireIngredientList[parentPosition], selectedList[parentPosition])
        var recyclerList = holder.requireIngredientRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = listManager
            adapter = listAdapter
        }
        listAdapter.setItemClickListener(object : PopularAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
            }
        })

    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        private var view : View = v
        var ivFood = v.findViewById<ImageView>(R.id.ivFood)
        var tvFoodTitle = v.findViewById<TextView>(R.id.tvFoodTitle)
        var requireIngredientRecyclerView = v.findViewById<RecyclerView>(R.id.requireIngredientRecyclerView)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

}