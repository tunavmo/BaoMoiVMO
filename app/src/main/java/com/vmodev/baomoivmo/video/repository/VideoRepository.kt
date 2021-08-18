package com.vmodev.baomoivmo.video.repository

import com.vmodev.baomoivmo.video.data.remote.RetrofitInstance
import com.vmodev.baomoivmo.video.data.local.VideosDatabase

class VideoRepository(private val db : VideosDatabase) {
    suspend fun getVideo() = RetrofitInstance.api.getVideo()
}