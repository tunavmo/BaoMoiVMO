package com.vmodev.baomoivmo.common


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.vmodev.baomoivmo.news.hot_news.HotNewsFragment
import com.vmodev.baomoivmo.news.new_news.NewNewsFragment
import com.vmodev.baomoivmo.news.sports.SportsNewsFragment

class NewsViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 9

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HotNewsFragment()

            1 -> NewNewsFragment()

            2 -> SportsNewsFragment()
            3 -> HotNewsFragment()

            4 -> NewNewsFragment()

            5 -> SportsNewsFragment()
            6 -> HotNewsFragment()

            7 -> NewNewsFragment()

            8 -> SportsNewsFragment()

            else -> HotNewsFragment()
        }
    }
}
