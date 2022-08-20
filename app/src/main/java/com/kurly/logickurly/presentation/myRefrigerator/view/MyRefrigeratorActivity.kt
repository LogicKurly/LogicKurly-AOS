package com.kurly.logickurly.presentation.myRefrigerator.view

import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kurly.logickurly.R
import com.kurly.logickurly.databinding.ActivityMyRefrigeratorBinding
import com.kurly.logickurly.presentation.myRefrigerator.viewModel.MyRefrigeratorViewModel
import com.kurly.logickurly.presentation.base.BaseActivity

class MyRefrigeratorActivity  : BaseActivity<ActivityMyRefrigeratorBinding>(R.layout.activity_my_refrigerator) {


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
        binding.header.ivBack?.setOnClickListener{
            onBackPressed()
        }

    }


    override fun onSubscribe() {

    }

    override fun release() {

    }

    fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        finish()
    }
}