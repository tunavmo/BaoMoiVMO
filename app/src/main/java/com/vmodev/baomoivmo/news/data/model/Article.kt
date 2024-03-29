package com.vmodev.baomoivmo.news.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vmodev.baomoivmo.common.Constants.Companion.DB_TABLE_ARTICLE
import java.io.Serializable

@Entity(
    tableName = DB_TABLE_ARTICLE
)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Serializable