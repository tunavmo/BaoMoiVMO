package com.vmodev.baomoivmo.news

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.*
import com.vmodev.baomoivmo.news.data.model.NewsResponse
import com.vmodev.baomoivmo.news.repository.NewsRepository
import com.vmodev.baomoivmo.news.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {
    val topHeadlinesNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val sportNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val covidNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var topHeadlinesNewsPage = 1

    init {
        getTopHeadlinesNews("us")
        getSportNews("sport")
        getCovidNews("covid-19")
    }

    fun getTopHeadlinesNews(countryCode:String) = viewModelScope.launch {
        topHeadlinesNews.postValue(Resource.Loading())
        val response = newsRepository.getTopHeadlines(countryCode, topHeadlinesNewsPage)
        topHeadlinesNews.postValue(handleTopHeadlinesNewsResponse(response))
    }

    fun getSportNews(query:String) = viewModelScope.launch {
        sportNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(query, topHeadlinesNewsPage)
        sportNews.postValue(handleSportNews(response))
    }

    fun getCovidNews(query:String) = viewModelScope.launch {
        covidNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(query, topHeadlinesNewsPage)
        covidNews.postValue(handleCovidNews(response))
    }


    private fun handleTopHeadlinesNewsResponse(response: Response<NewsResponse>):Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSportNews(response: Response<NewsResponse>):Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleCovidNews(response: Response<NewsResponse>):Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    private fun handleSearchNewsResponse(response: Response<NewsResponse>):Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}