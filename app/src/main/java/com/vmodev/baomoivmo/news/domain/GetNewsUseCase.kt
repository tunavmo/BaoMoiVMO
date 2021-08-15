package com.vmodev.baomoivmo.news.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.vmodev.baomoivmo.news.data.model.News
import com.vmodev.baomoivmo.news.repository.NewsRepository

class GetNewsUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(forceRefresh: Boolean = false): List<News>? {
//        return Transformations.map(repository.getTaskWithCache(forceRefresh)) {
//            it // Place here your specific logic actions
//        }

        return repository.getNews("covid_19")
    }
}