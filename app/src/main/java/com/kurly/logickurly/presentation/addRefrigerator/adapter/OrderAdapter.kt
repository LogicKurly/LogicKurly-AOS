package com.kurly.logickurly.presentation.addRefrigerator.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
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

class OrderAdapter (var context: Context, var dateList: ArrayList<String>, var orderList : ArrayList<ArrayList<String>>, var selectedList : ArrayList<ArrayList<Int>>): RecyclerView.Adapter<OrderAdapter.ViewHolder>(){

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }
    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") parentPosition: Int) {

        holder.tvDate.text = dateList[parentPosition]

        var listManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        Log.i("orderList_$parentPosition",orderList[parentPosition].toString())
        Log.i("selectedList_$parentPosition",selectedList[parentPosition].toString())
        var listAdapter = PopularAdapter(context, orderList[parentPosition], selectedList[parentPosition])
        var recyclerList = holder.orderRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = listManager
            adapter = listAdapter
        }
        listAdapter.setItemClickListener(object : PopularAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                if (selectedList[parentPosition][position] == 0) {
                    selectedList[parentPosition][position] = 1
                    Toast.makeText(context,"선택하신 식재료가 담겼습니다", Toast.LENGTH_SHORT).show()
                    Preferences.getInstance(context).putStringItem("MyRefrigerator",Preferences.getInstance(context).getStringItem("MyRefrigerator","")+",${orderList[parentPosition][position]}")
                } else {
                    selectedList[parentPosition][position] = 0
                    Toast.makeText(context,"선택하신 식재료를 꺼냈습니다", Toast.LENGTH_SHORT).show()
                    Preferences.getInstance(context).putStringItem("MyRefrigerator",Preferences.getInstance(context).getStringItem("MyRefrigerator","").toString().replace(orderList[parentPosition][position],""))
                }
                listAdapter.notifyDataSetChanged()
            }
        })

    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        private var view : View = v
        var tvDate = v.findViewById<TextView>(R.id.tvDate)
        var orderRecyclerView = v.findViewById<RecyclerView>(R.id.orderRecyclerView)
    }

    override fun getItemCount(): Int {
        return dateList.size
    }

}