package com.kurly.logickurly.presentation.kurlyMain.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kurly.logickurly.presentation.kurlyMain.view.fragment.*

private const val NUM_TABS = 5


class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return kurlyRecommendFragment()
            1 -> return kurlyNewProductFragment()
            2 -> return BestFragment()
            3 -> return ShoppingFragment()
            4 -> return SpecialFragment()
        }
        return kurlyRecommendFragment()
    }
}