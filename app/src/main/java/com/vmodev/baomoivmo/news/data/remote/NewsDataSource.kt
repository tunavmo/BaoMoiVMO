package com.vmodev.baomoivmo.news.data.remote

class NewsDataSource(private val newsServices: NewsServices) {
    fun getNewsAsync(query:String) = newsServices.getNews(query)
}