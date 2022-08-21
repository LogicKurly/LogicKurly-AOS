package com.kurly.logickurly.presentation.recommendRecipe.view.fragment

import androidx.lifecycle.ViewModelProvider
import com.google.gson.JsonParser
import com.kurly.logickurly.R
import com.kurly.logickurly.data.model.Preferences
import com.kurly.logickurly.databinding.FragmentRecommendDetailBinding
import com.kurly.logickurly.presentation.base.BaseFragment
import com.kurly.logickurly.presentation.myRefrigerator.view.dialog.NoticeDialog
import com.kurly.logickurly.presentation.recommendRecipe.view.RecommendRecipeActivty
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

        binding.ivNotice.setOnClickListener{
            RecipeNoticeDialog().show(this.parentFragmentManager, "")
        }

    }

    override fun onSubscribe() {

    }

    override fun release() {

    }
}


