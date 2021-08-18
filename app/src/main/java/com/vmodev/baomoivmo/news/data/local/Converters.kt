package com.vmodev.baomoivmo.news.data.local

import androidx.room.TypeConverter
import com.vmodev.baomoivmo.news.data.model.Source

class Converters {
    @TypeConverter
    fun fromSource(source:Source):String{
        return source.name
    }
    @TypeConverter
    fun toSource(name:String):Source{
        return Source(name, name)
    }
}