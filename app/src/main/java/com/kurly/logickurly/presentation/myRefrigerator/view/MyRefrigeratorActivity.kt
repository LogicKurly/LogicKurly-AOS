package com.kurly.logickurly.presentation.myRefrigerator.view

import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kurly.logickurly.R
import com.kurly.logickurly.databinding.ActivityMyRefrigeratorBinding
import com.kurly.logickurly.presentation.myRefrigerator.viewModel.MyRefrigeratorViewModel
import com.kurly.logickurly.presentation.base.BaseActivity
import com.kurly.logickurly.presentation.myRefrigerator.view.adapter.ItemGridAdapter
import com.kurly.logickurly.presentation.myRefrigerator.view.dialog.DeleteIngredientDialog
import com.kurly.logickurly.presentation.myRefrigerator.view.dialog.NoticeDialog

class MyRefrigeratorActivity :
    BaseActivity<ActivityMyRefrigeratorBinding>(R.layout.activity_my_refrigerator) {

    var selected = false


    private val mainVM: MyRefrigeratorViewModel by lazy {
        ViewModelProvider(this).get(MyRefrigeratorViewModel::class.java)
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

        var itemList = arrayListOf(
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근"
        )
        var itemImageList = arrayListOf(
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근",
            "당근"
        )
        var selectedItemList = arrayListOf(
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0
        )
        var listManager = GridLayoutManager(this, 4)
        var listAdapter = ItemGridAdapter(this, itemList, itemImageList, selectedItemList)
        binding.tvCount.text =
            binding.tvCount.text.toString().replace("#NUMBER#", itemList.size.toString())

        listAdapter.setItemClickListener(object : ItemGridAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                if (selectedItemList[position] == 0) {
                    selectedItemList[position] = 1
                } else {
                    selectedItemList[position] = 0
                }
                listAdapter.notifyItemChanged(position)

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
                        if (selectedItemList[i] != 1) {
                            saveItemList.add(itemList[i])
                            saveItemImageList.add(itemImageList[i])
                            saveSelectedList.add(0)
                        }
                    }

                    itemList.clear()
                    itemImageList.clear()
                    selectedItemList.clear()

                    itemList.addAll(saveItemList)
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

        var recyclerList = binding.ingredientRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = listManager
            adapter = listAdapter
        }


        binding.floatingLayout.setOnClickListener {
            if (selectedItemList.contains(1)) {
                //화면 이동
            } else {
                NoticeDialog().show(this.supportFragmentManager, "")
            }
        }


    }


    override fun onSubscribe() {

    }

    override fun release() {

    }

    override fun onBackPressed() {
        finish()
    }
}