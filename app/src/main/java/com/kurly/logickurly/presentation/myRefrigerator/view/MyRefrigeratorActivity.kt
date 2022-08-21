package com.kurly.logickurly.presentation.myRefrigerator.view

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kurly.logickurly.R
import com.kurly.logickurly.data.model.Preferences
import com.kurly.logickurly.databinding.ActivityMyRefrigeratorBinding
import com.kurly.logickurly.presentation.addRefrigerator.view.AddRefrigeratorActivity
import com.kurly.logickurly.presentation.base.BaseActivity
import com.kurly.logickurly.presentation.myRefrigerator.view.adapter.ItemGridAdapter
import com.kurly.logickurly.presentation.myRefrigerator.view.dialog.DeleteIngredientDialog
import com.kurly.logickurly.presentation.myRefrigerator.view.dialog.NoticeDialog
import com.kurly.logickurly.presentation.myRefrigerator.viewModel.MyRefrigeratorViewModel
import com.kurly.logickurly.presentation.recommendRecipe.view.RecommendRecipeActivty
import kotlin.collections.ArrayList

class MyRefrigeratorActivity :
    BaseActivity<ActivityMyRefrigeratorBinding>(R.layout.activity_my_refrigerator) {

    var selected = false


    private val mainVM: MyRefrigeratorViewModel by lazy {
        ViewModelProvider(this)[MyRefrigeratorViewModel::class.java]
    }

    /** dataBinding 객체에 추가적인 작업을 할 때 사용한다 */
    override fun extraSetupBinding() {
        binding.vm = mainVM
    }

    override fun setup() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun setupViews() {

        binding.header.tvTitle?.text = "My 냉장고"
        binding.header.ivBack?.setOnClickListener {
            onBackPressed()
        }


        var myRefrigerator = Preferences.getInstance(this).getStringItem("MyRefrigerator","")
        var itemList = myRefrigerator?.split(",")
        var tempList = mutableListOf<String>()
        for(i in 0 until itemList!!.size){
            if(itemList[i]!="" && itemList[i]!=","){
                tempList.add(itemList[i].replace(",",""))
            }
        }
        itemList = tempList
        var itemImageList = itemList
        var selectedItemList = mutableListOf<Int>()
        for(i in 0 until itemList!!.size){
            selectedItemList.add(0)
        }
        var listManager = GridLayoutManager(this, 4)
        var listAdapter = ItemGridAdapter(this, itemList as ArrayList<String>, itemImageList as ArrayList<String>,
            selectedItemList as ArrayList<Int>
        )
        binding.tvCount.text = "전체 ${itemList.size}개"
        var recyclerList = binding.ingredientRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = listManager
            adapter = listAdapter
        }

        listAdapter.setItemClickListener(object : ItemGridAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                if (selectedItemList[position] == 0) {
                    selectedItemList[position] = 1
                } else {
                    selectedItemList[position] = 0
                }
                listAdapter.notifyDataSetChanged()

                if (selectedItemList.contains(1)) {
                    selected = true
                    binding.floating.setBackgroundResource(R.drawable.enabled_select_r24)
                    binding.tvDeleteAll.setTextColor(Color.parseColor("#560c7b"))
                } else {
                    selected = false
                    binding.floating.setBackgroundResource(R.drawable.disabled_select_r24)
                    binding.tvDeleteAll.setTextColor(Color.parseColor("#bdbdbd"))
                }
            }

        })

        binding.tvDeleteAll.setOnClickListener {
            if (selected){
                var dialog = DeleteIngredientDialog {
                    Log.i("callback", "확인 !!")
                    var saveItemList = arrayListOf<String>()
                    var saveItemImageList = arrayListOf<String>()
                    var saveSelectedList = arrayListOf<Int>()
                    for (i in 0 until selectedItemList.size) {
                        if (selectedItemList[i] == 0) {
                            saveItemList.add((itemList as ArrayList<String>)[i])
                            saveItemImageList.add(itemImageList[i])
                            saveSelectedList.add(0)
                        }
                        else{
                            Preferences.getInstance(this).putStringItem("MyRefrigerator",Preferences.getInstance(this).getStringItem("MyRefrigerator","").toString().replace(
                                (itemList as ArrayList<String>)[i],""))
                        }
                    }

                    (itemList as ArrayList<String>).clear()
                    itemImageList.clear()
                    selectedItemList.clear()

                    itemList = saveItemList
                    itemImageList.addAll(saveItemImageList)
                    selectedItemList.addAll(saveSelectedList)

                    binding.tvCount.text = "전체 ${saveItemList.size}개"
                    binding.floating.setBackgroundResource(R.drawable.disabled_select_r24)
                    binding.tvDeleteAll.setTextColor(Color.parseColor("#bdbdbd"))
                    selected = false
                    listAdapter.notifyDataSetChanged()
                }
                dialog.isCancelable = true
                dialog.show(this.supportFragmentManager, "")
            }
        }

        binding.floatingLayout.setOnClickListener {
            if (selectedItemList.contains(1)) {
                var ingredient = mutableListOf<String>()
                for(i in 0 until selectedItemList.size){
                    if(selectedItemList[i]==1){
                        ingredient.add((itemList as ArrayList<String>)[i])
                    }
                }
                val intent = Intent(this, RecommendRecipeActivty::class.java)
                Preferences.getInstance(this).putStringItem("ingredient",ingredient.toString())
                startActivity(intent)
            } else {
                NoticeDialog().show(this.supportFragmentManager, "")
            }
        }

        binding.header.ivAdd?.setOnClickListener{
            val intent = Intent(this, AddRefrigeratorActivity::class.java)
            startActivity(intent)
        }

        //initial setup
        if (selectedItemList.contains(1)) {
            selected = true
            binding.floating.setBackgroundResource(R.drawable.enabled_select_r24)
            binding.tvDeleteAll.setTextColor(Color.parseColor("#560c7b"))
        } else {
            selected = false
            binding.floating.setBackgroundResource(R.drawable.disabled_select_r24)
            binding.tvDeleteAll.setTextColor(Color.parseColor("#bdbdbd"))
        }
        binding.tvCount.text = "전체 ${(itemList as ArrayList<String>).size}개"


    }


    override fun onSubscribe() {

    }

    override fun release() {

    }

    override fun onBackPressed() {
        finish()
    }
}