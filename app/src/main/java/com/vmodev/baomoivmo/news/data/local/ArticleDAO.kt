package com.vmodev.baomoivmo.news.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vmodev.baomoivmo.news.data.model.Article

@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article) : Long

    @Query("SELECT * FROM articles")
    fun getAllArticles():LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}