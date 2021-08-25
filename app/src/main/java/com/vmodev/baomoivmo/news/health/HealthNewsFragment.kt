package com.vmodev.baomoivmo.news.health

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AbsListView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vmodev.baomoivmo.MainActivity
import com.vmodev.baomoivmo.R
import com.vmodev.baomoivmo.common.Constants.Companion.HEALTH_NEWS_FRAGMENT_TAG
import com.vmodev.baomoivmo.common.Constants.Companion.QUERY_PAGE_SIZE
import com.vmodev.baomoivmo.common.Constants.Companion.TECH_NEWS_FRAGMENT_TAG
import com.vmodev.baomoivmo.common.Constants.Companion.TRAVEL_NEWS_FRAGMENT_TAG
import com.vmodev.baomoivmo.news.NewsViewModel
import com.vmodev.baomoivmo.news.utils.Resource
import com.vmodev.baomoivmo.news.view.NewsAdapter
import com.vmodev.baomoivmo.webview.WebviewActivity
import kotlinx.android.synthetic.main.fragment_health_news.*
import kotlinx.android.synthetic.main.fragment_hot_news.*
import kotlinx.android.synthetic.main.fragment_tech_news.*
import kotlinx.android.synthetic.main.fragment_travel_news.*


class HealthNewsFragment : Fragment(R.layout.fragment_health_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        newsAdapter.setOnItemClickListener {
            viewModel.saveArticle(it)
            val intent = Intent(context, WebviewActivity::class.java).apply {
                putExtra("URL", it.url)
            }
            startActivity(intent)
        }


        viewModel.healthNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.diff.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.totalResults / QUERY_PAGE_SIZE +2
                        isLastPage = viewModel.healthNewsPage == totalPages
                        if(isLastPage){
                            rcvHealthNews.setPadding(0,0,0,0)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(HEALTH_NEWS_FRAGMENT_TAG, message)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBarHealth.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        paginationProgressBarHealth.visibility = View.VISIBLE
        isLoading = true
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling=true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition>=0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling
            if(shouldPaginate){
                viewModel.getHealthNews()
                isScrolling = false
            }

        }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rcvHealthNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            addOnScrollListener(this@HealthNewsFragment.scrollListener)
        }
    }
}