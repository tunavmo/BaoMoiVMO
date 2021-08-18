package com.vmodev.baomoivmo.video

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vmodev.baomoivmo.video.data.model.VideoResponse
import com.vmodev.baomoivmo.video.repository.VideoRepository
import kotlinx.coroutines.launch
import com.vmodev.baomoivmo.video.utils.Resource
import retrofit2.Response

class VideoViewModel(
    val videoRepository: VideoRepository
) : ViewModel() {
    val videos: MutableLiveData<Resource<VideoResponse>> = MutableLiveData()

    init {
        getVideo()
    }

    fun getVideo() = viewModelScope.launch {
        videos.postValue(Resource.Loading())
        val response = videoRepository.getVideo()
        videos.postValue(handleVideoResponse(response))
    }

    private fun handleVideoResponse(response: Response<VideoResponse>): Resource<VideoResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)

            }

        }
        return Resource.Error(response.message())
    }
}