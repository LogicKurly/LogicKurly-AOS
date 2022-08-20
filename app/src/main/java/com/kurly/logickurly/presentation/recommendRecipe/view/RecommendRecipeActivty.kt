package com.kurly.logickurly.presentation.recommendRecipe.view

import android.content.Intent
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.kurly.logickurly.R
import com.kurly.logickurly.databinding.ActivityKurlyMainBinding
import com.kurly.logickurly.databinding.ActivityRecommendRecipeBinding
import com.kurly.logickurly.presentation.base.BaseActivity
import com.kurly.logickurly.presentation.kurlyMain.view.adapter.ViewPagerAdapter
import com.kurly.logickurly.presentation.kurlyMain.viewModel.MainViewModel
import com.kurly.logickurly.presentation.myRefrigerator.view.MyRefrigeratorActivity
import com.kurly.logickurly.presentation.recommendRecipe.viewModel.RecommendRecipeViewModel

class RecommendRecipeActivty: BaseActivity<ActivityRecommendRecipeBinding>(R.layout.activity_recommend_recipe) {

    private val mainVM: RecommendRecipeViewModel by lazy {
        ViewModelProvider(this)[RecommendRecipeViewModel::class.java]
    }

    /** dataBinding 객체에 추가적인 작업을 할 때 사용한다 */
    override fun extraSetupBinding() {
        binding.vm = mainVM
    }

    override fun setup() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun setupViews() {
        binding.header.tvTitle?.text = "추천 레시피"
        binding.header.ivAdd?.setImageResource(R.drawable.cart_black)
        binding.header.ivBack?.setOnClickListener{
            onBackPressed()
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