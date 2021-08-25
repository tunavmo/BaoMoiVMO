package com.vmodev.baomoivmo.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vmodev.baomoivmo.R
import com.vmodev.baomoivmo.common.MainViewPagerAdapter
import com.vmodev.baomoivmo.common.NewsViewPagerAdapter


class NewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_news, container, false)

        val viewPager = view.findViewById<ViewPager2>(R.id.view_pager_news)
        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout_news)
        val adapter = NewsViewPagerAdapter(parentFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->

            when(position)
            {
                0 -> tab.text = getString(R.string.hot_news)
                1 -> tab.text = getString(R.string.covid_news)
                2 -> tab.text = getString(R.string.sport_news)
                3 -> tab.text = getString(R.string.showbiz_news)
                4 -> tab.text = getString(R.string.law_news)
                5 -> tab.text = getString(R.string.car_news)
                6 -> tab.text = getString(R.string.tech_news)
                7 -> tab.text = getString(R.string.food_news)
                8 -> tab.text = getString(R.string.health_news)
                9 -> tab.text = getString(R.string.travel_news)
            }
        }.attach()

        return view
    }

}