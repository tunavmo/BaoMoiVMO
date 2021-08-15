package com.vmodev.baomoivmo.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.vmodev.baomoivmo.news.data.model.News
import com.vmodev.baomoivmo.news.domain.GetNewsUseCase
import com.vmodev.baomoivmo.news.repository.AppDispatchers
import kotlinx.coroutines.withContext
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.vmodev.baomoivmo.news.data.remote.NewsServices
import com.vmodev.baomoivmo.news.repository.NewsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsViewModel constructor(private val newsRepository: NewsRepository) : ViewModel(){

     val _news = MutableLiveData<List<News>>()

    fun getNews() {


        _news.value = newsRepository.getNews("covid-19")
    }





//    val news: LiveData<List<News>> get() = _news
//    private var newsSource: LiveData<List<News>> = MutableLiveData()
//
//    init {
//        getNews()
//    }
//    fun newsRefreshItem() = getNews()
//
//
//    private fun getNews():MutableLiveData<List<News>> {
//        _news.value = newsRepository.getNewsWithCache("covid-19")
//        return _news
//    }
//    = viewModelScope.launch(dispatchers.main) {
//        _news.removeSource(newsSource) // We make sure there is only one source of livedata (allowing us properly refresh)
//        withContext(dispatchers.io) { newsSource = getNewsUseCase(forceRefresh = forceRefresh) }
//        _news.addSource(newsSource) {
//            _news.value = it
////            if (it.status == Resource.Status.ERROR) _snackbarError.value = Event(R.string.an_error_happened)
//        }
//    }
}