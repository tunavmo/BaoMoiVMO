package com.vmodev.baomoivmo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vmodev.baomoivmo.common.MainViewPagerAdapter
import com.vmodev.baomoivmo.common.NewsViewModelProviderFactory
import com.vmodev.baomoivmo.common.VideoViewModelProviderFactory
import com.vmodev.baomoivmo.news.NewsViewModel
import com.vmodev.baomoivmo.news.data.local.ArticlesDatabase
import com.vmodev.baomoivmo.news.repository.NewsRepository
import com.vmodev.baomoivmo.video.VideoViewModel
import com.vmodev.baomoivmo.video.data.local.VideosDatabase
import com.vmodev.baomoivmo.video.repository.VideoRepository
import com.vmodev.baomoivmo.video.view.VideoAdapter

class MainActivity : AppCompatActivity() {
    lateinit var viewModel:NewsViewModel
    lateinit var videoViewModel:VideoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val newsRepository = NewsRepository(ArticlesDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)


        val videoRepository = VideoRepository(VideosDatabase(this))
        val videoViewModelProviderFactory = VideoViewModelProviderFactory(videoRepository)
        videoViewModel = ViewModelProvider(this, videoViewModelProviderFactory).get(VideoViewModel::class.java)


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
