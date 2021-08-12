package com.vmodev.baomoivmo.common


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vmodev.baomoivmo.news.NewsFragment
import com.vmodev.baomoivmo.trending.TrendingFragment
import com.vmodev.baomoivmo.utility.UtilityFragment
import com.vmodev.baomoivmo.video.VideoFragment


class MainViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewsFragment()

            1 -> VideoFragment()

            2 -> TrendingFragment()

            3 -> UtilityFragment()

            else -> NewsFragment()
        }
    }
}
