package com.vmodev.baomoivmo.news.repository

import com.vmodev.baomoivmo.news.data.model.News
import com.vmodev.baomoivmo.news.data.remote.NewsServices

interface NewsRepository {
    fun getNewsWithCache(query: String): List<News>
}

abstract class NewsRepositoryImpl(private val service: NewsServices) : NewsRepository {
    override fun getNewsWithCache(query: String): List<News> {
        return service.getNews(query)
    }

}