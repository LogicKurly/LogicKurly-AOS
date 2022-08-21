package com.kurly.logickurly.presentation.recommendRecipe.view.fragment

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kurly.logickurly.R
import com.kurly.logickurly.data.model.Preferences
import com.kurly.logickurly.databinding.FragmentRecommendRecipeBinding
import com.kurly.logickurly.presentation.addRefrigerator.adapter.PopularAdapter
import com.kurly.logickurly.presentation.base.BaseFragment
import com.kurly.logickurly.presentation.recommendRecipe.view.RecommendRecipeActivty
import com.kurly.logickurly.presentation.recommendRecipe.view.adapter.RecommendRecipeAdapter
import com.kurly.logickurly.presentation.recommendRecipe.viewModel.RecommendRecipeViewModel

class RecommendRecipeFragment: BaseFragment<FragmentRecommendRecipeBinding>(R.layout.fragment_recommend_recipe) {

    private val mainVM : RecommendRecipeViewModel by lazy {
        ViewModelProvider(this)[RecommendRecipeViewModel::class.java]
    }

    override fun extraSetupBinding() {
        binding.vm = mainVM
    }


    override fun setup() {
    }

    override fun setupViews() {
        //선택한 식재료
        var ingredientList = mutableListOf<String>()
        var ingredient = Preferences.getInstance(context).getStringItem("ingredient","").toString().replace("[","").replace("]","")
        if(ingredient.contains(", ")){
            var temp = ingredient.split(", ")
            for(element in temp){
                ingredientList.add(element)
            }
        }
        else{
            ingredientList.add(ingredient)
        }
        binding.ingredientCount.text = binding.ingredientCount.text.toString().replace("#NUMBER#",ingredientList.size.toString())
        var selectedItemList = mutableListOf<Int>()
        for(i in 0 until ingredientList.size){
            selectedItemList.add(1)
        }
        var listManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        Log.i("ingredientList",ingredientList.toString())
        Log.i("selectedItemList",ingredientList.toString())

        var listAdapter = PopularAdapter(requireContext(), ingredientList as ArrayList<String>,
            selectedItemList as ArrayList<Int>
        )
        var recyclerList = binding.ingredientRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = listManager
            adapter = listAdapter
        }

        listAdapter.setItemClickListener(object : PopularAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
            }
        })

        //추천 음식
        var foodList = mutableListOf("차돌박이 된장찌개","김치볶음밥")
        var foodImageList = mutableListOf<String>()
        var requireIngredientList = mutableListOf("된장","마늘")
        var requireIngredientList2 = mutableListOf("계란","고춧가루")
        var requireFoodList = mutableListOf<ArrayList<String>>()

        requireFoodList.add(requireIngredientList as ArrayList<String>)
        requireFoodList.add(requireIngredientList2 as ArrayList<String>)

        var listManager2 = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        var listAdapter2 = RecommendRecipeAdapter(requireContext(),
            foodList as ArrayList<String>, foodImageList as ArrayList<String>, requireFoodList as ArrayList<ArrayList<String>>
        )
        var recyclerList2 = binding.recommendRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = listManager2
            adapter = listAdapter2
        }

        listAdapter2.setItemClickListener(object : RecommendRecipeAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                (activity as RecommendRecipeActivty).recipeMenu = foodList[position]
                (activity as RecommendRecipeActivty).setFragment(RecommendDetailFragment())
            }
        })

    }

    override fun onSubscribe() {

    }

    override fun release() {

    }
}

