package com.vmodev.baomoivmo.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.vmodev.baomoivmo.news.data.model.News
import com.vmodev.baomoivmo.news.domain.GetNewsUseCase
import com.vmodev.baomoivmo.news.repository.AppDispatchers
import kotlinx.coroutines.withContext
import androidx.lifecycle.*
import com.vmodev.baomoivmo.news.repository.NewsRepository

class NewsViewModel(private val newsRepository: NewsRepository) {
    private val _news = MediatorLiveData<List<News>>()
    val news: LiveData<List<News>> get() = _news
    private var newsSource: LiveData<List<News>> = MutableLiveData()


    init {
        getNews()
    }
    fun newsRefreshItem() = getNews()


    private fun getNews()= newsRepository.getNewsWithCache("covid-19")
//    = viewModelScope.launch(dispatchers.main) {
//        _news.removeSource(newsSource) // We make sure there is only one source of livedata (allowing us properly refresh)
//        withContext(dispatchers.io) { newsSource = getNewsUseCase(forceRefresh = forceRefresh) }
//        _news.addSource(newsSource) {
//            _news.value = it
////            if (it.status == Resource.Status.ERROR) _snackbarError.value = Event(R.string.an_error_happened)
//        }
//    }
}