package com.vmodev.baomoivmo.video.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vmodev.baomoivmo.video.data.model.Video

@Dao
interface VideoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(video:Video) : Long

    @Query("SELECT * FROM videos")
    fun getAllVideos():LiveData<List<Video>>

    @Delete
    suspend fun deleteVideo(video: Video)
}