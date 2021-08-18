package com.vmodev.baomoivmo.news.hot_news

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vmodev.baomoivmo.MainActivity
import com.vmodev.baomoivmo.R
import com.vmodev.baomoivmo.news.NewsViewModel
import com.vmodev.baomoivmo.news.utils.Resource
import com.vmodev.baomoivmo.news.view.NewsAdapter
import com.vmodev.baomoivmo.webview.WebviewActivity
import kotlinx.android.synthetic.main.fragment_hot_news.*


class HotNewsFragment : Fragment(R.layout.fragment_hot_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        newsAdapter.setOnItemClickListener {
            val intent = Intent(context, WebviewActivity::class.java).apply {
                putExtra("URL", it.url)
            }
            startActivity(intent)
        }


        viewModel.topHeadlinesNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.diff.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("HotNewsFragment", "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rcvHotNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }
}