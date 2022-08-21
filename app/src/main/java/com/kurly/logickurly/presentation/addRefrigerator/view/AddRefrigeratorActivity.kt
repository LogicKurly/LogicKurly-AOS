package com.kurly.logickurly.presentation.addRefrigerator.view

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kurly.logickurly.R
import com.kurly.logickurly.data.model.Preferences
import com.kurly.logickurly.databinding.ActivityAddRefrigeratorBinding
import com.kurly.logickurly.presentation.addRefrigerator.adapter.OrderAdapter
import com.kurly.logickurly.presentation.addRefrigerator.adapter.PopularAdapter
import com.kurly.logickurly.presentation.addRefrigerator.adapter.searchListAdapter
import com.kurly.logickurly.presentation.addRefrigerator.viewModel.AddRefrigeratorViewModel
import com.kurly.logickurly.presentation.base.BaseActivity
import com.kurly.logickurly.presentation.cameraSearch.view.CameraSearchActivity
import com.kurly.logickurly.presentation.myRefrigerator.view.MyRefrigeratorActivity


class AddRefrigeratorActivity : BaseActivity<ActivityAddRefrigeratorBinding>(R.layout.activity_add_refrigerator) {


    private val mainVM: AddRefrigeratorViewModel by lazy {
        ViewModelProvider(this)[AddRefrigeratorViewModel::class.java]
    }

    /** dataBinding 객체에 추가적인 작업을 할 때 사용한다 */
    override fun extraSetupBinding() {
        binding.vm = mainVM
    }

    override fun setup() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun setupViews() {

        binding.ivDeleteAll.setImageResource(0)
        binding.rootContainer.setOnClickListener{
            softkeyboardHide()
        }

        binding.noSearchContainer.visibility = View.VISIBLE
        binding.SearchContainer.visibility = View.GONE

        binding.header.tvTitle?.text = "냉장고 담기"
        binding.header.ivAdd?.visibility = View.INVISIBLE
        binding.header.ivBack?.setImageResource(R.drawable.cancel)
        binding.header.ivBack?.setOnClickListener{
            onBackPressed()
        }

        /**
         * 재료 전체 리스트
         *
         * */

        val ingredientList = mutableListOf<String>()
        ingredientList.add("당근")
        ingredientList.add("고추")
        ingredientList.add("시금치")
        ingredientList.add("차돌박이")
        ingredientList.add("버섯")
        ingredientList.add("상추")
        ingredientList.add("두부")
        ingredientList.add("배추")
        ingredientList.add("단호박")
        ingredientList.add("감자")
        ingredientList.add("굴비")

        //popular RecyclerView
        var itemList = arrayListOf("당근", "고추","시금치","배추")

        //기존에 냉장고에 담겨 있는 재료는 선택된 상태
        var selectedItemList = mutableListOf<Int>()
        var myRefrigerator = Preferences.getInstance(this).getStringItem("MyRefrigerator","")
        var itemList2 = myRefrigerator?.split(",")
        var tempList = mutableListOf<String>()
        for(i in 0 until itemList2!!.size){
            if(itemList2[i]!="" && itemList2[i]!=","){
                tempList.add(itemList2[i].replace(",",""))
            }
        }
        itemList2 = tempList
        for(i in 0 until itemList.size){
            if(itemList2.contains(itemList[i])){
                selectedItemList.add(1)
            }
            else{
                selectedItemList.add(0)
            }
        }

        //order RecyclerView
        var orderList1 = arrayListOf("버섯", "상추","두부","배추","단호박", "감자","굴비","당근","고추")
        var orderList2 = arrayListOf("버섯", "상추","굴비")
        var orderList3 = arrayListOf("당근", "고추","시금치","차돌박이")
        var orderList4 = arrayListOf("버섯", "상추","두부","배추","단호박",)
        var orderList5 = arrayListOf("버섯", "상추","굴비","단호박", "감자","시금치","차돌박이","고추")
        var orderList6 = arrayListOf("당근", "고추","시금치","차돌박이","고추")
        var orderList7 = arrayListOf("버섯", "상추","두부","배추","단호박", "감자","굴비")
        var orderList8 = arrayListOf("당근", "고추")
        var orderList9 = arrayListOf("당근", "상추","감자","배추","고추")
        var orderList10 = arrayListOf("당근", "고추","시금치","차돌박이")
        var dateList = arrayListOf("22.07.01","22.07.02","22.07.08","22.07.20","22.08.09","22.09.01","22.09.02","22.09.08","22.09.20","22.10.09")
        dateList.reverse()
        var orderList = mutableListOf(orderList1,orderList2,orderList3,orderList4,orderList5,orderList6,orderList7,orderList8,orderList9,orderList10)
        var orderSelectedItemList1 = mutableListOf<Int>()
        var orderSelectedItemList2 = mutableListOf<Int>()
        var orderSelectedItemList3 = mutableListOf<Int>()
        var orderSelectedItemList4 = mutableListOf<Int>()
        var orderSelectedItemList5 = mutableListOf<Int>()
        var orderSelectedItemList6 = mutableListOf<Int>()
        var orderSelectedItemList7 = mutableListOf<Int>()
        var orderSelectedItemList8 = mutableListOf<Int>()
        var orderSelectedItemList9 = mutableListOf<Int>()
        var orderSelectedItemList10 = mutableListOf<Int>()

        for(i in 0 until orderList1.size) {
            if (itemList2.contains(orderList1[i])) {
                orderSelectedItemList1.add(1)
            } else {
                orderSelectedItemList1.add(0)
            }
        }

        for(i in 0 until orderList2.size) {
            if (itemList2.contains(orderList2[i])) {
                orderSelectedItemList2.add(1)
            } else {
                orderSelectedItemList2.add(0)
            }
        }

        for(i in 0 until orderList3.size) {
            if (itemList2.contains(orderList3[i])) {
                orderSelectedItemList3.add(1)
            } else {
                orderSelectedItemList3.add(0)
            }
        }

        for(i in 0 until orderList4.size) {
            if (itemList2.contains(orderList4[i])) {
                orderSelectedItemList4.add(1)
            } else {
                orderSelectedItemList4.add(0)
            }
        }

        for(i in 0 until orderList5.size) {
            if (itemList2.contains(orderList5[i])) {
                orderSelectedItemList5.add(1)
            } else {
                orderSelectedItemList5.add(0)
            }
        }

        for(i in 0 until orderList6.size) {
            if (itemList2.contains(orderList6[i])) {
                orderSelectedItemList6.add(1)
            } else {
                orderSelectedItemList6.add(0)
            }
        }

        for(i in 0 until orderList7.size) {
            if (itemList2.contains(orderList7[i])) {
                orderSelectedItemList7.add(1)
            } else {
                orderSelectedItemList7.add(0)
            }
        }

        for(i in 0 until orderList8.size) {
            if (itemList2.contains(orderList8[i])) {
                orderSelectedItemList8.add(1)
            } else {
                orderSelectedItemList8.add(0)
            }
        }

        for(i in 0 until orderList9.size) {
            if (itemList2.contains(orderList9[i])) {
                orderSelectedItemList9.add(1)
            } else {
                orderSelectedItemList9.add(0)
            }
        }

        for(i in 0 until orderList10.size) {
            if (itemList2.contains(orderList10[i])) {
                orderSelectedItemList10.add(1)
            } else {
                orderSelectedItemList10.add(0)
            }
        }


        var orderSelectedList = mutableListOf(orderSelectedItemList1,orderSelectedItemList2,orderSelectedItemList3,orderSelectedItemList4,orderSelectedItemList5,orderSelectedItemList6,orderSelectedItemList7,orderSelectedItemList8,orderSelectedItemList9,orderSelectedItemList10)
        var listManager2 = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        var listAdapter2 = OrderAdapter(this,dateList, orderList as ArrayList<ArrayList<String>>,orderSelectedList as ArrayList<ArrayList<Int>>)
        var recyclerList2 = binding.orderRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = listManager2
            adapter = listAdapter2
        }

