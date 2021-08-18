package com.vmodev.baomoivmo.news.repository

import com.vmodev.baomoivmo.news.data.local.ArticlesDatabase
import com.vmodev.baomoivmo.news.data.remote.RetrofitInstance

class NewsRepository(private val db: ArticlesDatabase) {
    suspend fun getTopHeadlines(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getTopHeadlines(countryCode, pageNumber)

    suspend fun searchNews(query: String, pageNumber: Int) =
        RetrofitInstance.api.getNewsbyQuery(query)

}