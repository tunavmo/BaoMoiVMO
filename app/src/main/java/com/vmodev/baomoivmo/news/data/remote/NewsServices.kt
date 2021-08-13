package com.vmodev.baomoivmo.news.data.remote

import com.vmodev.baomoivmo.news.data.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {
    @GET("v2/everything")
    fun getNews(
        @Query("q") q: String,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String= "45d33a95a753465d94100e108f1f0791"
    ): List<News>
}