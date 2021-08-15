package com.vmodev.baomoivmo.news.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vmodev.baomoivmo.R
import com.vmodev.baomoivmo.databinding.FragmentHotNewsBinding
import com.vmodev.baomoivmo.databinding.ItemNewsBinding
import com.vmodev.baomoivmo.news.NewsViewModel
import com.vmodev.baomoivmo.news.data.model.News

class NewsAdapter: RecyclerView.Adapter<NewsViewHolder>() {
    var _news = mutableListOf<News>()

    fun setNewsList(newsList: List<News>) {
        this._news = newsList.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }
//    =
//        NewsViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
//        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int){
        val news = _news[position]
        holder.binding.newsTitle.text = news.articles!![0].title
        Glide.with(holder.itemView.context).load(news.articles!![0].urlToImage).into(holder.binding.newsImage)
        holder.binding.newsPublished.text = news.articles!![0].publishedAt
    }

    override fun getItemCount(): Int = _news!!.size
}

class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
//    val binding = ItemNewsBinding.bind(parent)
//    fun bindTo(news: News, viewModel: NewsViewModel) {
//        binding.news = news
//        binding.viewmodel = viewModel
//    }
}