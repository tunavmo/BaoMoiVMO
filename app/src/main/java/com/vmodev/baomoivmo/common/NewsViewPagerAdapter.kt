package com.vmodev.baomoivmo.common


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vmodev.baomoivmo.news.car.CarNewsFragment
import com.vmodev.baomoivmo.news.food.FoodNewsFragment
import com.vmodev.baomoivmo.news.health.HealthNewsFragment

import com.vmodev.baomoivmo.news.hot_news.HotNewsFragment
import com.vmodev.baomoivmo.news.law.LawNewsFragment
import com.vmodev.baomoivmo.news.covid_news.CovidNewsFragment
import com.vmodev.baomoivmo.news.showbiz.ShowbizNewsFragment
import com.vmodev.baomoivmo.news.sports.SportsNewsFragment
import com.vmodev.baomoivmo.news.tech.TechNewsFragment
import com.vmodev.baomoivmo.news.travel.TravelNewsFragment

class NewsViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 10

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HotNewsFragment()

            1 -> CovidNewsFragment()

            2 -> SportsNewsFragment()
            3 -> LawNewsFragment()

            4 -> ShowbizNewsFragment()

            5 -> CarNewsFragment()
            6 -> TechNewsFragment()

            7 -> FoodNewsFragment()

            8 -> HealthNewsFragment()
            9 -> TravelNewsFragment()

            else -> HotNewsFragment()
        }
    }
}
