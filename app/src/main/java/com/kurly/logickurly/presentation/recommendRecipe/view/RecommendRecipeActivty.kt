package com.kurly.logickurly.presentation.recommendRecipe.view

import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kurly.logickurly.R
import com.kurly.logickurly.data.model.Preferences
import com.kurly.logickurly.databinding.ActivityRecommendRecipeBinding
import com.kurly.logickurly.presentation.addRefrigerator.adapter.PopularAdapter
import com.kurly.logickurly.presentation.base.BaseActivity
import com.kurly.logickurly.presentation.recommendRecipe.view.adapter.RecommendRecipeAdapter
import com.kurly.logickurly.presentation.recommendRecipe.view.fragment.RecommendRecipeFragment
import com.kurly.logickurly.presentation.recommendRecipe.viewModel.RecommendRecipeViewModel

class RecommendRecipeActivty: BaseActivity<ActivityRecommendRecipeBinding>(R.layout.activity_recommend_recipe) {

    lateinit var recommendRecipeFragment: RecommendRecipeFragment

    private val mainVM: RecommendRecipeViewModel by lazy {
        ViewModelProvider(this)[RecommendRecipeViewModel::class.java]
    }

    /** dataBinding 객체에 추가적인 작업을 할 때 사용한다 */
    override fun extraSetupBinding() {
        binding.vm = mainVM
    }

    override fun setup() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        recommendRecipeFragment = RecommendRecipeFragment()
        setFragment(recommendRecipeFragment)
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
        if (supportFragmentManager.findFragmentById(R.id.fragment) == recommendRecipeFragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment, fragment)
        transaction.commit()
    }

}