        binding.ivDeleteAll.setOnClickListener{
            binding.editText.text.clear()
        }

        //카메라로 재료 등록
        binding.cameraBtn.setOnClickListener{
            Preferences.getInstance(this).putBooleanItem("takePicture",false)
            val intent = Intent(this, CameraSearchActivity::class.java)
            startActivity(intent)
        }

        // 직접입력으로 재료 등록
        var textDetectList = mutableListOf<String>()
        var textDetectSelectedItemList = mutableListOf<String>()

        var listSearchManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        var listSearchAdapter = searchListAdapter(this, textDetectList as ArrayList<String>, textDetectSelectedItemList as ArrayList<Int>)
        var listSearchRecyclerList = binding.searchListRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = listSearchManager
            adapter = listSearchAdapter
        }
        listSearchAdapter.setItemClickListener(object : searchListAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                if (textDetectSelectedItemList[position] == 0) {
                    textDetectSelectedItemList[position] = 1
                    Toast.makeText(this@AddRefrigeratorActivity,"선택하신 식재료가 담겼습니다", Toast.LENGTH_SHORT).show()
                    Preferences.getInstance(this@AddRefrigeratorActivity).putStringItem("MyRefrigerator",Preferences.getInstance(this@AddRefrigeratorActivity).getStringItem("MyRefrigerator","")+",${textDetectList[position]}")
                } else {
                    textDetectSelectedItemList[position] = 0
                    Toast.makeText(this@AddRefrigeratorActivity,"선택하신 식재료를 꺼냈습니다", Toast.LENGTH_SHORT).show()
                    Preferences.getInstance(this@AddRefrigeratorActivity).putStringItem("MyRefrigerator",Preferences.getInstance(this@AddRefrigeratorActivity).getStringItem("MyRefrigerator","").toString().replace(textDetectList[position],""))
                }
                listSearchAdapter.notifyDataSetChanged()
            }
        })

        Log.i("MyRefrigerator",Preferences.getInstance(this@AddRefrigeratorActivity).getStringItem("MyRefrigerator","").toString())


        binding.editText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(binding.editText.text.toString().isEmpty()){
                    binding.noSearchContainer.visibility = View.VISIBLE
                    binding.SearchContainer.visibility = View.GONE
                    binding.ivDeleteAll.setImageResource(0)
                    textDetectList.clear()
                    textDetectSelectedItemList.clear()
                    listSearchAdapter.notifyDataSetChanged()
                }
                else{
                    binding.noSearchContainer.visibility = View.GONE
                    binding.SearchContainer.visibility = View.VISIBLE
                    binding.ivDeleteAll.setImageResource(R.drawable.delete_all)
                    textDetectList.clear()
                    textDetectSelectedItemList.clear()
                    for(i in 0 until ingredientList.size){
                        if(ingredientList[i].contains(binding.editText.text)){
                            textDetectList.add(ingredientList[i])
                            if (itemList2.contains(ingredientList[i])) {
                                textDetectSelectedItemList.add(1)
                            } else {
                                textDetectSelectedItemList.add(0)
                            }
                        }
                    }
                    listSearchAdapter.notifyDataSetChanged()
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.editText.text.toString().isEmpty()){
                    binding.noSearchContainer.visibility = View.VISIBLE
                    binding.SearchContainer.visibility = View.GONE
                    binding.ivDeleteAll.setImageResource(0)
                    textDetectList.clear()
                    textDetectSelectedItemList.clear()
                    listSearchAdapter.notifyDataSetChanged()
                }
                else{
                    binding.noSearchContainer.visibility = View.GONE
                    binding.SearchContainer.visibility = View.VISIBLE
                    binding.ivDeleteAll.setImageResource(R.drawable.delete_all)
                    textDetectList.clear()
                    textDetectSelectedItemList.clear()
                    for(i in 0 until ingredientList.size){
                        if(ingredientList[i].contains(binding.editText.text)){
                            textDetectList.add(ingredientList[i])
                            if (itemList2.contains(ingredientList[i])) {
                                textDetectSelectedItemList.add(1)
                            } else {
                                textDetectSelectedItemList.add(0)
                            }
                        }
                    }
                    listSearchAdapter.notifyDataSetChanged()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(binding.editText.text.toString().isEmpty()){
                    binding.noSearchContainer.visibility = View.VISIBLE
                    binding.SearchContainer.visibility = View.GONE
                    binding.ivDeleteAll.setImageResource(0)
                    textDetectList.clear()
                    textDetectSelectedItemList.clear()
                    listSearchAdapter.notifyDataSetChanged()
                }
                else{
                    binding.noSearchContainer.visibility = View.GONE
                    binding.SearchContainer.visibility = View.VISIBLE
                    binding.ivDeleteAll.setImageResource(R.drawable.delete_all)
                    textDetectList.clear()
                    textDetectSelectedItemList.clear()
                    for(i in 0 until ingredientList.size){
                        if(ingredientList[i].contains(binding.editText.text)){
                            textDetectList.add(ingredientList[i])
                            if (itemList2.contains(ingredientList[i])) {
                                textDetectSelectedItemList.add(1)
                            } else {
                                textDetectSelectedItemList.add(0)
                            }
                        }
                    }
                    listSearchAdapter.notifyDataSetChanged()
                }
            }

        })

        //사람들이 많이 검색한 재료
        var listManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        var listAdapter = PopularAdapter(this, itemList, selectedItemList as ArrayList<Int>)
        var recyclerList = binding.popularRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = listManager
            adapter = listAdapter
        }

        listAdapter.setItemClickListener(object : PopularAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                if (selectedItemList[position] == 0) {
                    selectedItemList[position] = 1
                    Toast.makeText(this@AddRefrigeratorActivity,"선택하신 식재료가 담겼습니다", Toast.LENGTH_SHORT).show()
                    Preferences.getInstance(this@AddRefrigeratorActivity).putStringItem("MyRefrigerator",Preferences.getInstance(this@AddRefrigeratorActivity).getStringItem("MyRefrigerator","")+",${itemList[position]}")
                } else {
                    selectedItemList[position] = 0
                    Toast.makeText(this@AddRefrigeratorActivity,"선택하신 식재료를 꺼냈습니다", Toast.LENGTH_SHORT).show()
                    Preferences.getInstance(this@AddRefrigeratorActivity).putStringItem("MyRefrigerator",Preferences.getInstance(this@AddRefrigeratorActivity).getStringItem("MyRefrigerator","").toString().replace(itemList[position],""))
                }
                listAdapter.notifyDataSetChanged()
                listAdapter2.notifyDataSetChanged()
            }
        })


    }

    override fun release() {

    }

    override fun onBackPressed() {
        finish()
    }

    override fun onSubscribe() {

    }

    fun softkeyboardHide() {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}