package com.vmodev.baomoivmo.news

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.*
import com.vmodev.baomoivmo.news.data.model.Article
import com.vmodev.baomoivmo.news.data.model.NewsResponse
import com.vmodev.baomoivmo.news.repository.NewsRepository
import com.vmodev.baomoivmo.news.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {
    val topHeadlinesNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var topHeadlinesResponse: NewsResponse? = null
    var topHeadlinesNewsPage = 1

    val sportNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var sportNewsResponse: NewsResponse? = null
    var sportNewsPage = 1

    val covidNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var covidNewsResponse: NewsResponse? = null
    var covidNewsPage = 1

    val showbizNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var showbizNewsResponse: NewsResponse? = null
    var showbizNewsPage = 1

    val lawNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var lawNewsResponse: NewsResponse? = null
    var lawNewsPage = 1

    val carNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var carNewsResponse: NewsResponse? = null
    var carNewsPage = 1

    val techNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var techNewsResponse: NewsResponse? = null
    var techNewsPage = 1

    val foodNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var foodNewsResponse: NewsResponse? = null
    var foodNewsPage = 1

    val healthNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var healthNewsResponse: NewsResponse? = null
    var healthNewsPage = 1

    val travelNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var travelNewsResponse: NewsResponse? = null
    var travelNewsPage = 1


    init {
        getTopHeadlinesNews("us")
        getSportNews()
        getCovidNews()
        getCarNews()
        getFoodNews()
        getHealthNews()
        getLawNews()
        getShowbizNews()
        getTechNews()
        getTravelNews()
    }

    fun getTopHeadlinesNews(countryCode: String) = viewModelScope.launch {
        topHeadlinesNews.postValue(Resource.Loading())
        val response = newsRepository.getTopHeadlines(countryCode, topHeadlinesNewsPage)
        topHeadlinesNews.postValue(handleTopHeadlinesNewsResponse(response))
    }

    fun getSportNews() = viewModelScope.launch {
        sportNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews("sport", sportNewsPage)
        sportNews.postValue(handleSportNews(response))
    }

    fun getCovidNews() = viewModelScope.launch {
        covidNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews("covid", covidNewsPage)
        covidNews.postValue(handleCovidNews(response))
    }

    fun getShowbizNews() = viewModelScope.launch {
        showbizNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews("entertainment", showbizNewsPage)
        showbizNews.postValue(handleShowbizNews(response))
    }

    fun getLawNews() = viewModelScope.launch {
        lawNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews("law", lawNewsPage)
        lawNews.postValue(handleLawNews(response))
    }

    fun getCarNews() = viewModelScope.launch {
        carNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews("car", carNewsPage)
        carNews.postValue(handleCarNews(response))
    }

    fun getTechNews() = viewModelScope.launch {
        techNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews("technology", techNewsPage)
        techNews.postValue(handleTechNews(response))
    }

    fun getFoodNews() = viewModelScope.launch {
        foodNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews("food", foodNewsPage)
        foodNews.postValue(handleFoodNews(response))
    }

    fun getHealthNews() = viewModelScope.launch {
        healthNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews("health", healthNewsPage)
        healthNews.postValue(handleHealthNews(response))
    }

    fun getTravelNews() = viewModelScope.launch {
        travelNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews("travel", travelNewsPage)
        travelNews.postValue(handleTravelNews(response))
    }


    private fun handleTopHeadlinesNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                topHeadlinesNewsPage++
                if (topHeadlinesResponse == null) {
                    topHeadlinesResponse = resultResponse
                } else {
                    val oldArticles = topHeadlinesResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(topHeadlinesResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSportNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                sportNewsPage++
                if (sportNewsResponse == null) {
                    sportNewsResponse = resultResponse
                } else {
                    val oldArticles = sportNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(sportNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleCovidNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                covidNewsPage++
                if (covidNewsResponse == null) {
                    covidNewsResponse = resultResponse
                } else {
                    val oldArticles = covidNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(covidNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleShowbizNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                showbizNewsPage++
                if (showbizNewsResponse == null) {
                    showbizNewsResponse = resultResponse
                } else {
                    val oldArticles = showbizNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(showbizNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleLawNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                lawNewsPage++
                if (lawNewsResponse == null) {
                    lawNewsResponse = resultResponse
                } else {
                    val oldArticles = lawNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(lawNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleCarNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                carNewsPage++
                if (carNewsResponse == null) {
                    carNewsResponse = resultResponse
                } else {
                    val oldArticles = carNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(carNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleTechNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                techNewsPage++
                if (techNewsResponse == null) {
                    techNewsResponse = resultResponse
                } else {
                    val oldArticles = techNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(techNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleFoodNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                foodNewsPage++
                if (foodNewsResponse == null) {
                    foodNewsResponse = resultResponse
                } else {
                    val oldArticles = foodNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(foodNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleHealthNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                healthNewsPage++
                if (healthNewsResponse == null) {
                    healthNewsResponse = resultResponse
                } else {
                    val oldArticles = healthNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(healthNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleTravelNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                travelNewsPage++
                if (travelNewsResponse == null) {
                    travelNewsResponse = resultResponse
                } else {
                    val oldArticles = travelNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(travelNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSaveNews() = newsRepository.getSavedNews()

    fun deteleArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }
}