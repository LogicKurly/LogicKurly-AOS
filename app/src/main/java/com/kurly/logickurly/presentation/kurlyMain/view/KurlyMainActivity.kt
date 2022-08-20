package com.kurly.logickurly.presentation.kurlyMain.view

import android.content.Intent
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.kurly.logickurly.R
import com.kurly.logickurly.databinding.ActivityKurlyMainBinding
import com.kurly.logickurly.presentation.base.BaseActivity
import com.kurly.logickurly.presentation.kurlyMain.view.adapter.ViewPagerAdapter
import com.kurly.logickurly.presentation.kurlyMain.viewModel.MainViewModel
import com.kurly.logickurly.presentation.myRefrigerator.view.MyRefrigeratorActivity


class KurlyMainActivity : BaseActivity<ActivityKurlyMainBinding>(R.layout.activity_kurly_main) {

    private val tabTitleArray = arrayOf("컬리추천", "신상품", "베스트","알뜰쇼핑","특가/혜택")

    private val mainVM: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    /** dataBinding 객체에 추가적인 작업을 할 때 사용한다 */
    override fun extraSetupBinding() {
        binding.vm = mainVM
    }

    override fun setup() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun setupViews() {
        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        TabLayoutMediator(binding.tablayout,binding.viewPager){ tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()

        binding.myRefrigeratorBtn.setOnClickListener{
            val intent = Intent(this, MyRefrigeratorActivity::class.java)
            startActivity(intent)
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