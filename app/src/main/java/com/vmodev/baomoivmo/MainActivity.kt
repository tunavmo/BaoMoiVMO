package com.vmodev.baomoivmo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vmodev.baomoivmo.common.MainViewPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager = findViewById<ViewPager2>(R.id.view_pager_main)
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_nav_main)
        val adapter = MainViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = false
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        bottomNav.menu.findItem(R.id.menu_nav_news).isChecked = true
                    }
                    1 -> {
                        bottomNav.menu.findItem(R.id.menu_nav_video).isChecked = true
                    }
                    2 -> {
                        bottomNav.menu.findItem(R.id.menu_nav_trending).isChecked = true
                    }
                    3 -> {
                        bottomNav.menu.findItem(R.id.menu_nav_utility).isChecked = true
                    }
                }
                super.onPageSelected(position)
            }
        })

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_nav_news -> {
                    viewPager.currentItem = 0
                }
                R.id.menu_nav_video -> {
                    viewPager.currentItem = 1
                }
                R.id.menu_nav_trending -> {
                    viewPager.currentItem = 2
                }
                R.id.menu_nav_utility -> {
                    viewPager.currentItem = 3
                }
            }
            false
        }


    }

}
