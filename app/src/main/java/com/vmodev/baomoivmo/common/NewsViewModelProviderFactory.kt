package com.vmodev.baomoivmo.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vmodev.baomoivmo.news.NewsViewModel
import com.vmodev.baomoivmo.news.repository.NewsRepository

class NewsViewModelProviderFactory(
    val newsRepository: NewsRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}