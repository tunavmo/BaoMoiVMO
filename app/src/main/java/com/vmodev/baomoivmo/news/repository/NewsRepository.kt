package com.vmodev.baomoivmo.news.repository

import android.util.Log
import com.vmodev.baomoivmo.news.data.model.News
import com.vmodev.baomoivmo.news.data.remote.NewsServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//
//interface NewsRepository {
//
//    fun getNewsWithCache(query: String): List<News>
//}
//
//abstract class NewsRepositoryImpl(private val service: NewsServices) : NewsRepository {
//    override fun getNewsWithCache(query: String): List<News> {
//        return service.getNews(query)
//    }
//
//}
class NewsRepository constructor(private val newsServices: NewsServices) {

    fun getNews(query: String): News? {
        var _news: News? = null
        val response = newsServices.getNews(query)

        response.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                _news = response.body()!!
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                t.message?.let { Log.e("RETROFIT_ERROR", it) }
            }
        })
        return _news
    }
}