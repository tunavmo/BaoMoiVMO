package com.vmodev.baomoivmo.news.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vmodev.baomoivmo.R
import com.vmodev.baomoivmo.databinding.ItemNewsBinding
import com.vmodev.baomoivmo.news.NewsViewModel
import com.vmodev.baomoivmo.news.data.model.News

class NewsAdapter(val viewModel: NewsViewModel) : RecyclerView.Adapter<NewsViewHolder>() {
    val news : List<News>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder
    = NewsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) = holder.bindTo(news!![position], viewModel)

    override fun getItemCount(): Int = news!!.size
}

class NewsViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
    val binding = ItemNewsBinding.bind(parent)
    fun bindTo(news: News, viewModel: NewsViewModel) {
binding.news = news
        binding.viewmodel = viewModel
    }
}