package com.vmodev.baomoivmo.news.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vmodev.baomoivmo.news.data.model.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ArticlesDatabase : RoomDatabase(){

    abstract fun getArticleDao():ArticleDAO

    companion object{
        @Volatile
        private var instance:ArticlesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ArticlesDatabase::class.java,
            "articles_db.db"
        ).build()
    }
}