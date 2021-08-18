package com.vmodev.baomoivmo.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vmodev.baomoivmo.video.VideoViewModel
import com.vmodev.baomoivmo.video.repository.VideoRepository

class VideoViewModelProviderFactory(
    val videoRepository: VideoRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VideoViewModel(videoRepository) as T
    }
}