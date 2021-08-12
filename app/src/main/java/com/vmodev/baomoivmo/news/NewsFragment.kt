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
            tab.text = "Tab ${(position + 1)}"
            when(position)
            {
                0 -> tab.text = "Tab ${(position + 1)}"
            }
        }.attach()

        return view
    }

}