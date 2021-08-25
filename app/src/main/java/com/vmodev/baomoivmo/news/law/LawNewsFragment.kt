package com.vmodev.baomoivmo.news.law

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
import com.vmodev.baomoivmo.common.Constants.Companion.LAW_NEWS_FRAGMENT_TAG
import com.vmodev.baomoivmo.common.Constants.Companion.QUERY_PAGE_SIZE
import com.vmodev.baomoivmo.common.Constants.Companion.TRAVEL_NEWS_FRAGMENT_TAG
import com.vmodev.baomoivmo.news.NewsViewModel
import com.vmodev.baomoivmo.news.utils.Resource
import com.vmodev.baomoivmo.news.view.NewsAdapter
import com.vmodev.baomoivmo.webview.WebviewActivity
import kotlinx.android.synthetic.main.fragment_hot_news.*
import kotlinx.android.synthetic.main.fragment_law_news.*
import kotlinx.android.synthetic.main.fragment_travel_news.*


class LawNewsFragment : Fragment(R.layout.fragment_law_news) {

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


        viewModel.lawNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.diff.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.totalResults / QUERY_PAGE_SIZE +2
                        isLastPage = viewModel.travelNewsPage == totalPages
                        if(isLastPage){
                            rcvLawNews.setPadding(0,0,0,0)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(LAW_NEWS_FRAGMENT_TAG, message)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBarLaw.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        paginationProgressBarLaw.visibility = View.VISIBLE
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
                viewModel.getLawNews()
                isScrolling = false
            }

        }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rcvLawNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            addOnScrollListener(this@LawNewsFragment.scrollListener)
        }
    }
}