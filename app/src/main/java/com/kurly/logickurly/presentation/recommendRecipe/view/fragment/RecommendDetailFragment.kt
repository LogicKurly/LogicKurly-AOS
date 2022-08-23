package com.kurly.logickurly.presentation.recommendRecipe.view.fragment

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.kurly.logickurly.R
import com.kurly.logickurly.data.model.Preferences
import com.kurly.logickurly.databinding.FragmentRecommendDetailBinding
import com.kurly.logickurly.presentation.addRefrigerator.adapter.PopularAdapter
import com.kurly.logickurly.presentation.base.BaseFragment
import com.kurly.logickurly.presentation.recommendRecipe.view.RecommendRecipeActivty
import com.kurly.logickurly.presentation.recommendRecipe.view.adapter.IngredientAdapter
import com.kurly.logickurly.presentation.recommendRecipe.view.adapter.RequireAdapter
import com.kurly.logickurly.presentation.recommendRecipe.view.dialog.AddCartDialog
import com.kurly.logickurly.presentation.recommendRecipe.view.dialog.RecipeNoticeDialog
import com.kurly.logickurly.presentation.recommendRecipe.viewModel.RecommendRecipeViewModel

class RecommendDetailFragment : BaseFragment<FragmentRecommendDetailBinding>(R.layout.fragment_recommend_detail) {

    private val mainVM : RecommendRecipeViewModel by lazy {
        ViewModelProvider(this)[RecommendRecipeViewModel::class.java]
    }

    override fun extraSetupBinding() {
        binding.vm = mainVM
    }


    override fun setup() {
    }

    override fun setupViews() {

        binding.tvRecipe.text = (activity as RecommendRecipeActivty).recipeMenu
        if((activity as RecommendRecipeActivty).recipeMenu.contains("된장찌개")){
            binding.recipeContainer.setBackgroundResource(R.drawable.jjigae)
            binding.tvRecipeDetail.text = resources.getString(R.string.recipe_jjigae)
        }
        else if((activity as RecommendRecipeActivty).recipeMenu.contains("김치볶음밥")){
            binding.recipeContainer.setBackgroundResource(R.drawable.kimchi)
            binding.tvRecipeDetail.text = resources.getString(R.string.recipe_kimchi)
        }
        else{
            binding.recipeContainer.setBackgroundResource(R.drawable.default_img)
            binding.tvRecipeDetail.text = resources.getString(R.string.recipe)
        }

        binding.ivNotice.setOnClickListener{
            RecipeNoticeDialog().show(this.parentFragmentManager, "")
        }


        var listManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        var ingredientList = mutableListOf("배추","고춧가루")
        var itemImageList = mutableListOf<String>()
        var itemDeliverList = mutableListOf(mutableListOf("샛별배송","샛별배송","샛별배송","낯배송","샛별배송"),mutableListOf("샛별배송","샛별배송","샛별배송","낯배송","샛별배송")
        ,mutableListOf("샛별배송","샛별배송","샛별배송","낯배송","샛별배송"),mutableListOf("샛별배송","샛별배송","샛별배송","낯배송","샛별배송"),mutableListOf("샛별배송","샛별배송","샛별배송","낯배송","샛별배송"))
        var itemProductList = mutableListOf(mutableListOf("[햇님마을] 고춧가루 2종","[예림원] 빛깔고운 고춧가루","[진미] 햇살홍 고춧가루","[진미] 국산태양초 고춧가루","[햇님마을] 영양산 고춧가루"),mutableListOf("[햇님마을] 고춧가루 2종","[예림원] 빛깔고운 고춧가루","[진미] 햇살홍 고춧가루","[진미] 국산태양초 고춧가루","[햇님마을] 영양산 고춧가루")
        ,mutableListOf("[햇님마을] 고춧가루 2종","[예림원] 빛깔고운 고춧가루","[진미] 햇살홍 고춧가루","[진미] 국산태양초 고춧가루","[햇님마을] 영양산 고춧가루"),mutableListOf("[햇님마을] 고춧가루 2종","[예림원] 빛깔고운 고춧가루","[진미] 햇살홍 고춧가루","[진미] 국산태양초 고춧가루","[햇님마을] 영양산 고춧가루"),mutableListOf("[햇님마을] 고춧가루 2종","[예림원] 빛깔고운 고춧가루","[진미] 햇살홍 고춧가루","[진미] 국산태양초 고춧가루","[햇님마을] 영양산 고춧가루"))
        var itemValueList = mutableListOf(mutableListOf(12000,9300,17300,8500,10500),mutableListOf(12000,9300,17300,8500,10500),mutableListOf(12000,9300,17300,8500,10500)
        ,mutableListOf(12000,9300,17300,8500,10500),mutableListOf(12000,9300,17300,8500,10500))

        var listAdapter = IngredientAdapter(requireContext(), ingredientList as ArrayList<String>, itemImageList,itemDeliverList,itemProductList,itemValueList)
        var recyclerList = binding.addIngredientRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = listManager
            adapter = listAdapter
        }

        listAdapter.setItemClickListener(object : IngredientAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                AddCartDialog().show(parentFragmentManager, "")
            }
        })

        var requireIngredientList = mutableListOf("차돌박이","양파","호박","감자","두부","된장","파","마늘","고추")
        //기존에 냉장고에 담겨 있는 재료는 선택된 상태
        var selectedItemList = mutableListOf<Int>()
        var myRefrigerator = Preferences.getInstance(requireContext()).getStringItem("MyRefrigerator","")
        var itemList2 = myRefrigerator?.split(",")
        var tempList = mutableListOf<String>()
        for(i in 0 until itemList2!!.size){
            if(itemList2[i]!="" && itemList2[i]!=","){
                tempList.add(itemList2[i].replace(",",""))
            }
        }
        itemList2 = tempList
        for(i in 0 until requireIngredientList.size){
            if(itemList2.contains(requireIngredientList[i])){
                selectedItemList.add(1)
            }
            else{
                selectedItemList.add(0)
            }
        }


        val listManager2 = FlexboxLayoutManager(activity).apply{
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
        }

        var listAdapter2 = RequireAdapter(requireContext(), requireIngredientList as ArrayList<String>, selectedItemList as ArrayList<Int>)
        var recyclerList2 = binding.ingredientRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = listManager2
            adapter = listAdapter2
        }

        listAdapter2.setItemClickListener(object : RequireAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
            }
        })




    }

    override fun onSubscribe() {

    }

    override fun release() {

    }
}


