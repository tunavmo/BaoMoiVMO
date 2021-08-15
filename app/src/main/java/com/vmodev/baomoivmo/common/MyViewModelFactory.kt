package com.vmodev.baomoivmo.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vmodev.baomoivmo.news.NewsViewModel
import com.vmodev.baomoivmo.news.repository.NewsRepository

class MyViewModelFactory constructor(private val repository: NewsRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            NewsViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}