package com.kurly.logickurly.presentation.kurlyMain.view

import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kurly.logickurly.R
import com.kurly.logickurly.databinding.ActivityKurlyMainBinding
import com.kurly.logickurly.presentation.base.BaseActivity
import com.kurly.logickurly.presentation.kurlyMain.viewModel.MainViewModel


class kurlyMainActivity : BaseActivity<ActivityKurlyMainBinding>(R.layout.activity_kurly_main) {


    private val mainVM: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    /** dataBinding 객체에 추가적인 작업을 할 때 사용한다 */
    override fun extraSetupBinding() {
        binding.vm = mainVM
    }

    override fun setup() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun setupViews() {

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
        //finish()
    }
}